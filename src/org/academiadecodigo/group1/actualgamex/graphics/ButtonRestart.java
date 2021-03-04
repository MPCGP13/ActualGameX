package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.Messages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonRestart implements ActionListener {

    private int action;
    private final UserGraphics userGraphics;

    public ButtonRestart(int action, UserGraphics userGraphics) {
        this.action = action;
        this.userGraphics = userGraphics;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (action == 0 && userGraphics.getGameStage().equals(Messages.STANDBY)) {
            userGraphics.setRestarting(true);
        }

    }
}
