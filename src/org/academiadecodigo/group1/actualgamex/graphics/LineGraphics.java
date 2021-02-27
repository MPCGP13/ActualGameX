package org.academiadecodigo.group1.actualgamex.graphics;

import javax.swing.*;
import java.awt.*;

public class LineGraphics extends JFrame {

    private Canvas canvas;
    private int xinitial;
    private int xfinal;
    private int yinitial;
    private int yfinal;

    LineGraphics(int xinitial,int yinitial,int xfinal,int yfinal) {

        this.xinitial = xinitial;
        this.xfinal = xfinal;
        this.yinitial = yinitial;
        this.yfinal = yfinal;
        Graphics line = getGraphics();
        line.drawLine(0,300,600,300);
        line.setColor(Color.BLACK);

    }
    ///CREATE A BUILDER FOR TEXTGRAPHICS
    // public void generateText()


    // public class TextGraphicsBuilder(){

    // }

}
