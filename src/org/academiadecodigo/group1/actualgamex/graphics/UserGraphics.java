package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.server.Messages;
import org.academiadecodigo.group1.actualgamex.user.Paintor;
import org.academiadecodigo.group1.actualgamex.user.User;
import org.academiadecodigo.group1.actualgamex.user.UserTimer;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UserGraphics implements Runnable {


    private int[] coordenates;
    private Color color;
    private int quadrant;
    private MouseController mouseController;
    private Screen screen;
    public static final int POINTER_SIZE = 10;
    private User user;
    private String gameStage;
    private String gameWord;
    private String winner;
    private boolean restart;



    public UserGraphics(User user) {
        coordenates = new int[2];
        this.user = user;
        gameStage = "init";
        restart = false;
    }

    @Override
    public void run() {
        try {
            //waiting room + music?

            if(!restart) {
                screen = new Screen("Fake Dudu", quadrant);
            }

            while(!gameStage.equals(Messages.START_GAME)) {
                TimeUnit.SECONDS.sleep(1);
            }

            //remove extras


            //Enable painting
            mouseController = new MouseController(this, user);
            screen.getFrame().addMouseMotionListener(mouseController);

            //Timer init
            timerInit(60);

            //Other players realtime painting
            realTimePainting();

            while(!gameStage.equals(Messages.VOTE_TIME)) {
                TimeUnit.SECONDS.sleep(1);
            }

            //Disable painting

            while(!gameStage.equals("/END_GAME")) {
                TimeUnit.SECONDS.sleep(1);
            }

            switch(winner) {
                case "impostor":
                    // print on screen that Fake artist won
                    //show gameWord
                    System.out.println("FA WON " + gameWord);
                    break;
                case "player":
                    //print on screen that Fake artist was found
                    //show gameWord
                    System.out.println("FA FOUND " + gameWord);
                    break;
            }

            //Restarting in...
            timerInit(15);
            gameStage = "init";
            restart = true;
            run();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method responsible for atributing a Color.java.awt according to User quadrant
     *
     */

    public Color attributeColor(int quadrant){
        switch (quadrant){
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


    //GETTERS
    public Screen getCanvas() {
        return screen;
    }

    public int getQuadrant() {
        return quadrant;
    }

    public String getGameStage() {
        return gameStage;
    }

    //SETTERS

    public void setCoordenates(int[] coordenates) {
        this.coordenates = coordenates;
    }

    public void setQuadrant(int quadrant) {
        this.quadrant = quadrant;
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

    public void paintDot(int x, int y, int quadrant) {

        Graphics g = screen.getFrame().getGraphics();
        g.setColor(attributeColor(quadrant));
        g.fillOval(x, y, POINTER_SIZE, POINTER_SIZE);

    }

    public void timerInit(int seconds) throws InterruptedException {
        UserTimer timer = new UserTimer(seconds);
        Thread timerThread = new Thread(timer);
        timerThread.start();

    }

    public void realTimePainting(){
        ExecutorService paint = Executors.newFixedThreadPool(3);

        for (int i = 1; i < 5; i++) {
            if(i == quadrant) {
                continue;
            }
            paint.submit(new Paintor(this, user, i));
        }
    }

}
