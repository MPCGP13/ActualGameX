package org.academiadecodigo.group1.actualgamex.graphics;

import org.academiadecodigo.group1.actualgamex.server.Messages;
import org.academiadecodigo.group1.actualgamex.user.User;

import java.awt.event.*;


public class MouseController implements MouseMotionListener {

    private UserGraphics userGraphics;
    private User user;

    public MouseController(UserGraphics userGraphics, User user) {
        this.userGraphics = userGraphics;
        this.user = user;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if(userGraphics.getGameStage().equals(Messages.START_GAME)){
            if (checkIfPaint(e)) {
                userGraphics.paintDot(e.getX(), e.getY(), userGraphics.getUserID());
                user.getMyPaintingBuffer().add(e.getX() + "," + e.getY());
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        userGraphics.setCoordinates(new int[]{e.getX(), e.getY()});
    }

    /**
     * checkIfPaint will return one true boolean on just one quadrant
     *
     * @param e Mouse Event java.awt
     * @return boolean to now if he can paint in the quadrant
     */
    private boolean checkIfPaint(MouseEvent e) {

        switch (userGraphics.getUserID()) {
            case 1:
                return (e.getX() > 0 && e.getX() < 595 && e.getY() > 10 && e.getY() < 315);
            case 2:
                return (e.getX() >= 605 && e.getX() < 1190 && e.getY() > 10 && e.getY() < 315);
            case 3:
                return (e.getX() > 0 && e.getX() < 595 && e.getY() > 315 && e.getY() < 640);
            default:
                return (e.getX() >= 605 && e.getX() < 1190 && e.getY() > 315 && e.getY() < 640);
        }

    }



}
