package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.user.UserTimer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Screen {

    private JFrame frame;
    private final String[] playerPaths = {"resources/player_01.png", "resources/player_02.png", "resources/player_03.png", "resources/player_04.png"};
    private UserGraphics userGraphics;
    private JLayeredPane layer_background;
    private JLayeredPane layer_init;
    private JLayeredPane layer_text;
    private JLayeredPane layer_counter;
    private JLayeredPane layer_win;
    private JLayeredPane layer_lose;
    private JLayeredPane layer_voting;
    private JLabel background_Img;
    private JLabel init_titleImg;
    private JLabel background_PlayerImg;
    private JButton background_button;
    private JLabel word;
    private JLabel counter;
    private JLabel win;
    private JLabel lose;
    private JButton[] votePlayers;

    public Screen(String title, UserGraphics userGraphics) {
        this.userGraphics = userGraphics;
        drawScreen();

    }

    public Screen(String title) {
        this.userGraphics = userGraphics;
        drawScreen();

    }

    public void drawScreen() {

        layer_background = new JLayeredPane();
        layer_init = new JLayeredPane();
        layer_text = new JLayeredPane();
        layer_counter = new JLayeredPane();
        layer_win = new JLayeredPane();
        layer_lose = new JLayeredPane();
        layer_voting = new JLayeredPane();

        frame = new JFrame();

        frame.setSize(1415, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // COUNTER -----------------------------------------

        frame.add(layer_counter, 0);

        counter = new JLabel("");
        counter.setFont(new Font("Verdana", Font.PLAIN, 34));
        counter.setBounds(1258, 280, 200, 40);
        layer_counter.add(counter);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // START ----------------------------------------------

        frame.add(layer_text, 1);

        word = new JLabel("");
        word.setFont(new Font("Verdana", Font.PLAIN, 26));
        word.setBounds(1215, 530, 200, 40);
        layer_text.add(word);

        frame.setVisible(true);

        // INIT -------------------------------------------------

        frame.add(layer_init, 2);
        init_titleImg = null;

        try {
            init_titleImg = new JLabel(new ImageIcon(ImageIO.read(new File("resources/title.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        init_titleImg.setBounds(400,140, 405, 302);
        layer_init.add(init_titleImg);

        frame.setVisible(true);

        // BACKGROUND --------------------------------------

        frame.add(layer_background, 3);

        background_Img = null;
        background_PlayerImg = null;
        background_button = null;

        try {
            background_button = new JButton(new ImageIcon(ImageIO.read(new File("resources/mute.png"))));
            background_Img = new JLabel(new ImageIcon(ImageIO.read(new File("resources/background.png"))));
            init_titleImg = new JLabel(new ImageIcon(ImageIO.read(new File("resources/title.png"))));
            background_PlayerImg = new JLabel(new ImageIcon(ImageIO.read(new File(playerPaths[userGraphics.getQuadrant()-1]))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        background_button.setBounds(1350,18,42,43);
        background_button.setContentAreaFilled(false);
        background_button.setBorderPainted(false);
        layer_background.add(background_button);

        background_PlayerImg.setBounds(1200, 10, 68, 64);
        layer_background.add(background_PlayerImg);

        background_Img.setBounds(0,0,1400,600);
        layer_background.add(background_Img);

        frame.setVisible(true);

    }

    public void start() {

        System.out.println("TEST START");

        frame.remove(layer_init);
        frame.revalidate();
        frame.repaint();

        word.setText(/* userGraphics.getGameWord()*/"MERDinhas");
        word.setText(/* userGraphics.getGameWord()*/"MERDinhas");
        word.setText(/* userGraphics.getGameWord()*/"MERDinhas");
        timerInit(60);
    }

    public void vote() {

        // VOTING -----------------------------------------

        frame.add(layer_voting, 0);

        votePlayers = new JButton[4];


        for (int i = 0; i < votePlayers.length; i++) {

            votePlayers[i] = new JButton("J'accuse Fake Dudu!");

            if (i < 2 ) {
                votePlayers[i].setBounds(180 + 600 * i, 130, 300, 30);
            } else {
                votePlayers[i].setBounds(180 + 600 * (i - 2), 430, 300, 30);
            }

            votePlayers[i].setFont(new Font("Verdana", Font.PLAIN, 20));
            votePlayers[i].addActionListener(new ButtonAction((i+1), userGraphics));

            layer_voting.add(votePlayers[i]);
        }

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void win() {

        frame.remove(layer_voting);
        frame.revalidate();
        frame.repaint();

        // WIN -----------------------------------------

        frame.add(layer_win, 0);

        win = new JLabel("Fake Dudu won!");
        win.setFont(new Font("Verdana", Font.BOLD, 70));
        win.setBounds(250, 0, 700, 600);
        layer_win.add(win);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void lost() {

        frame.remove(layer_voting);
        frame.revalidate();
        frame.repaint();

        // LOST -----------------------------------------

        frame.add(layer_win, 0);

        win = new JLabel("Fake Dudu lost!");
        win.setFont(new Font("Verdana", Font.BOLD, 68));
        win.setBounds(250, 0, 700, 600);
        layer_win.add(win);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void timerInit(int seconds) {

        UserTimer timer = new UserTimer(seconds, this);
        Thread timerThread = new Thread(timer);
        timerThread.start();
    }

    public JLabel getCounter() {
        return counter;
    }
    public JFrame getFrame() {
        return frame;
    }

}
