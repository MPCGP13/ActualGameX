package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.user.Paintor;
import org.academiadecodigo.group1.actualgamex.user.User;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserGraphics implements Runnable {


    private int[] coordenates;
    private Color color;
    private int quadrant;
    private MouseController mouseController;
    private Screen screen;
    public static final int POINTER_SIZE = 10;
    private User user;


    public UserGraphics(User user) {
        coordenates = new int[2];
        this.user = user;
    }

    @Override
    public void run() {
        screen = new Screen();
        mouseController = new MouseController(this, user);
        screen.addMouseMotionListener(mouseController);

        ExecutorService paint = Executors.newFixedThreadPool(3);

        for (int i = 1; i < 5; i++) {
            if(i == quadrant) {
                continue;
            }
            paint.submit(new Paintor(this, user, i));
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



    //SETTERS

    public void setCoordenates(int[] coordenates) {
        this.coordenates = coordenates;
    }

    public void setQuadrant(int quadrant) {
        this.quadrant = quadrant;
    }

    /*public void usersPaint() {
        String[] messageSplit = user.getUserListener().getOtherCoordBuffer().remove(0).split(":");
        System.out.println("Color id:" + messageSplit[0] + "\n" + "Coord: " + messageSplit[1]);
        int quadrant = Integer.parseInt(messageSplit[0]);
        int x = Integer.parseInt(messageSplit[1].split(",")[0]);
        int y = Integer.parseInt(messageSplit[1].split(",")[1]);
        paintDot(x, y, quadrant);
    }*/

    public void paintDot(int x, int y, int quadrant) {

        Graphics g = screen.getGraphics();
        g.setColor(attributeColor(quadrant));
        g.fillOval(x, y, POINTER_SIZE, POINTER_SIZE);

    }
}
