package org.academiadecodigo.group1.actualgamex.server.commands;


import org.academiadecodigo.group1.actualgamex.server.Messages;
import org.academiadecodigo.group1.actualgamex.server.Server;
import org.academiadecodigo.group1.actualgamex.server.UserHandler;

public class InvalidHandler implements CommandHandler{

    @Override
    public void handle(Server server, UserHandler sender, String message) {
        sender.send(Messages.ERROR + ": " + (message.startsWith(Messages.COMMAND_IDENTIFIER) ? Messages.INVALID_COMMAND : message));
    }
}
