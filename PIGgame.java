package com.aurionpro.assSolution;
import java.util.Random;
import java.util.Scanner;

public class PIGgame {

	public static void main(String[] args) {
		
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;

       
        while (playAgain) {
            int totalScore = 0;
            int turn = 0;

            System.out.println("Welcome to PIG game ");

            
            while (totalScore < 20) {
                turn++;
                int turnScore = 0;
                boolean turnOver = false;

                System.out.println("\n turn " + turn);
                
                if (totalScore>=20)
                {
                	System.out.println("game is over, you scored 20 or greater than 20");
                }


                
                while (!turnOver) {
                    System.out.print("roll/hold? (r/h): ");
                    String choice = scanner.nextLine().trim().toLowerCase();
                    
                    
                    
                    
                    if (choice.equals( "r")) {
                        int die = random.nextInt(6) + 1; 
                        System.out.println("Die: " + die);

                        
                        if (die == 1) {
                            System.out.println("you roll a 1! turn over");
                            turnOver = true;
                        } else  if (turnScore>=20)
                        {
                        	System.out.println("game is over, you Scored 20 or greater than 20");
                        	turnOver = true;
                        }
                        
                        
                        
                        else{
                            turnScore = turnScore+die; 
                            System.out.println("current turn score: " + turnScore);
                        }

                    
                    } else if (choice.equals("h")) {
                        //turnScore = turnScore+ totalScore; 
                        totalScore = turnScore + totalScore;
                        
                        
                        System.out.println("you hold. turn score: " + totalScore);
                        System.out.println("total score: " + totalScore);
                        turnOver = true; 
                        
                    
                    } 
                    
                    
                    
                    else  {
                        System.out.println("Invalid input. please enter 'r'/'h'");
                    }
                }
            }

            System.out.println("\nYou won in " + turn + " turns with a total score of " + totalScore + "!");
            
           
            System.out.print("do you want to play again? (Y/N): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("n")) {
                playAgain = false;
            }
        }

        System.out.println("Thankyou for playing ");
        
    }
		       
}
