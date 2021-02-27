package org.academiadecodigo.group1.actualgamex.graphics;

import javax.swing.*;
import java.awt.*;

public class LineGraphics extends JFrame {

    private Canvas canvas;

    LineGraphics(int xinitial,int yinitial,int xfinal,int yfinal) {
        Graphics line = getGraphics();
        line.drawLine(xinitial, yinitial, xfinal, yfinal);
        line.setColor(Color.BLACK);

    }
    ///CREATE A BUILDER FOR TEXTGRAPHICS
    // public void generateText()


    // public class TextGraphicsBuilder(){

    // }

}
