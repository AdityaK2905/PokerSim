package classes;

import java.util.Random;
import java.util.ArrayList;


public class Game {
    public int[] handRanks = new int[13];
    public Dealer dealer;


    public Game() {
        
        int players = 0;
        dealer = new Dealer(players);
        for (int i = 0; i < 13; i++) {
            handRanks[i] = 0;
        }
        dealer.initialFlop();
        dealer.turn();
        dealer.river();
        for (int i = 0; i < dealer.flopPile.size(); i++) {
            System.out.println(dealer.flopPile.get(i));
        }
        System.out.println("pairscount: " + checkPairs());
        System.out.println("twoPairs: " + checkTwoPair());
        System.out.println("FullHouse: "+checkFullHouse());
        System.out.println("Straight: " + checkStraight());
    }

    //Fold and Call function
    public String foldOrCallBasic() {
      return "";  
    }



    //return 2 if one pair, 3 if trips, 4 if 4 of a kind
    public int checkPairs() {
        int totalSize = dealer.playerHand.size() + dealer.flopPile.size();
        ArrayList<Card> totalHand = new ArrayList<Card>();
        for (int i = 0; i < 2; i++) {
            totalHand.add(dealer.playerHand.get(i));
        }
        for (int i = 0; i < dealer.flopPile.size(); i++) {
            totalHand.add(dealer.flopPile.get(i));
        }
        int[] ranks = new int[13];
        for (int i = 0; i < 13; i++) {
            ranks[i] = 0;
        }

        for (int i = 0; i < totalSize; i++) {
            ranks[totalHand.get(i).getRank()-1]++;
        }


        //pair logic
        int highest = 1;
        int toReturn = 0;
        for (int i = 0; i < 13; i++) {
            if (ranks[i] > 1) {
                if (ranks[i] > toReturn) {
                    toReturn = ranks[i];
                }
            }
            /*
            if (toReturn == 2 && ranks[i] == 2) {
                toReturn = 2;
            }
            */
        }
        /*
        for (int i = 0; i < ranks.length; i++) {
            System.out.println(ranks[i]);
        }
        */
        
        return toReturn;
    }
    
    public int checkTwoPair() {
        int totalSize = dealer.playerHand.size() + dealer.flopPile.size();
        ArrayList<Card> totalHand = new ArrayList<Card>();
        for (int i = 0; i < 2; i++) {
            totalHand.add(dealer.playerHand.get(i));
        }
        for (int i = 0; i < dealer.flopPile.size(); i++) {
            totalHand.add(dealer.flopPile.get(i));
        }
        int[] ranks = new int[13];
        for (int i = 0; i < 13; i++) {
            ranks[i] = 0;
        }

        for (int i = 0; i < totalSize; i++) {
            ranks[totalHand.get(i).getRank()-1]++;
        }

        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i] == 2) {
                for (int j = i+1; j < ranks.length; j++) {
                    if (ranks[j] == 2) {
                        return 2;
                    }
                }
            }
        }
        return 0;
    }

    public int checkFullHouse() {
        int totalSize = dealer.playerHand.size() + dealer.flopPile.size();
        ArrayList<Card> totalHand = new ArrayList<Card>();
        for (int i = 0; i < 2; i++) {
            totalHand.add(dealer.playerHand.get(i));
        }
        for (int i = 0; i < dealer.flopPile.size(); i++) {
            totalHand.add(dealer.flopPile.get(i));
        }
        int[] ranks = new int[13];
        for (int i = 0; i < 13; i++) {
            ranks[i] = 0;
        }

        for (int i = 0; i < totalSize; i++) {
            ranks[totalHand.get(i).getRank()-1]++;
        }

        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i] == 2) {
                for (int j = i+1; j < ranks.length; j++) {
                    if (ranks[j] == 3) {
                        return 1;
                    }
                }
            }
            if (ranks[i] == 3) {
                for (int j = i+1; j < ranks.length; j++) {
                    if (ranks[j] == 2) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    public int checkStraight() {
        int totalSize = dealer.playerHand.size() + dealer.flopPile.size();
        ArrayList<Card> totalHand = new ArrayList<Card>();
        for (int i = 0; i < 2; i++) {
            totalHand.add(dealer.playerHand.get(i));
        }
        for (int i = 0; i < dealer.flopPile.size(); i++) {
            totalHand.add(dealer.flopPile.get(i));
        }
        int[] ranks = new int[13];
        for (int i = 0; i < 13; i++) {
            ranks[i] = 0;
        }
        /*
        ranks[0] = 1;
        ranks[1] = 1;
        ranks[2] = 1;
        ranks[3] = 1;
        ranks[5] = 1;
        */

        for (int i = 0; i < totalSize; i++) {
            ranks[totalHand.get(i).getRank()-1]++;
        }
        boolean straight;
        for (int i = 0; i < 9; i++) {
            straight = true;
            for (int j = i; j < i + 5; j++) {
                if (ranks[j] < 1) {
                    straight = false;
                }
                
            }
            if (straight) {
                return 1;
            }
        }
        return 0;
    }

    public int checkFlush() {
        int totalSize = dealer.playerHand.size() + dealer.flopPile.size();
        ArrayList<Card> totalHand = new ArrayList<Card>();
        for (int i = 0; i < 2; i++) {
            totalHand.add(dealer.playerHand.get(i));
        }
        for (int i = 0; i < dealer.flopPile.size(); i++) {
            totalHand.add(dealer.flopPile.get(i));
        }
        int[] ranks = new int[13];
        for (int i = 0; i < 13; i++) {
            ranks[i] = 0;
        }

        for (int i = 0; i < totalSize; i++) {
            switch (totalHand.get(i).getSuit()) {
                case "Clubs":
                    ranks[i] = 1;
                case "Spades":
                    ranks[i] = 2;
                case "Hearts":
                    ranks[i] = 3;
                case "Diamonds":
                    ranks[i] = 4;
            }
        }
        boolean flush;
        int startingSuit;
        for (int i = 0; i < totalSize-3; i++) {
            flush = true;
            startingSuit=ranks[i];
            for (int j = i; j < i+4; j++){
                
            }
        }
    }
}
