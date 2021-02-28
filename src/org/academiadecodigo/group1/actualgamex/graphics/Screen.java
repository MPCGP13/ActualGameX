package org.academiadecodigo.group1.actualgamex.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Screen {

    private JFrame frame;
    private final String[] playerPaths = {"resources/player_01.png", "resources/player_02.png", "resources/player_03.png", "resources/player_04.png"};
    private int quadrant;
    private JLayeredPane layer1;
    private JLabel backgroundImg;
    private JLabel titleImg;
    private JLabel playerImg;
    private JButton button;

    public Screen(String title, int quadrant) {
        this.quadrant = quadrant;
        drawScreen();

    }

    public void drawScreen() {

        layer1 = new JLayeredPane();
        frame = new JFrame();
        frame.setSize(1415, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(layer1, 0);

        backgroundImg = null;
        titleImg = null;
        playerImg = null;
        button = null;
        try {
            button = new JButton(new ImageIcon(ImageIO.read(new File("resources/mute.png"))));
            backgroundImg = new JLabel(new ImageIcon(ImageIO.read(new File("resources/background.png"))));
            titleImg = new JLabel(new ImageIcon(ImageIO.read(new File("resources/title.png"))));
            playerImg = new JLabel(new ImageIcon(ImageIO.read(new File(playerPaths[quadrant-1]))));
        } catch (IOException e) {
            e.printStackTrace();
        }


        button.setBounds(1350,18,42,43);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        layer1.add(button);
        titleImg.setBounds(398,149, 405, 302);
        layer1.add(titleImg);
        playerImg.setBounds(1200, 10, 68, 64);
        layer1.add(playerImg);
        backgroundImg.setBounds(0,0,1400,600);
        layer1.add(backgroundImg);


        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public JFrame getFrame() {
        return frame;
    }
}
