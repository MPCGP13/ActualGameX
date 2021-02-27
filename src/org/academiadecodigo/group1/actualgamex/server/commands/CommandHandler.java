package org.academiadecodigo.group1.actualgamex.server.commands;

import org.academiadecodigo.group1.actualgamex.server.Server;
import org.academiadecodigo.group1.actualgamex.server.UserHandler;

public interface CommandHandler {
    void handle(Server server, UserHandler sender, String message);
}
