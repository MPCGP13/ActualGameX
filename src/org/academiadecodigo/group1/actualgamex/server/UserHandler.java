package org.academiadecodigo.group1.actualgamex.server;
import org.academiadecodigo.group1.actualgamex.server.commands.Command;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserHandler implements Runnable {

    private Socket socket;
    private Server server;
    private String name;
    private PrintWriter out;
    private final Color color;

    public UserHandler(Socket socket, Server server, String name, Color color) {
        this.socket = socket;
        this.server = server;
        this.name = name;
        this.color = color;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = openStreams();

            send(Messages.WELCOME);

            if (!server.addClient(this)) {
                send(Messages.SERVER_FULL);
                close();
            }

            while (!socket.isClosed()) {
                listen(in);
            }

        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }

    private void listen(BufferedReader in) throws IOException {
        String message = in.readLine();
        Command.filterMessage(message).getHandler().handle(server, this, message);
    }

    private BufferedReader openStreams() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error closing client socket: " + e.getMessage());
        }
    }

    public void send(String message) {
        out.println(message);
    }


    // GETTERS & GETTERS --------------------------------------------------------------------

    public String getName() {
        return name;
    }
    public Color getColor() { return color; }
    public void setName(String name) {
        this.name = name;
    }

}
