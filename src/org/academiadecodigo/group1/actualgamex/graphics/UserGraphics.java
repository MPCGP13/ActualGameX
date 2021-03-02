package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.server.Messages;
import org.academiadecodigo.group1.actualgamex.user.Paintor;
import org.academiadecodigo.group1.actualgamex.user.User;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UserGraphics implements Runnable {

    public static final int POINTER_SIZE = 10;
    private int[] coordinates;
    private int userID;
    private MouseController mouseController;
    private Screen screen;
    private User user;
    private String gameStage;
    private String gameWord;
    private String winner;
    private boolean restart;

    public UserGraphics(User user) {
        coordinates = new int[2];
        this.user = user;
        gameStage = "init";
        restart = false;
    }

    /**
     * Run method responds to the server's GameLogic.
     */
    @Override
    public void run() {

        try {
            if(!restart) {
                screen = new Screen("Fake Dudu", this);
            }

            // Wait for all players to enter the game
            while(!gameStage.equals(Messages.START_GAME)) {
                TimeUnit.SECONDS.sleep(1);
            }

            // Remove extras
            screen.start();

            // Enable painting
            mouseController = new MouseController(this, user);
            screen.getFrame().addMouseMotionListener(mouseController);

            // Other players realtime painting
            opponentsPainting();

            while(!gameStage.equals(Messages.VOTE_TIME)) {
                TimeUnit.SECONDS.sleep(1);
            }

            // Disable painting
            screen.vote();

            // Wait for all players to vote
            while(!gameStage.equals("/END_GAME")) {
                TimeUnit.SECONDS.sleep(1);
            }

            // Present end screen
            switch(winner) {
                case "FakeDudu":
                    screen.win();
                    break;
                case "player":
                    screen.lost();
                    break;
            }

            // Restarting in...
            //TimeUnit.SECONDS.sleep(15);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method responsible for attributing a Color.java.awt according to User ID
     *
     */
    public Color attributeColor(int userID){
        switch (userID){
            case 1:
                return Color.BLUE;
            case 2:
                return Color.RED;
            case 3:
                return Color.YELLOW;
            default:
                return Color.GREEN;
        }
    }

    /**
     * Method that creates a Thread and a Paintor for each opponents.
     */
    public void opponentsPainting(){
        ExecutorService paint = Executors.newFixedThreadPool(3);

        for (int i = 1; i < 5; i++) {
            if(i == userID) {
                continue;
            }
            paint.submit(new Paintor(this, user, i));
        }
    }

    /**
     * Method responsible for each dot painted by the players
     * @param x
     * @param y
     * @param userID
     */
    public void paintDot(int x, int y, int userID) {
        Graphics g = getScreen().getFrame().getGraphics();
        g.setColor(attributeColor(userID));
        g.fillOval(x, y, POINTER_SIZE, POINTER_SIZE);
    }


    //GETTERS -----------------------------------------------------------

    public int getUserID() {
        return userID;
    }
    public String getGameStage() {
        return gameStage;
    }
    public String getGameWord() {
        return gameWord;
    }
    public User getUser() {
        return user;
    }
    public Screen getScreen() {
        return screen;
    }


    // SETTERS -----------------------------------------------------------

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setGameStage(String gameStage) {
        this.gameStage = gameStage;
    }
    public void setGameWord(String gameWord) {
        this.gameWord = gameWord;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }

}
