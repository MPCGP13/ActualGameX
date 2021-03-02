package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.graphics.UserGraphics;
import org.academiadecodigo.group1.actualgamex.server.Messages;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserListener {

    private final Socket socket;
    private final User user;
    private final UserGraphics userGraphics;
    private BufferedReader in;
    private HashMap <Integer, CopyOnWriteArrayList<String>> opponentsPaintingBuffer;

    public UserListener(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
        userGraphics = user.getUserGraphics();
        opponentsPaintingBuffer = new HashMap<>();
        initOpponentsPaintingBuffer();
    }

    /**
     * Method that adds 4 CopyOnWriteArrayList to the initOpponentsPaintingBuffer.
     */
    public void initOpponentsPaintingBuffer() {
        for (int i = 1; i < 5; i++) {
            opponentsPaintingBuffer.put(i, new CopyOnWriteArrayList<>());
        }
    }

    /**
     * Method that listens to the server and filters the received messages.
     */
    public void listen () {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // This first message form server gives this user an ID
            int userID = Integer.parseInt(in.readLine());
            userGraphics.setUserID(userID);

            // The messages received during the rest of the game
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

    /**
     * Method that
     * @param message
     */
    private void filter (String message) {
        if (message.startsWith(Messages.COMMAND_IDENTIFIER)) {

            String[] splitedMsg = message.split(" ");


            switch (splitedMsg[0]) {
                case Messages.START_GAME:
                    userGraphics.setGameStage(Messages.START_GAME);
                    userGraphics.setGameWord(splitedMsg[1]);
                    break;
                case Messages.VOTE_TIME:
                    userGraphics.setGameStage(Messages.VOTE_TIME);
                    break;
                case Messages.END_GAME:
                    userGraphics.setGameStage(Messages.END_GAME);
                    userGraphics.setWinner(splitedMsg[1]);
                    userGraphics.setGameWord(splitedMsg[2]);
                    break;
            }

        } else {
            String[] splitedMsg = message.split(":");

            int userID = Integer.parseInt(splitedMsg[0]);

            opponentsPaintingBuffer.get(userID).add(splitedMsg[1]);
        }
    }

    /**
     * Method that closes BufferedReader and user socket.
     */
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

    /**
     * Method that returns a specific opponent painting buffer
     * @param userID
     * @return
     */
    public CopyOnWriteArrayList<String> getOpponentPaintingBuffer(int userID) {
        return opponentsPaintingBuffer.get(userID);
    }

}
