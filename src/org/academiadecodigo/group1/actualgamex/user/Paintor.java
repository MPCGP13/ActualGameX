package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.graphics.UserGraphics;

import java.util.concurrent.CopyOnWriteArrayList;

public class Paintor implements Runnable{

    private final UserGraphics userGraphics;
    private final User user;
    private final int userID;

    public Paintor(UserGraphics userGraphics, User user, int userID) {
        this.user = user;
        this.userGraphics = userGraphics;
        this.userID = userID;
    }

    /**
     * Method that gets the opponent PaintingBuffer and paints dots on the user screen.
     */

    @Override
    public void run() {
        CopyOnWriteArrayList<String> paintingBuffer = user.getUserListener().getOpponentPaintingBuffer(userID);

        while (true) {
            if (paintingBuffer.size() > 0) {
                String[] messageSplit = paintingBuffer.remove(0).split(",");
                int x = Integer.parseInt(messageSplit[0]);
                int y = Integer.parseInt(messageSplit[1]);
                userGraphics.paintDot(x, y, userID);
            }
        }
    }
}
