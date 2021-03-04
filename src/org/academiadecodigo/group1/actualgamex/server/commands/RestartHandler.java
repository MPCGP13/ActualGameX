package org.academiadecodigo.group1.actualgamex.server.commands;


import org.academiadecodigo.group1.actualgamex.server.Server;
import org.academiadecodigo.group1.actualgamex.server.UserHandler;

public class RestartHandler implements CommandHandler{

    @Override
    public void handle(Server server, UserHandler sender, String message) {
        server.getGameLogic().setRestart(true);
    }

}