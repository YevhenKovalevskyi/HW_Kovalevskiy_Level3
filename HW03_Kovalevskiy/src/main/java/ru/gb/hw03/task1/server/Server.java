package ru.gb.hw03.task1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private final List<ClientHandler> loggedUser;
    private final AuthService authService;

    public Server() {
        authService = new AuthService();
        loggedUser = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(8888);
            
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void subscribe(ClientHandler user) {
        loggedUser.add(user);
    }

    public synchronized void unsubscribe(ClientHandler user) {
        loggedUser.remove(user);
    }

    public synchronized boolean isNotUserOccupied(String name) {
        return !isUserOccupied(name);
    }

    public synchronized boolean isUserOccupied(String name) {
        return loggedUser.stream()
                .anyMatch(user -> user.getName().equals(name));
    }

    public synchronized void broadcastMessage(String outboundMessage, boolean logging) {
        loggedUser.forEach(clientHandler -> clientHandler.sendMessage(outboundMessage, logging));
    }
}
