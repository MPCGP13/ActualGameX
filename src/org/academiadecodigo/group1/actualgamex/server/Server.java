package org.academiadecodigo.group1.actualgamex.server;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private static final String DEFAULT_NAME = "Player";
    private static final int MAXIMUM_CLIENTS = 4;
    private static final Color[] COLORS = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA};

    private ServerSocket socket;
    private ExecutorService service;
    private final List<UserHandler> users;
    private boolean connected;

    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        users = Collections.synchronizedList(new LinkedList<>());
        service = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        int connections = 1;

        while (true) {
            waitConnection(connections);
            connections++;
        }
    }

    private void waitConnection(int connections) {
        try {
            Socket clientSocket = socket.accept();

            UserHandler connection = new UserHandler(clientSocket, this, DEFAULT_NAME + connections, connections);
            service.submit(connection);

        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }

    public boolean addClient(UserHandler client) {
        synchronized (users) {

            if (users.size() >= MAXIMUM_CLIENTS) {
                return false;
            }

            broadcast(client.getName() + " " + Messages.JOIN_ALERT, null);
            users.add(client);
            return true;
        }
    }

    public void broadcast(String message, UserHandler sender) {

        synchronized (users) {

            for (UserHandler user : users) {

                if (user != sender) {
                    user.send(message);
                }
            }
        }
    }

    public void remove(UserHandler client) {
        users.remove(client);
    }

    public String listClients() {
        StringBuilder list = new StringBuilder("Connected Clients:\n");

        synchronized (users) {
            for (UserHandler user : users) {
                list.append(user.getName()).append("\n");
            }
        }

        return list.toString();
    }

    public UserHandler getClientByName(String name) {
        synchronized (users) {
            for (UserHandler user : users) {
                if (user.getName().equals(name)) {
                    return user;
                }
            }
        }

        return null;
    }
}

