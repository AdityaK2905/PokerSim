package classes;

import java.util.ArrayList;

public class Dealer {
    private int pot;
    private Deck deck;
    private int playerCount;
    private ArrayList<Card> flopPile = new ArrayList<Card>();
    private ArrayList<Card> playerHand = new ArrayList<Card>();
    private ArrayList<Card> otherHands = new ArrayList<Card>();

    public Dealer(int players) {
        //Variable Assignment
        playerCount = players;

        //Deck Creation
        deck = new Deck(false);
        deck.shuffleDeck();

        //Game Creation
        startDeal();
        System.out.println("Your Hand");
        printPlayerHand();

        for (int i = 0; i < playerCount; i++) {
            otherHands.add(deck.get(0));
            deck.remove(0);
            otherHands.add(deck.get(0));
            deck.remove(0);
        }

        System.out.println("There are "+playerCount+" other players");
        System.out.println("There are " + deck.getSize() + " cards in the deck remaining");
    }

    public int getPot() {
        return pot;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void startDeal() {
        if (playerHand.size() != 0) {
            int size = playerHand.size();
            for (int s = 0; s < size; s++) {
                deck.add(playerHand.get(0));
                playerHand.remove(0);
            }
        }
        for (int i = 0; i < 2; i++) {
            playerHand.add(deck.get(0));
            deck.remove(0);
        }
    }

    public void initialFlop() {
        for (int i = 0; i < 3; i++) {
            flopPile.add(deck.get(0));
            deck.remove(0);
        }
    }

    public void turn() {
        flopPile.add(deck.get(0));
        deck.remove(0);
    }

    public void river() {
        flopPile.add(deck.get(0));
        deck.remove(0);
    }


    public void printFlopPile() {
        for (int i = 0; i < flopPile.size(); i++) {
            System.out.println(flopPile.get(i));
        }
    }

    public void printPlayerHand() {
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println(playerHand.get(i));
        }
    }

}
