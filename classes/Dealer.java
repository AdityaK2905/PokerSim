package classes;

import java.util.ArrayList;

public class Dealer {
    private int pot;
    private Deck deck;
    private int playerCount;
    private ArrayList<Card> flopPile = new ArrayList<Card>();

    public Dealer(int players) {
        //Variable Assignment
        playerCount = players;

        //Deck Creation
        deck = new Deck(false);
        deck.shuffleDeck();
    }

    public int getPot() {
        return pot;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    
}
