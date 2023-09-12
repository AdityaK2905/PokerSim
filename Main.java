import classes.*;
import classes.Card;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        Deck deck = new Deck(false);
        deck.printDeck();
        deck.shuffleDeck();
        deck.printDeck();
        System.out.println(deck.getSize());
    }
}