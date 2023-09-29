import classes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        double gamesCount = 0;
        double wonCount = 0;
        while (true) {
            System.out.println("Would You Like to Play Again? Y/N");
            String ans = myObj.nextLine();
            if (ans.toLowerCase().equals("y")) {
                gamesCount += 1.0;
                Game game = new Game();
                wonCount += game.wOrL;
                System.out.println("Win Percentage: " + (Double)(wonCount/gamesCount));
            } else if (ans.toLowerCase().equals("n")) {
                break;
            }
        }
    }
}