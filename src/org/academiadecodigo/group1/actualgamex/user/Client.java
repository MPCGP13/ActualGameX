package org.academiadecodigo.group1.actualgamex.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    private Socket socket;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }

    public void start() {
        Thread keyboard = new Thread(new KeyboardHandler(socket));
        keyboard.start();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {
                waitMessage(reader);
            }

        } catch (IOException e) {
            System.err.println("Error handling socket connection: " + e.getMessage());
        }
    }

    private void waitMessage(BufferedReader reader) throws IOException {
        String message = reader.readLine();

        if (message == null) {
            System.out.println("Connection closed from server side");
            System.exit(0);
        }

        System.out.println(message);
    }
}
