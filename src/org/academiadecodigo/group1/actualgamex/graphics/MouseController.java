package org.academiadecodigo.group1.actualgamex.graphics;

import java.awt.*;
import java.awt.event.*;


public class MouseController implements MouseMotionListener {

    private int quadrant;
    private Color color;
    private UserGraphics user;

    MouseController(UserGraphics user) {
        this.user = user;
        quadrant = 3;


    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (checkIfPaint(e)) {
            Graphics g = user.getCanvas().getGraphics();
            g.setColor(Color.cyan);
            g.fillOval(e.getX(), e.getY(), 10, 10);
        }
        System.out.println("x:" + e.getX() + " -> y : " + e.getY());

    }



    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("x:" + e.getX() + " -> y : " + e.getY());
        user.setCoordenates(new double[]{e.getX(), e.getY()});
    }


    /**
     * checkIfPaint will return one true boolean on just one quadrant
     *
     * @param e Mouse Event java.awt
     * @return boolean to now if he can paint in the quadrant
     */

    private boolean checkIfPaint(MouseEvent e) {

        switch (quadrant) {
            case 1:
                return (e.getX() > 0 && e.getX() < 600 && e.getY() > 40 && e.getY() < 300);
            case 2:
                return (e.getX() >= 600 && e.getX() < 1200 && e.getY() > 40 && e.getY() < 300);
            case 3:
                return (e.getX() > 0 && e.getX() < 600 && e.getY() >= 300 && e.getY() < 600);
            default:
                return (e.getX() >= 600 && e.getX() < 1200 && e.getY() >= 300 && e.getY() < 600);
        }

    }



}
