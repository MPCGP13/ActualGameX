package org.academiadecodigo.group1.actualgamex.server;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class GameLogic {

    private final Server server;
    private UserHandler fakeArtist;
    private CopyOnWriteArrayList<Integer> votes;


    public GameLogic(Server server) {
        this.server = server;
        votes = new CopyOnWriteArrayList<>();
    }

    public void init() {

        fakeArtist = server.getUsers().get((int) (Math.random() * server.getUsers().size()));

        server.broadcast ("/START_GAME " + GameWords.random(), fakeArtist);
        fakeArtist.send("/START_GAME imposter!");

        sleep(60);

        server.broadcast ("/VOTE_TIME 1", null);

        while (votes.size() < server.getUsers().size()) {
            sleep(3);
        }

        if (checkVote()) { server.broadcast ("/END_GAME FA_lost", null); }
        else { server.broadcast ("/END_GAME FA_won", null); }

        sleep(20);

        server.run();

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

        if (finalVotes >= server.getUsers().size() / 2) {
            return true;
        }

        return false;

    }

    public void addVotes(int vote) {
        votes.add(vote);
    }
}

