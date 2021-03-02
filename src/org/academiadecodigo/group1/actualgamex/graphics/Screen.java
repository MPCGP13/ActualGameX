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

    private JLayeredPane layer_background;
    private JLayeredPane layer_init;
    private JLayeredPane layer_word;
    private JLayeredPane layer_counter;
    private JLayeredPane layer_end;
    private JLayeredPane layer_voting;
    private JLabel background_Img;
    private JLabel init_titleImg;
    private JLabel background_PlayerImg;
    private JLabel word;
    private JLabel counter;
    private JLabel end;
    private JButton[] votePlayers;

    public Screen(String title, UserGraphics userGraphics) {
        this.userGraphics = userGraphics;
        layer_background = new JLayeredPane();
        layer_init = new JLayeredPane();
        layer_word = new JLayeredPane();
        layer_counter = new JLayeredPane();
        layer_end = new JLayeredPane();
        layer_voting = new JLayeredPane();
        counter = new JLabel("");
        word = new JLabel("");
        end = new JLabel("");
        background_Img = null;
        init_titleImg = null;
        background_PlayerImg = null;

        initScreen();
    }

    /**
     * Method that initiates the screen and lauches the first images.
     */
    public void initScreen() {
        // Frame
        frame.setSize(SCREEN_WIDTH + 15, SCREEN_HEIGHT + 35);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Counter
        frame.add(layer_counter, 0);
        paintWord(layer_counter, counter, "", 34, new int[]{1258, 280, 200, 40});
        framePaint();

        // Start
        frame.add(layer_word, 1);
        paintWord(layer_word, word, "", 24, new int[]{1215, 530, 200, 40});
        frame.setVisible(true);

        // Init
        frame.add(layer_init, 2);
        paintImage(layer_init, init_titleImg, "resources/title.png", new int[] {400, 140, 405, 302});
        frame.setVisible(true);

        // Background
        frame.add(layer_background, 3);
        paintImage(layer_background, background_PlayerImg, playerPaths[userGraphics.getUserID()-1], new int[] {1200, 10, 68, 64});
        paintImage(layer_background, background_Img, "resources/background.png", new int[] {0,0,1400,600});
        frame.setVisible(true);
    }

    /**
     * Method that refreshes the screen to the start mode.
     */
    public void start() {
        frame.remove(layer_init);
        frame.revalidate();
        frame.repaint();
        System.out.println("Starting Word");

        word.setText(userGraphics.getGameWord());
        word.setText(userGraphics.getGameWord());
        word.setText(userGraphics.getGameWord());
        timerInit(30);
    }

    /**
     * Method that refreshes the screen to the vote mode.
     */
    public void vote() {
        frame.add(layer_voting, 0);

        votePlayers = new JButton[User.getMaximumPlayers()];

        for (int i = 0; i < votePlayers.length; i++) {
            votePlayers[i] = new JButton("J'accuse Fake Dudu!");

            if (i < User.getMaximumPlayers() / 2) {
                votePlayers[i].setBounds((180 + ((SCREEN_WIDTH - SCREEN_SIDEBAR) / 2) * i), 130, (SCREEN_HEIGHT / 2), 30);
            } else {
                votePlayers[i].setBounds((180 + ((SCREEN_WIDTH - SCREEN_SIDEBAR) / 2) * (i - 2)), 430, (SCREEN_HEIGHT / 2), 30);
            }

            votePlayers[i].setFont(new Font(FONT_TYPE, Font.PLAIN, 20));
            votePlayers[i].addActionListener(new ButtonAction((i+1), userGraphics));

            layer_voting.add(votePlayers[i]);
        }

        framePaint();
    }

    /**
     * Method that refreshes the screen to the end screen mode
     */
    public void endScreen (String message) {
        frame.remove(layer_voting);
        frame.revalidate();
        frame.repaint();

        frame.add(layer_end, 0);
        paintWord(layer_end, end, message, 68, new int[]{250, 0, 700, 600});
        framePaint();
    }

    /**
     * Method that refreshes adds an image to a layer.
     */
    private void paintImage(JLayeredPane layer, JLabel label, String imagePath, int[] bounds) {
        try {
            label = new JLabel(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        label.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        layer.add(label);
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