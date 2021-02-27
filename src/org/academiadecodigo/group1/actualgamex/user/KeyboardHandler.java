package org.academiadecodigo.group1.actualgamex.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class KeyboardHandler implements Runnable {

    private static final String EXIT = "/quit";
    private Socket socket;

    public KeyboardHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (!socket.isClosed()) {
                String input = scanner.nextLine();

                if (input.equals(EXIT)) {
                    System.exit(0);
                }

                writer.println(input);
            }

        } catch (IOException e) {
            System.err.println("Error handling socket connection: " + e.getMessage());
        }
    }
}
