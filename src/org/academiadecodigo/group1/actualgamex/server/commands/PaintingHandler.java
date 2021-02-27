package org.academiadecodigo.group1.actualgamex.server.commands;


import org.academiadecodigo.group1.actualgamex.server.Server;
import org.academiadecodigo.group1.actualgamex.server.UserHandler;

public class PaintingHandler implements CommandHandler{

    @Override
    public void handle(Server server, UserHandler sender, String message) {

        if (!isValid(message)) {
            return;
        }

        server.broadcast(sender.getColor() + ":" + message);
    }

    private boolean isValid(String message) {
        return !message.trim().isEmpty();
    }
}