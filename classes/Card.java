package classes;

public class Card {
    public final int rank;
    public final String suit;

    public Card(int r, String s) {
        rank = r;
        suit = s;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
}