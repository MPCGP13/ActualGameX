package org.academiadecodigo.group1.actualgamex.server.commands;

import org.academiadecodigo.group1.actualgamex.Messages;

public enum Command {
    QUIT("", new QuitHandler()),
    RESTART("RESTART_GAME", new RestartHandler()),
    INVALID("", new InvalidHandler()),
    COORDINATES("", new PaintHandler()),
    VOTE_COORDINATES("VOTING", new VoteHandler());

    private String commandMessage;
    private CommandHandler handler;

    Command(String commandMessage, CommandHandler handler) {
        this.commandMessage = Messages.COMMAND_IDENTIFIER + commandMessage;
        this.handler = handler;
    }

    public static Command filterMessage(String message) {

        if (message == null) {
            return QUIT;
        }

        if (!message.startsWith(Messages.COMMAND_IDENTIFIER)) {
            return COORDINATES;
        }

        String userCommand = message.split(" ")[0];


        for (Command command : values()) {
            if (userCommand.equals(command.commandMessage)) {
                return command;
            }
        }

        System.out.println("going invalid on command");
        return INVALID;
    }

    public CommandHandler getHandler() {
        return handler;
    }
}