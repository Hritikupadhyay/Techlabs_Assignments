package com.aurionpro.array;
import java.util.Random;
import java.util.Scanner;

public class BookCricket {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        
        int[] scores = new int[2]; 
        System.out.println("Play game");
        
        
        for (int player = 0; player < 2; player++) {
            System.out.println("Batsman " + (player + 1) );
            while (true) {
            	
                int pageNumber = random.nextInt(100) + 1; 
                int lastDigit = pageNumber % 10;
            

                System.out.println("Page number: " + pageNumber);
                if (lastDigit == 7 || lastDigit == 8 || lastDigit == 9) {
                    System.out.println("Player " + (player + 1) + " is out!");
                    break;
                } else {
                    scores[player] += lastDigit;
                    System.out.println("Score: " + lastDigit);
                    System.out.println(" Total: " + scores[player] );
                }
            }
        }

       
        System.out.println("Final Scores:");
        System.out.println("Player1: " + scores[0]);
        System.out.println("Player2: " + scores[1]);

        if (scores[0] > scores[1]) {
            System.out.println(" Player1 is winner");
        } else if (scores[1] > scores[0]) {
            System.out.println("Player2 is winner");
        } else {
            System.out.println("tie");
        }


	}

}
