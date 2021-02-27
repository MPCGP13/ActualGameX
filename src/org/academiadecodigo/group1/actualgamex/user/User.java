package org.academiadecodigo.group1.actualgamex.user;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class User {

    private final Socket socket;
    private final ExecutorService listenThread;
    private final ExecutorService writeThread;
    private final Canvas canvas;
    private UserListener listenToServer;
    private UserWriter writeToServer;

    public User(String host, int port) throws IOException {
        socket = new Socket(host, port);
        listenThread = Executors.newSingleThreadExecutor();
        writeThread = Executors.newSingleThreadExecutor();
        canvas = new Canvas();
    }

    public void start() {

        listenToServer = new UserListener(socket, this);
        listenThread.submit(listenToServer);

        writeToServer = new UserWriter(socket, this;
        writeThread.submit(writeToServer);

        try {
            // MOUSE READER!!!!
            // GRAPHICS...

            while (!socket.isClosed()) {
                // waitMessage(reader); MOUSE READER LOGIC
            }

        } catch (IOException e) {
            System.err.println("Error handling socket connection: " + e.getMessage());
        }
    }

    public void stop() {

        try {
            if (socket != null) {
                System.out.println("Closing the socket");
                socket.close();
                listenThread.shutdown();
                writeThread.shutdown();
                // System.exit(0);  ????
            }

        } catch (IOException ex) { System.out.println("Error closing connection: " + ex.getMessage()); }

    }

    public Canvas getCanvas() {
        return canvas;
    }
}
