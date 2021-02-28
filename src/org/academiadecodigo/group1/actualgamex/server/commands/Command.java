package org.academiadecodigo.group1.actualgamex.server.commands;

import org.academiadecodigo.group1.actualgamex.server.Messages;

public enum Command {
    QUIT("", new QuitHandler()),
    INVALID("", new InvalidHandler()),
    COORDINATES("", new PaintingHandler()),
    VOTE_COORDINATES("/VOTING", new VotingHandler());

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

        return INVALID;
    }

    public CommandHandler getHandler() {
        return handler;
    }
}