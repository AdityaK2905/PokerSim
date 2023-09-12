package classes;

public class Card {
    private final int rank;
    private final String suit;
    private boolean shortened;

    public Card(int r, String s, boolean sh) {
        rank = r;
        suit = s;
        shortened = sh;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return ""+rank+" of " + suit;
    }
}