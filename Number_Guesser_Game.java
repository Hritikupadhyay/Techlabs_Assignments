package com.aurionpro.array;
import java.util.Random;
import java.util.Scanner;

public class NumberGuesser {

	public static void main(String[] args) {
		
	Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;

        while (playAgain) {
            int randomNumber = random.nextInt(100) + 1; 
            int attempts = 0;
            int guess = 0;

             while (guess != randomNumber) {
                System.out.print("Guess number (1-100): ");
                guess = scanner.nextInt();
                attempts++;

                if (guess < randomNumber) {
                    System.out.println("Sorry, too low");
                } else if (guess > randomNumber) {
                    System.out.println("Sorry, too high");
                } else {
                    System.out.println("You won in " + attempts + " attempts");
                }
            }

       
            System.out.print("Do you want to play again (yes/no)? ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("yes")) {
                playAgain = true;
            } else {
                playAgain = false;
            }
        }

        System.out.println("Thank you for playing!");
        scanner.close();
	}

}
