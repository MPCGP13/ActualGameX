package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.server.Messages;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserWriter implements Runnable {

    private final Socket socket;
    private final User user;
    private PrintWriter out;

    public UserWriter(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run() { send(); }

    private void send() {

        try {
            out = new PrintWriter(socket.getOutputStream(), true);

            CopyOnWriteArrayList<String> buff = user.getMyCoordBuffer();

            while (!socket.isClosed()) {

                if(user.getUserGraphics().getGameStage().equals(Messages.VOTE_TIME) && user.getVote() > 0) {
                    out.println(Messages.VOTING + " " + user.getVote());
                }

                if (buff.size() > 0) {
                    out.println(buff.remove(0));

                }

            }

        } catch (IOException ex) {
            System.out.println("Receiving error: " + ex.getMessage());

        } finally {
            close();
        }

    }

    private void close() {

        System.out.println("USER_WRITER: I'm closing...");

        out.close();
        user.stop();


    }
}
