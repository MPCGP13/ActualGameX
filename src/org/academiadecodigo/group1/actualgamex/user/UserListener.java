package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.server.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserListener {

    private final Socket socket;
    private final User user;
    private BufferedReader in;
    private CopyOnWriteArrayList<String> quadrant1;
    private CopyOnWriteArrayList<String> quadrant2;
    private CopyOnWriteArrayList<String> quadrant3;
    private CopyOnWriteArrayList<String> quadrant4;


    public UserListener(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
        quadrant1 = new CopyOnWriteArrayList<>();
        quadrant2 = new CopyOnWriteArrayList<>();
        quadrant3 = new CopyOnWriteArrayList<>();
        quadrant4 = new CopyOnWriteArrayList<>();
    }


    public void listen () {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int quadrant = Integer.parseInt(in.readLine());
            user.getUserGraphics().setQuadrant(quadrant);

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

            String[] splitedMsg = message.split(" ");

            switch (splitedMsg[0]) {
                case Messages.START_GAME:
                    user.getUserGraphics().setGameStage(Messages.START_GAME);
                    user.getUserGraphics().setGameWord(splitedMsg[1]);
                    break;
                case Messages.VOTE_TIME:
                    user.getUserGraphics().setGameStage(Messages.VOTE_TIME);
                    break;
                case Messages.END_GAME:
                    user.getUserGraphics().setGameStage(Messages.END_GAME);
                    user.getUserGraphics().setWinner(splitedMsg[1]);
                    user.getUserGraphics().setGameWord(splitedMsg[2]);
                    break;
            }

        } else {
            String[] splitedMsg = message.split(":");

            int quadrant = Integer.parseInt(splitedMsg[0]);

            switch (quadrant) {
                case 1:
                    quadrant1.add(splitedMsg[1]);
                    break;
                case 2:
                    quadrant2.add(splitedMsg[1]);
                    break;
                case 3:
                    quadrant3.add(splitedMsg[1]);
                    break;
                default:
                    quadrant4.add(splitedMsg[1]);
                    break;
            }
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

    public CopyOnWriteArrayList<String> getOtherCoordBuffer(int quadrant) {
        switch (quadrant) {
            case 1:
                return quadrant1;
            case 2:
                return quadrant2;
            case 3:
                return quadrant3;
            default:
                return quadrant4;
        }
    }

}
