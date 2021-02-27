package org.academiadecodigo.group1.actualgamex.graphics;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JFrame{

    private UserGraphics host;
    private UserGraphics adv;


    Canvas(){
        setSize(1200, 600);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setVisible(true);
        host = new UserGraphics(true, 1,this);
        adv = new UserGraphics(false, 3,this);
        addMouseMotionListener(new MouseController(host));

    }


    /**
     * this paint method will draw lines to divide
     * @param g
     */

    public void paint(Graphics g){
        g.drawLine(0,300,1200,300);
        g.drawLine(600,0,600,600);
    }






}
