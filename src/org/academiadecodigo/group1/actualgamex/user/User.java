package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.graphics.UserGraphics;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class User {

    private final Socket socket;
    private final ExecutorService listenThread;
    private final ExecutorService writeThread;
    private final ExecutorService graphicsThread;
    private UserListener userListener;
    private UserWriter userWriter;
    private UserGraphics userGraphics;
    private CopyOnWriteArrayList<String> myPaintingBuffer;
    private int vote = 0;

    public User(String host, int port) throws IOException {
        socket = new Socket(host, port);
        listenThread = Executors.newSingleThreadExecutor();
        writeThread = Executors.newSingleThreadExecutor();
        graphicsThread = Executors.newSingleThreadExecutor();
        myPaintingBuffer = new CopyOnWriteArrayList<>();
    }

    /**
     * This method starts the threads and initiates the graphics and user listener and writer.
     */
    public void start() {

        userGraphics = new UserGraphics(this);
        graphicsThread.submit(userGraphics);

        userListener = new UserListener(socket, this);

        userWriter = new UserWriter(socket, this);
        writeThread.submit(userWriter);

        while (!socket.isClosed()) {
            System.out.println("START - listening...");
            userListener.listen();
        }

    }

    /**
     * This method closes the user threads and socket.
     */
    public void stop() {
        System.out.println("STOPPING!");

        try {
            if (socket != null) {
                System.out.println("Closing the socket");
                socket.close();
                listenThread.shutdown();
                writeThread.shutdown();
                graphicsThread.shutdown();
            }

        } catch (IOException ex) { System.out.println("Error closing connection: " + ex.getMessage()); }
    }


    // GETTERS -------------------------------------------------------------

    public UserGraphics getUserGraphics() {
        return userGraphics;
    }
    public CopyOnWriteArrayList<String> getMyPaintingBuffer() {
        return myPaintingBuffer;
    }
    public UserListener getUserListener() {
        return userListener;
    }
    public int getVote() {
        return vote;
    }


    // SETTERS --------------------------------------------------------------

    public void setVote(int vote) {
        this.vote = vote;
    }
}
