package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.user.User;
import java.awt.*;

public class UserGraphics implements Runnable {


    private int[] coordenates;
    private Color color;
    private int quadrant;
    private MouseController mouseController;
    private Screen screen;
    public static final int POINTER_SIZE = 10;
    private User user;


    public UserGraphics(int quadrant, User user) {

        coordenates = new int[2];
        color = attributeColor(quadrant);
        this.user = user;
    }

    @Override
    public void run() {
        screen = new Screen();
        mouseController = new MouseController(this, user);
        screen.addMouseMotionListener(mouseController);

        while (true) { UsersPaint(); }

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


    //SETTERS

    public void setCoordenates(int[] coordenates) {
        this.coordenates = coordenates;
    }

    public void UsersPaint () {
        String[] messageSplit = user.getListenToServer().getOtherCoordBuffer().remove(0).split(":");
        int quadrant = Integer.parseInt(messageSplit[0]);
        int x = Integer.parseInt(messageSplit[1].split(",")[0]);
        int y = Integer.parseInt(messageSplit[1].split(",")[1]);
        paintDot(x, y, quadrant);
    }

    public void paintDot(int x, int y, int quadrant) {

        Graphics g = screen.getGraphics();
        g.setColor(attributeColor(quadrant));
        g.fillOval(x, y, POINTER_SIZE, POINTER_SIZE);

    }
}
