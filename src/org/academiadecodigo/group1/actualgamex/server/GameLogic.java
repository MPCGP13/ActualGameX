package org.academiadecodigo.group1.actualgamex.server;

import org.academiadecodigo.group1.actualgamex.Messages;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class GameLogic {

    private final Server server;
    private UserHandler fakeArtist;
    private CopyOnWriteArrayList<Integer> votes;
    private String word;
    private boolean restart;

    public GameLogic(Server server) {
        this.server = server;
        votes = new CopyOnWriteArrayList<>();

    }

    public void init() {

        restart = false;

        word = GameWords.random();
        fakeArtist = server.getUsers().get((int) (Math.random() * server.getUsers().size()));

        sleep(4);

        server.broadcast(Messages.START_GAME + " " + word, fakeArtist);
        fakeArtist.send(Messages.START_GAME + " " + "FakeDudu");

        sleep(31);

        server.broadcast("/VOTE_TIME 1", null);

        while (votes.size() < server.getUsers().size()) {
            sleep(3);
        }

        if (!checkVote()) {
            server.broadcast(Messages.STANDBY + " FakeDudu " + word, null);
        } else {
            server.broadcast(Messages.STANDBY + " player " + word, null);
        }

        while (!restart) {
            sleep(1);
        }

        server.broadcast(Messages.RESTART_GAME, null);

        init();
    }

    public void sleep (int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkVote() {

        int finalVotes = 0;

        for (Integer vote : votes) {
            if (vote == fakeArtist.getColorID()) {
                finalVotes++;
            }
        }

        System.out.println("FakeDudu votes: " + finalVotes);

        if (finalVotes >= server.getUsers().size() / 2) {
            return true;
        }

        return false;

    }

    public void addVotes(int vote) {
        votes.add(vote);
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }
}

