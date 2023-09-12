package classes;

import java.util.ArrayList;


public class Deck {
    private int size;
    private boolean shuffled;
    public ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();

        for (int i = 1; i < 14; i++) {
            for (int j = 1; j < 5; j++) {
                switch (j) {
                    case 1:
                        deck.add(new Card(i, "Clubs"));
                }
            }
        }
    }

}

