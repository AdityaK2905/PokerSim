package classes;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
    private int size = 52;
    private boolean shuffled;
    private boolean shortened = false;

    public ArrayList<Card> deck = new ArrayList<Card>();

    public Deck(boolean sh) {
        shortened = sh;
        

        for (int i = 1; i < 14; i++) {
            for (int j = 1; j < 5; j++) {
                switch (j) {
                    case 1:
                        deck.add(new Card(i, "Clubs", shortened));
                        break;
                    case 2:
                        deck.add(new Card(i, "Spades", shortened));
                        break;
                    case 3:
                        deck.add(new Card(i, "Hearts", shortened));
                        break;
                    case 4:
                        deck.add(new Card(i, "Diamonds", shortened));
                        break;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void printDeck() {
        for (int i = 0; i < size; i++) {
            System.out.println(deck.get(i));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

}

