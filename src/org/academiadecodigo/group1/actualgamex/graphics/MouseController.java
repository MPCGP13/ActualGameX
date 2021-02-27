package org.academiadecodigo.group1.actualgamex.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MousePaint extends Frame implements MouseMotionListener {

    private int quadrant;
    private Color color;

    MousePaint() {
        quadrant = 3;
        addMouseMotionListener(this);

        setSize(1200, 600);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setVisible(true);
        color =Color.BLUE;

        Graphics text = getGraphics();
        text.drawString("HELLO WORLD", 100, 100);
        text.setColor(Color.BLUE);
        text.setFont (new Font ("Helvetica", Font.BOLD | Font.ITALIC, 20));


    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (checkIfPaint(e)) {
            Graphics g = getGraphics();
            g.setColor(Color.cyan);
            g.fillOval(e.getX(), e.getY(), 10, 10);



        }
        System.out.println("x:" + e.getX() + " -> y : " + e.getY());

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        text.setColor(Color.BLUE);
        text.setFont (new Font ("Helvetica", Font.BOLD | Font.ITALIC, 20));
    }

    public static void main(String[] args) {
        new MousePaint();

    }

    private boolean checkIfPaint(MouseEvent e) {

        switch (quadrant) {
            case 0:
                return (e.getX() > 0 && e.getX() < 600 && e.getY() > 40 && e.getY() < 300);
            case 1:
                return (e.getX() >= 600 && e.getX() < 1200 && e.getY() > 40 && e.getY() < 300);
            case 2:
                return (e.getX() > 0 && e.getX() < 600 && e.getY() >= 300 && e.getY() < 600);
            default:
                return (e.getX() >= 600 && e.getX() < 1200 && e.getY() >= 300 && e.getY() < 600);
        }

    }
/*

    public Color attributeColor(){
        switch (quadrant){
            case 0:
                return  Color.BLUE;
            case
        }
    }
*/

}
