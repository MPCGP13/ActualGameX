package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.server.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserListener implements Runnable {

    private final Socket socket;
    private final User user;
    private BufferedReader in;
    private CopyOnWriteArrayList<String> otherCoordBuffer;
    private final ExecutorService coordinatesThreads = Executors.newFixedThreadPool(5) ;

    public UserListener(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
        otherCoordBuffer = new CopyOnWriteArrayList<>();
    }

    @Override
    public void run() { listen(); }

    private void listen () {

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
            close();
        }

    }


    private void filter (String message) {

        if (message.startsWith(Messages.COMMAND_IDENTIFIER)) {
            // ?!?!??!?!?!?!?!?!?!?!?!?

        } else {

            otherCoordBuffer.add(message);
            coordinatesThreads.submit(user.getCanvas());

            /* Canvas:
            String[] messageSplit = coordinatesBuffer.remove(0).split(":");
            int getColorID = Integer.parseInt(messageSplit[0]);
            Double x = Double.parseDouble(messageSplit[1].split(",")[0]);
            Double y = Double.parseDouble(messageSplit[1].split(",")[1]);
            */
        }
    }

    private void close() {

        try {
            in.close();
            user.stop();
        }
        catch (IOException ex) {
            System.out.println("Receiving error: " + ex.getMessage());
        }

    }
}
