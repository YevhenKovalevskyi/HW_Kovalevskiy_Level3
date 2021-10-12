package ru.gb.hw04.task2.server;

import ru.gb.hw04.task2.database.entities.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    
            server.executorService.execute(() -> {
                try {
                    doAuthentication();
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection(socket);
                }
            });
            server.executorService.shutdown();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }

    private void closeConnection(Socket socket) {
        server.unsubscribe(this);
        server.broadcastMessage(String.format("User [%s] is out.", name), false);

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
        sendMessage("Greeting you in the Outstanding Chat.", false);
        sendMessage("Please do authentication. Template is: -auth [login] [password]", false);
        sendMessage("-----", false);

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
                        sendMessage("AUTH OK.", false);
                        sendMessage(String.format("Welcome, %s!", name), false);
                        sendMessage("-----", false);
                        server.broadcastMessage(String.format("User [ %s ] entered chat.", name), false);
                        server.subscribe(this);
    
                        loadHistory();
                        
                        return;
                    } else {
                        sendMessage("Current user is already logged in", false);
                    }
                } else {
                    sendMessage("Invalid credentials.", false);
                }
            } else {
                sendMessage("Invalid auth operation", false);
            }
        }
    }

    public void sendMessage(String outboundMessage, boolean logging) {
        try {
            out.writeUTF(outboundMessage);

            if (logging) {
                HistoryService.writeHistoryLog(user.getLogin(), outboundMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();
            
            if (inboundMessage.equals("-exit")) {
                String date = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss").format(LocalDateTime.now());
                HistoryService.writeHistoryLog(user.getLogin(), String.format("--- %s ---", date));
                break;
            }
    
            if (inboundMessage.startsWith("-update")) {
                String[] data = inboundMessage.split("\\s");
                int result = server.getAuthService().updateUserData(user, data[1], data[2]);
                
                String message = (result == 1)
                        ? String.format("field [ %s ] was updated!", data[1])
                        : String.format("field [ %s ] was not updated! Something went wrong. Code: %d", data[1], result);
                sendMessage(message, false);
                
                continue;
            }
            
            server.broadcastMessage(String.format("[%s]: %s", name, inboundMessage), true);
        }
    }
    
    private void loadHistory() {
        List<String> logMessagesList = HistoryService.readHistoryLog(user.getLogin());
        
        if (logMessagesList.size() > 0) {
            StringBuilder logMessagesSB = new StringBuilder();
            logMessagesList.forEach(message -> logMessagesSB.append(message).append("\n"));
            sendMessage(logMessagesSB.toString(), false);
        }
    }
}
