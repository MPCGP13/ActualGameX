package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonRestartQuit implements ActionListener {

    private int action;
    private UserGraphics userGraphics;

    public ButtonRestartQuit(int action, UserGraphics userGraphics) {
        this.action = action;
        this.userGraphics = userGraphics;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (action == 0) { userGraphics.setGameStage(Messages.RESTART_GAME); }
        else { userGraphics.setGameStage(Messages.QUIT); }
    }
}
