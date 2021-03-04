package org.academiadecodigo.group1.actualgamex.server;

public class GameWords {

    private static final String[] WORDS = {
            "elephant",
            "array",
            "genius",
            "glasses",
            "football",
            "Luis",
            "Fabio",
            "programming",
            "boat",
            "mouse",
            "computer",
            "banana",
            "clock",
            "shoe",
            "lips",
            "fish",
            "lamp",
            "t-shirt",
            "apple",
            "spider",
            "tree",
            "vacation",
            "peixeDeFrente",
            "CarCrash"
    };

    public static String random() {
        return WORDS[(int) (Math.random() * WORDS.length)];
    }

}
