package org.academiadecodigo.group1.actualgamex.graphics;

import java.awt.*;

public class UserGraphics {

    //private String name;
    private boolean host;
    private double[] coordenates;
    private Color color;
    private int quadrant;
    private MouseController mouseController;
    private Canvas canvas;


    UserGraphics(boolean host, int quadrant, Canvas canvas) {
        //name = null;
        this.host = host;
        this.canvas = canvas;
        coordenates = new double[2];
        color = attributeColor();
        mouseController = new MouseController(this);

    }


    /**
     * Method responsible for atributing a Color.java.awt according to User quadrant
     *
     */

    public Color attributeColor(){
        switch (quadrant){
            case 1:
                return  Color.BLUE;
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

    //SETTERS


    public void setCoordenates(double[] coordenates) {
        this.coordenates = coordenates;
    }
}
