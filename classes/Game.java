package classes;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    public int[] handRanks = new int[13];
    public Dealer dealer;
    Scanner myObj = new Scanner(System.in);
    //0 if bad move, 1 if good move
    public double wOrL = 0;

    public Game() {
        
        int players = 1;
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

        ArrayList<Card> totalHand = new ArrayList<Card>();
        for (int i = 0; i < 2; i++) {
            totalHand.add(dealer.playerHand.get(i));
        }
        for (int i = 0; i < dealer.flopPile.size(); i++) {
            totalHand.add(dealer.flopPile.get(i));
        }

        ArrayList<Card> oppHand = new ArrayList<Card>();
        for (int i = 0; i < 2; i++) {
            oppHand.add(dealer.otherHands.get(i));
        }
        for (int i = 0; i < dealer.flopPile.size(); i++) {
            oppHand.add(dealer.flopPile.get(i));
        }
        

        int oppHighCard = determineHighCard(oppHand);
        int myHighCard = determineHighCard(totalHand);


        // System.out.println("pairscount: " + checkPairs(totalHand));
        // System.out.println("twoPairs: " + checkTwoPair(totalHand));
        // System.out.println("FullHouse: "+checkFullHouse(totalHand));
        // System.out.println("Straight: " + checkStraight(totalHand));
        // System.out.println("Flush: " + checkFlush(totalHand));
        // System.out.println("StraightFlush: " + checkStraightFlush(totalHand));
        System.out.println("1 if high card, 2 if pair, 3 if two pair, 4 if trips, 5 if straight, 6 if flush, 7 if full house, 8 if quads, 9 if straight flush");
        System.out.println("HandRank: " + determineHand(totalHand));

        System.out.println("Would you like to Fold or Call? F/C.");
        String ans = myObj.nextLine().toLowerCase();

        if (ans.equals("f")) {
            if (determineHand(oppHand) > determineHand(totalHand)) {
                System.out.println("Good fold, you would've lost.");
                wOrL = 1;
            } else if (determineHand(totalHand) == determineHand(oppHand)) {
                if (oppHighCard > myHighCard) {
                    System.out.println("Good Fold, you would've lost off high card.");
                    wOrL = 1;
                } else if (myHighCard > oppHighCard) {
                    System.out.println("Bad fold, you won off high card.");
                    wOrL = 0;
                } else if (myHighCard == oppHighCard) {
                    System.out.println("You would've tied.");
                    wOrL = 0;
                }    
            } else {
                System.out.println("Bad fold, you would've won.");
                wOrL = 0;
            }
        }
        if (ans.equals("c")) {
            if (determineHand(oppHand) > determineHand(totalHand)) {
                System.out.println("Bad call, you lost.");
                wOrL = 0;
            } else if (determineHand(totalHand) == determineHand(oppHand)) {
                if (oppHighCard > myHighCard) {
                    System.out.println("Bad call, you lost off high card.");
                    wOrL = 0;
                } else if (myHighCard > oppHighCard) {
                    System.out.println("Good call, you won off high card.");
                    wOrL = 1;
                } else if (myHighCard == oppHighCard) {
                    System.out.println("You tied.");
                    wOrL = 1;
                }   
            } else {
                System.out.println("Good call, you won.");
                wOrL = 1;
            }
        }

        System.out.println("OppHandRank: " + determineHand(oppHand));
        System.out.println("Opp Hand");
        for (int i = 0; i < 2; i++) {
            System.out.println(oppHand.get(i));
        }
        // if (determineHand(totalHand) > determineHand(oppHand)) {
        //     System.out.println("You win.");
        // } else if (determineHand(totalHand) == determineHand(oppHand)) {
        //     System.out.println("Tied");
        // } else {
        //     System.out.println("You lost.");
        // }
    }

    //Fold and Call function
    public String foldOrCallBasic() {
      return "";  
    }



    //return 2 if one pair, 3 if trips, 4 if 4 of a kind
    public int checkPairs(ArrayList<Card> totalHand) {
        int totalSize = totalHand.size();
        
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
    
    public int checkTwoPair(ArrayList<Card> totalHand) {
        int totalSize = totalHand.size();
        
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

    public int checkFullHouse(ArrayList<Card> totalHand) {
        int totalSize = totalHand.size();
        
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

    public int checkStraight(ArrayList<Card> totalHand) {
        int totalSize = totalHand.size();
        
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

    public boolean checkFlush(ArrayList<Card> totalHand) {
        int totalSize = totalHand.size();
        
        int[] suits = new int[4];
        for (int i = 0; i < 4; i++) {
            suits[i] = 0;
        }

        for (int i = 0; i < totalSize; i++) {
            switch (totalHand.get(i).getSuit()) {
                case "Clubs":
                    suits[0]++;
                    break;
                case "Spades":
                    suits[1]++;
                    break;
                case "Hearts":
                    suits[2]++;
                    break;
                case "Diamonds":
                    suits[3]++;
                    break;
            }
        }
        
        
        for (int i = 0; i < suits.length; i++) {
            if (suits[i] > 4) {
                return true;
            }
        }
        return false;
    }

    public boolean checkStraightFlush(ArrayList<Card> totalHand) {
        int totalSize = totalHand.size();
        
        int[] suits = new int[4];
        for (int i = 0; i < 4; i++) {
            suits[i] = 0;
        }

        for (int i = 0; i < totalSize; i++) {
            switch (totalHand.get(i).getSuit()) {
                case "Clubs":
                    suits[0]++;
                    break;
                case "Spades":
                    suits[1]++;
                    break;
                case "Hearts":
                    suits[2]++;
                    break;
                case "Diamonds":
                    suits[3]++;
                    break;
            }
        }
        
        ArrayList<Card> flushHand = new ArrayList<Card>();
        for (int i = 0; i < suits.length; i++) {
            if (suits[i] > 4) {
                for (int j = 0; j < totalSize; j++) {
                    switch (i) {
                        case (0):
                        if (totalHand.get(j).getSuit() == "Clubs") {
                            flushHand.add(totalHand.get(j));
                        }
                        break;
                        case (1):
                        if (totalHand.get(j).getSuit() == "Spades") {
                            flushHand.add(totalHand.get(j));
                        }
                        break;
                        case 2:
                        if (totalHand.get(j).getSuit() == "Hearts") {
                            flushHand.add(totalHand.get(j));
                        }
                        break;
                        case 3:
                        if (totalHand.get(j).getSuit() == "Diamonds") {
                            flushHand.add(totalHand.get(j));
                        }
                        break;
                    }
                }
            }
        }

        //System.out.println(flushHand.size());
        int[] flushNums = new int[13];
        for (int i = 0; i < 13; i++) {
            flushNums[i] = 0;
        }
        for (int i = 0; i < flushHand.size(); i++) {
            flushNums[flushHand.get(i).getRank()-1]++;
            System.out.println(flushHand.get(i));
        }
        if (flushHand.size() > 0) {
            for (int i = 0; i < 13; i++) {
                //System.out.println("flushing"+i+" "+ flushNums[i]);
            }
        }
        boolean straight = false;
        for (int i = 0; i < 9; i++) {
            straight = true;
            for (int j = i; j < i + 5; j++) {
                if (flushNums[j] < 1) {
                    straight = false;
                }
                
            }
            if (straight) {
                return true;
            }
        }
        return false;
    }

    //1 if high card, 2 if pair, 3 if two pair, 4 if trips, 5 if straight, 6 if flush, 7 if full house, 8 if quads, 9 if straight flush, 
    public int determineHand(ArrayList<Card> totalHand) {
        int output = 0;
        if (checkStraightFlush(totalHand)) {
            output = 9;
            return output;
        }
        if (checkPairs(totalHand) == 4) {
            output = 9;
            return output;
        }
        if (checkFullHouse(totalHand) == 1) {
            output = 7;
            return output;
        }
        if (checkFlush(totalHand)) {
            output = 6;
            return output;
        }
        if (checkStraight(totalHand) == 1) {
            output = 5;
            return output;
        }
        if (checkPairs(totalHand) == 3) {
            output = 4;
            return output;
        }
        if (checkTwoPair(totalHand) == 2){
            output = 3;
            return output;
        }
        if (checkPairs(totalHand) == 2) {
            output = 2;
            return output;
        }
        return 1;
        
        
        
    }

    public int determineHighCard(ArrayList<Card> totalHand) {
        int totalSize = totalHand.size();
        
        int highCard = 0;

        int[] ranks = new int[13];
        for (int i = 0; i < 13; i++) {
            ranks[i] = 0;
        }

        for (int i = 0; i < totalSize; i++) {
            ranks[totalHand.get(i).getRank()-1]++;
        }

        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i] > highCard) {
                highCard = i;
            }
        }
        return highCard;
    }


}
