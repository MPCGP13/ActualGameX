package org.academiadecodigo.group1.actualgamex.user;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserWriter implements Runnable {

    private final Socket socket;
    private final User user;
    private BufferedWriter out;

    public UserWriter(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run() { send(); }

    private void send() {

        try {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (!socket.isClosed()) {
                out.write(user.getUserGraphics().getMyCoordBuffer().remove(0));
            }

        } catch (IOException ex) {
            System.out.println("Receiving error: " + ex.getMessage());

        } finally {
            close();
        }

    }

    private void close() {

        try {
            out.close();
            user.stop();
        }
        catch (IOException ex) {
            System.out.println("Receiving error: " + ex.getMessage());
        }

    }
}
