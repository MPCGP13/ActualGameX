package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.user.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Screen {

    private static final String FONT_TYPE = "Verdana";
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_WIDTH = 1400;
    private static final int SCREEN_SIDEBAR = 200;

    private final UserGraphics userGraphics;
    private final JFrame frame = new JFrame();;
    private final String[] playerPaths = {"resources/player_01.png",
            "resources/player_02.png",
            "resources/player_03.png",
            "resources/player_04.png"};

    private JLayeredPane layerBackground;
    private JLayeredPane layerInit;
    private JLayeredPane layerWord;
    private JLayeredPane layerCounter;
    private JLayeredPane layerResult;
    private JLayeredPane layerVoting;
    private JLayeredPane layerStandby;
    private JLayeredPane layerEnd;

    private JLabel backgroundImg;
    private JLabel initTitleImg;
    private JLabel backgroundPlayerImg;
    private JLabel word;
    private JLabel counter;
    private JLabel result;
    private JLabel end;
    private JLabel standby;

    private JButton[] votePlayers;
    private JButton restart;

    public Screen(String title, UserGraphics userGraphics) {
        this.userGraphics = userGraphics;
        initLayers();
        initScreen();
    }

    public void initLayers() {
        layerBackground = new JLayeredPane();
        layerInit = new JLayeredPane();
        layerWord = new JLayeredPane();
        layerCounter = new JLayeredPane();
        layerResult = new JLayeredPane();
        layerVoting = new JLayeredPane();
        layerVoting = new JLayeredPane();
        layerStandby = new JLayeredPane();
        layerEnd = new JLayeredPane();

        end = new JLabel("");
        standby = new JLabel("");
        counter = new JLabel("");
        word = new JLabel("");
        result = new JLabel("");
        backgroundImg = null;
        initTitleImg = null;
        backgroundPlayerImg = null;

        restart = new JButton();
    }

    /**
     * Method that initiates the screen and launches the first images.
     */
    public void initScreen() {
        // Frame
        frame.setSize(SCREEN_WIDTH + 15, SCREEN_HEIGHT + 35);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Counter
        frame.add(layerCounter, 0);
        paintWord(layerCounter, counter, "", 34, new int[]{1258, 280, 200, 40});
        framePaint();

        // Start
        frame.add(layerWord, 1);
        paintWord(layerWord, word, "", 24, new int[]{1215, 530, 200, 40});
        frame.setVisible(true);

        // Init
        frame.add(layerInit, 2);
        paintImage(layerInit, initTitleImg, "resources/title.png", new int[] {400, 140, 405, 302});
        frame.setVisible(true);

        // Background
        frame.add(layerBackground, 3);
        paintImage(layerBackground, backgroundPlayerImg, playerPaths[userGraphics.getUserID()-1], new int[] {1200, 10, 68, 64});
        paintImage(layerBackground, backgroundImg, "resources/background.png", new int[] {0,0,1400,600});
        frame.setVisible(true);
    }

    /**
     * Method that refreshes the screen to the start mode.
     */
    public void start() {
        remove(layerInit);

        word.setText(userGraphics.getGameWord());
        word.setText(userGraphics.getGameWord());
        word.setText(userGraphics.getGameWord());
        timerInit(30);
    }

    /**
     * Method that refreshes the screen to the vote mode.
     */
    public void vote() {

        frame.add(layerVoting, 0);
        votePlayers = new JButton[User.getMaximumPlayers()];
        for (int i = 0; i < votePlayers.length; i++) {
            votePlayers[i] = new JButton("J'accuse Fake Dudu!");

            if (i < User.getMaximumPlayers() / 2) {
                votePlayers[i].setBounds((180 + ((SCREEN_WIDTH - SCREEN_SIDEBAR) / 2) * i), 130, (SCREEN_HEIGHT / 2), 30);
            } else {
                votePlayers[i].setBounds((180 + ((SCREEN_WIDTH - SCREEN_SIDEBAR) / 2) * (i - 2)), 430, (SCREEN_HEIGHT / 2), 30);
            }

            votePlayers[i].setFont(new Font(FONT_TYPE, Font.PLAIN, 20));
            votePlayers[i].addActionListener(new ButtonVote((i+1), userGraphics));

            layerVoting.add(votePlayers[i]);
        }

        framePaint();
    }

    /**
     * Method that refreshes the screen to the result screen mode
     */
    public void resultScreen (String message) {
        remove(layerVoting);

        frame.add(layerResult, 0);
        paintWord(layerResult, result, message, 68, new int[]{250, 0, 700, 600});
        framePaint();
    }

    /**
     * Method that refreshes the screen to the standby screen mode
     */
    public void standbyScreen() {
        remove(layerResult);

        frame.add(layerStandby, 0);

        if(userGraphics.getUserID() == 1) {

            restart = new JButton("Play Again");
            restart.setBounds(((SCREEN_WIDTH - SCREEN_SIDEBAR) / 2 - 125), 285, 250, 30);
            restart.setFont(new Font(FONT_TYPE, Font.PLAIN, 20));
            restart.addActionListener(new ButtonRestart(0, userGraphics));

            layerStandby.add(restart);

        } else {
            paintWord(layerStandby, standby, "Waiting for host...", 35, new int[]{(SCREEN_WIDTH - SCREEN_SIDEBAR) / 2 - 150, 0, SCREEN_WIDTH, SCREEN_HEIGHT});

        }

        framePaint();
    }

    /**
     * Method that refreshes the screen to the end screen mode
     */
    public void endScreen (String message) {
        remove(layerStandby);

        frame.add(layerEnd, 0);
        paintWord(layerEnd, end, message, 58, new int[]{250, 0, 700, 600});
        framePaint();
    }

    /**
     * Method that refreshes adds an image to a layer.
     */
    private void paintImage(JLayeredPane layer, JLabel label, String imagePath, int[] bounds) {
        try {
            label = new JLabel(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath))));
           // label = new JLabel(new ImageIcon(ImageIO.read(new File(imagePath))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        label.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        layer.add(label);
    }

    /**
     * Method that resets the frame.
     */
    public void reset() {
        frame.getContentPane().removeAll();
        frame.repaint();

        initLayers();
        initScreen();
    }

    /**
     * Method that refreshes adds a word to a layer.
     */
    private void paintWord(JLayeredPane layer, JLabel label, String labelText, int fontSize, int[] bounds) {
        label.setText(labelText);
        label.setFont(new Font(FONT_TYPE, Font.PLAIN, fontSize));
        label.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        layer.add(label);
    }

    /**
     * Method that paints the frame.
     */
    private void framePaint() {
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Method that paints the frame.
     */
    private void remove(JLayeredPane layer) {
        frame.remove(layer);
        //frame.revalidate();
        frame.repaint();
    }

    /**
     * Method that creates a new timer & counter.
     */
    public void timerInit(int seconds) {
        UserTimer timer = new UserTimer(seconds, this);
        Thread timerThread = new Thread(timer);
        timerThread.start();
    }


    // GETTERS ----------------------------------------------------------------------

    public JLabel getCounter() {
        return counter;
    }
    public JFrame getFrame() {
        return frame;
    }
}