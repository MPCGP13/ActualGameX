package org.academiadecodigo.group1.actualgamex.graphics;

import java.awt.*;

public class UserGraphics {

    //private String name;
    private boolean host;
    private int[] coordenates;
    private Color color;
    private int quadrant;
    private MouseController mouseController;


    UserGraphics(boolean host, int quadrant) {
        //name = null;
        this.host = host;
        coordenates = new int[2];
        color = attributeColor();
        mouseController = new MouseController(this);
    }


    /**
     * Method responsible for atributing a Color.java.awt according to User quadrant
     *
     */

    public Color attributeColor(){
        switch (quadrant){
            case 0:
                return  Color.BLUE;
            case 1:
                return Color.RED;
            case 2:
                return Color.YELLOW;
            default:
                return Color.GREEN;
        }
    }


    //GETTERS

    //SETTERS


    public void setCoordenates(int[] coordenates) {
        this.coordenates = coordenates;
    }
}
