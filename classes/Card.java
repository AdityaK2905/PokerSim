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
        switch (rank) {
            case 11:
                return "Jack of " + suit;
            case 12:
                return "Queen of " + suit;
            case 13:
                return "King of " + suit;
            case 1:
                return "Ace of " + suit;
            default:
                return ""+rank+" of " + suit;
        }
    }
}