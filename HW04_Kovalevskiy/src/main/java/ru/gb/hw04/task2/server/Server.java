package ru.gb.hw04.task2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    
    protected ExecutorService executorService;
    private ServerSocket serverSocket;
    private final List<ClientHandler> loggedUser;
    private final AuthService authService;
    
    public Server() {
        authService = new AuthService();
        loggedUser = new ArrayList<>();
        
        try {
            serverSocket = new ServerSocket(8888);
            executorService = Executors.newSingleThreadExecutor(); /* Executors.newFixedThreadPool (poolSize) */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                executorService.execute(new ClientHandler(this, serverSocket.accept()));
            }
        } catch (IOException ex) {
            executorService.shutdown();
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
