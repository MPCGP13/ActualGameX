package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.Canvas;
import org.academiadecodigo.group1.actualgamex.graphics.MouseController;
import org.academiadecodigo.group1.actualgamex.graphics.UserGraphics;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class User {

    private final Socket socket;
    private final ExecutorService listenThread;
    private final ExecutorService writeThread;
    private final ExecutorService graphicsThread;
    private UserListener listenToServer;
    private UserWriter writeToServer;
    private UserGraphics userGraphics;

    public User(String host, int port) throws IOException {
        socket = new Socket(host, port);
        listenThread = Executors.newSingleThreadExecutor();
        writeThread = Executors.newSingleThreadExecutor();
        graphicsThread = Executors.newSingleThreadExecutor();
        userGraphics = new UserGraphics(3);

    }

    public void start() {

        listenToServer = new UserListener(socket, this);
        listenThread.submit(listenToServer);

        writeToServer = new UserWriter(socket, this);
        writeThread.submit(writeToServer);
        userGraphics = new UserGraphics(2);
        graphicsThread.submit(userGraphics);


       /* try {
            // MOUSE READER!!!!
            // GRAPHICS...

            while (!socket.isClosed()) {
                // waitMessage(reader); MOUSE READER LOGIC
            }

        } catch (IOException e) {
            System.err.println("Error handling socket connection: " + e.getMessage());
        }*/
    }

    public void stop() {

        try {
            if (socket != null) {
                System.out.println("Closing the socket");
                socket.close();
                listenThread.shutdown();
                writeThread.shutdown();
                graphicsThread.shutdown();
                // System.exit(0);  ????
            }

        } catch (IOException ex) { System.out.println("Error closing connection: " + ex.getMessage()); }

    }


    public UserWriter getWriteToServer() {
        return writeToServer;
    }

    public UserGraphics getUserGraphics() {
        return userGraphics;
    }

    public UserListener getListenToServer() {
        return listenToServer;
    }
}
