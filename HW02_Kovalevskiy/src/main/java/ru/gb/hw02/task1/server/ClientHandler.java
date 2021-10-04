package ru.gb.hw02.task1.server;

import ru.gb.hw02.task1.database.entities.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {

    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private User user;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    doAuthentication();
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection(socket);
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }

    private void closeConnection(Socket socket) {
        server.unsubscribe(this);
        server.broadcastMessage(String.format("User[%s] is out.", name));

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    private void doAuthentication() throws IOException {
        sendMessage("Greeting you in the Outstanding Chat.");
        sendMessage("Please do authentication. Template is: -auth [login] [password]");

        while (true) {
            String maybeCredentials = in.readUTF();

            if (maybeCredentials.startsWith("-auth")) {
                String[] credentials = maybeCredentials.split("\\s");

                Optional<User> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);

                if (maybeUser.isPresent()) {
                    user = maybeUser.get();
                    name = user.getName();
                    
                    if (server.isNotUserOccupied(name)) {
                        sendMessage("AUTH OK.");
                        sendMessage(String.format("Welcome, %s!\n", name));
                        server.broadcastMessage(String.format("User [ %s ] entered chat.\n", name));
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage("Current user is already logged in");
                    }
                } else {
                    sendMessage("Invalid credentials.");
                }
            } else {
                sendMessage("Invalid auth operation");
            }
        }
    }

    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();
            
            if (inboundMessage.equals("-exit")) {
                break;
            }
    
            if (inboundMessage.startsWith("-update")) {
                String[] data = inboundMessage.split("\\s");
                int result = server.getAuthService().updateUserData(user, data[1], data[2]);
                
                String message = (result == 1)
                        ? String.format("field [ %s ] was updated!", data[1])
                        : String.format("field [ %s ] was not updated! Something went wrong. Code: %d", data[1], result);
                sendMessage(message);
                
                continue;
            }
            
            server.broadcastMessage(inboundMessage);
        }
    }
}
