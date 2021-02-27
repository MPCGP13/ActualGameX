package org.academiadecodigo.group1.actualgamex.server.commands;


import org.academiadecodigo.group1.actualgamex.server.Messages;
import org.academiadecodigo.group1.actualgamex.server.Server;
import org.academiadecodigo.group1.actualgamex.server.UserHandler;

public class QuitHandler implements CommandHandler{

    @Override
    public void handle(Server server, UserHandler sender, String message) {
        server.remove(sender);
        server.broadcast(sender.getName() + " " + Messages.LEAVE);
        sender.close();
    }
}
