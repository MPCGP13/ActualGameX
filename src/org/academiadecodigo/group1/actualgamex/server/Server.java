package org.academiadecodigo.group1.actualgamex.server;

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

    private ServerSocket socket;
    private ExecutorService service;
    private final List<UserHandler> users;
    private boolean connected;
    private int connections;
    private GameLogic gameLogic;

    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        users = Collections.synchronizedList(new LinkedList<>());
        service = Executors.newCachedThreadPool();
        connections = 1;
    }

    @Override
    public void run() {

        connected = true;

        while (connections < MAXIMUM_CLIENTS+1) {
            waitConnection(connections);
            connections++;
        }

        gameLogic = new GameLogic(this);
        gameLogic.init();

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
        System.out.println("REMOVING!");
        users.remove(client);
        connections--;
    }

    public boolean isConnected() {
        return connected;
    }

    public List<UserHandler> getUsers() {
        return users;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }
}

