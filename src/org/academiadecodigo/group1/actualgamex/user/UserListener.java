package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.server.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserListener {

    private final Socket socket;
    private final User user;
    private BufferedReader in;
    private CopyOnWriteArrayList<String> otherCoordBuffer;
    // private final ExecutorService coordinatesThreads = Executors.newFixedThreadPool(5) ;

    public UserListener(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
        otherCoordBuffer = new CopyOnWriteArrayList<>();
    }


    public void listen () {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {
            String message = in.readLine();

            if (message == null) {
                System.out.println("Server closed the connection.");
            }

            filter(message);
            }

        } catch (IOException ex) {
            System.out.println("Receiving error: " + ex.getMessage());

        } finally {
            System.out.println("LISTENER - FINALLY");
            close();
        }

    }


    private void filter (String message) {

        if (message.startsWith(Messages.COMMAND_IDENTIFIER)) {
            // ?!?!??!?!?!?!?!?!?!?!?!?

        } else {
            otherCoordBuffer.add(message);

        }
    }

    private void close() {

        System.out.println("USER_LISTENER: I'm closing...");

        try {
            in.close();
            user.stop();
        }
        catch (IOException ex) {
            System.out.println("Receiving error: " + ex.getMessage());
        }

    }

    public CopyOnWriteArrayList<String> getOtherCoordBuffer() {
        return otherCoordBuffer;
    }

}
