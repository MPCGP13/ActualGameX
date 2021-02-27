package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.Canvas;
import org.academiadecodigo.group1.actualgamex.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserGraphics implements Runnable {


    private int[] coordenates;
    private Color color;
    private int quadrant;
    private MouseController mouseController;
    private org.academiadecodigo.group1.actualgamex.Canvas canvas;
    private CopyOnWriteArrayList<String> myCoordBuffer;
    public static final int POINTER_SIZE = 10;
    private User user;


    public UserGraphics(int quadrant) {

        canvas = new org.academiadecodigo.group1.actualgamex.Canvas();
        canvas.init();
        coordenates = new int[2];
        color = attributeColor(quadrant);
        mouseController = new MouseController(this);
        canvas.addMouseMotionListener(mouseController);

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

    public Canvas getCanvas() {
        return canvas;
    }
    public CopyOnWriteArrayList<String> getMyCoordBuffer() {
        return myCoordBuffer;
    }

    //SETTERS


    public void setCoordenates(int[] coordenates) {
        this.coordenates = coordenates;
    }

    @Override
    public void run() {

        String[] messageSplit = user.getListenToServer().getOtherCoordBuffer().remove(0).split(":");
        int quadrant = Integer.parseInt(messageSplit[0]);
        int x = Integer.parseInt(messageSplit[1].split(",")[0]);
        int y = Integer.parseInt(messageSplit[1].split(",")[1]);
        paintDot(x, y, quadrant);

    }

    public void paintDot(int x, int y, int quadrant) {

        Graphics g = canvas.getGraphics();
        g.setColor(attributeColor(quadrant));
        g.fillOval(x, y, POINTER_SIZE, POINTER_SIZE);
    }
}
