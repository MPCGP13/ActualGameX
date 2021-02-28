package org.academiadecodigo.group1.actualgamex.server;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class GameLogic {

    private final Server server;
    private UserHandler fakeArtist;
    private CopyOnWriteArrayList<Integer> votes;
    private String word;


    public GameLogic(Server server) {
        this.server = server;
        votes = new CopyOnWriteArrayList<>();
    }

    public void init() {

        word = GameWords.random();

        fakeArtist = server.getUsers().get((int) (Math.random() * server.getUsers().size()));

        server.broadcast ("/START_GAME " + word, fakeArtist);
        fakeArtist.send("/START_GAME FakeDudu");

        sleep(31);

        server.broadcast ("/VOTE_TIME 1", null);

        while (votes.size() < server.getUsers().size()) {
            sleep(3);
        }


        if (!checkVote()) {
            server.broadcast (Messages.END_GAME + " FakeDudu " + word, null); }
        else {
            server.broadcast (Messages.END_GAME + " player " + word, null);
        }

        //sleep(15);



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
}

