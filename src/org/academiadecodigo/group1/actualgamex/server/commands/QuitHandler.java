package org.academiadecodigo.group1.actualgamex.server.commands;


import org.academiadecodigo.group1.actualgamex.Messages;
import org.academiadecodigo.group1.actualgamex.server.Server;
import org.academiadecodigo.group1.actualgamex.server.UserHandler;

import java.util.concurrent.TimeUnit;

public class QuitHandler implements CommandHandler{

    @Override
    public void handle(Server server, UserHandler sender, String message) {
        server.broadcast(Messages.END_GAME, server.getUsers().get(0));

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        server.remove(sender);
        sender.close();
    }
}
