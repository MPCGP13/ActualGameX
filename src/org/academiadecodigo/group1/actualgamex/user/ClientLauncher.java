package org.academiadecodigo.group1.actualgamex.user;

import java.io.IOException;

public class ClientLauncher {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: <host> <port>");
            return;
        }

        try {
            User user = new User(args[0], Integer.valueOf(args[1]));
            user.start();

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.err.println("Error port must be a valid number: " + args[1]);
        }
    }
}
