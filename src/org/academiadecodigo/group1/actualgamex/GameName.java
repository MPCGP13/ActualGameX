package org.academiadecodigo.group1.actualgamex;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.group1.actualgamex.server.Server;
import org.academiadecodigo.group1.actualgamex.user.User;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GameName {

    public static Prompt prompt;

    public GameName() {
        prompt = new Prompt(System.in, System.out);
    }

    public static void main(String[] args) {

        GameName game = new GameName();

        IntegerRangeInputScanner scanner = new IntegerRangeInputScanner(1, 3);
        scanner.setMessage("===Welcome to GameName===\n\r\n\r" +
                "1 -> New Game\n\r" +
                "2 -> Join Game\n\r" +
                "3 -> Exit\n\r\n\r" +
                "=========================\n\r\n\r" +
                "Select an option: ");


        scanner.setError("ERROR: Please select one option...\n\r");
        int option = prompt.getUserInput(scanner);

        try {

            switch(option) {
                case 1:
                    game.newGame();
                    break;
                case 2:
                    game.joinGame();
                    break;
                case 3:
                    System.exit(1);
            }

        } catch (IOException e) {
            System.err.println("Error opening server socket: " + e.getMessage());

        }

    }

    public void newGame() throws IOException {
        int port = getPort();
        Server server = new Server(port);

        Thread serverThread = new Thread(server);
        serverThread.start();

        while (!server.isConnected()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        new User("localhost", port).start();
    }

    public void joinGame() throws IOException {
        String serverIP = getIP();
        int port = getPort();

        new User(serverIP, port).start();
    }


    // GETTERS & SETTERS -----------------------------------------------------------------------------

    private int getPort() {
        IntegerInputScanner portScanner = new IntegerInputScanner();
        portScanner.setMessage("Port: ");
        portScanner.setError("Error port must be a valid number");
        return prompt.getUserInput(portScanner);

    }
    private String getIP() {
        StringInputScanner ipScanner = new StringInputScanner();
        ipScanner.setMessage("Server IP: ");
        return prompt.getUserInput(ipScanner);

    }
}
