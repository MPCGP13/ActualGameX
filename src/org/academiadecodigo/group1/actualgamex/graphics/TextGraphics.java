package org.academiadecodigo.group1.actualgamex.graphics;

import javax.swing.*;
import java.awt.*;

public class TextGraphics extends JFrame {

   private Canvas canvas;

    TextGraphics(){
        Graphics text = getGraphics();
        text.drawString("HELLO WORLD", 100, 100);
        text.setColor(Color.BLUE);
        text.setFont (new Font ("Helvetica", Font.BOLD | Font.ITALIC, 20));
    }

    ///CREATE A BUILDER FOR TEXTGRAPHICS
   // public void generateText()


       // public class TextGraphicsBuilder(){

       // }

}
