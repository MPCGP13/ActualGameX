package org.academiadecodigo.group1.actualgamex.server.commands;


import org.academiadecodigo.group1.actualgamex.server.Server;
import org.academiadecodigo.group1.actualgamex.server.UserHandler;

public class EndingHandler implements CommandHandler{

    @Override
    public void handle(Server server, UserHandler sender, String coordinates) {

        if (!isValid(coordinates)) {
            return;
        }

        server.endingOption(coordinates);
    }

    private boolean isValid(String message) {
        return !message.trim().isEmpty();
    }
}