package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.graphics.UserGraphics;

import java.util.concurrent.CopyOnWriteArrayList;

public class Paintor implements Runnable{


    private UserGraphics userGraphics;
    private User user;
    private int quadrant;

    public Paintor(UserGraphics userGraphics, User user, int quadrant) {
        this.user = user;
        this.userGraphics = userGraphics;
        this.quadrant = quadrant;
    }

    @Override
    public void run() {

        CopyOnWriteArrayList<String> buff = user.getUserListener().getOtherCoordBuffer(quadrant);

        while (true) {
            if (buff.size() > 0) {
                String[] messageSplit = buff.remove(0).split(",");
                int x = Integer.parseInt(messageSplit[0]);
                int y = Integer.parseInt(messageSplit[1]);
                userGraphics.paintDot(x, y, quadrant);
            }
        }

    }
}
