package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.Messages;

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

    /**
     * Method that sends messages to the server.
     */
    private void send() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            CopyOnWriteArrayList<String> myPaintingBuffer = user.getMyPaintingBuffer();

            while (!socket.isClosed()) {

                // Restart
                if (user.getUserGraphics().isRestarting()) {
                    out.println(Messages.RESTART_GAME);
                    user.getUserGraphics().setRestarting(false);
                }

                // Sends vote
                if (user.getUserGraphics().getGameStage().equals(Messages.VOTE_TIME) && user.getVote() != 0) {
                    out.println(Messages.VOTING + " " + user.getVote());
                    user.getUserGraphics().setGameStage("");
                }

                // Sends painted dots
                if (myPaintingBuffer.size() > 0) {
                    out.println(myPaintingBuffer.remove(0));
                }
            }

        } catch (IOException ex) {
            System.out.println("Receiving error: " + ex.getMessage());

        } finally {
            close();
        }
    }

    /**
     * Method that closes BufferedReader and user socket.
     */
    private void close() {
        System.out.println("USER_WRITER: I'm closing...");

        out.close();
        user.stop();
    }
}
