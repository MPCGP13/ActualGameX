package org.academiadecodigo.group1.actualgamex.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAction implements ActionListener {

    private int player;
    private UserGraphics userGraphics;

    public ButtonAction(int player, UserGraphics userGraphics) {
        this.player = player;
        this.userGraphics = userGraphics;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        userGraphics.getUser().setVote(player);



    }

}
