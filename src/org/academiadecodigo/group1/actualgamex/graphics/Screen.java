package org.academiadecodigo.group1.actualgamex.graphics;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame{

    Screen(){
        setSize(1200, 600);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setVisible(true);
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
