package com.aurionpro.sol1;

import java.util.Scanner;

public class TreasureIsland {

	public static void main(String[] args) {
		
	Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Treasure Island, your mission is to find treasure");

        
        System.out.print("Do you want to go left or right? ");
        String direction = scanner.nextLine().toLowerCase();

        if (direction.equals("left")) {
            
            System.out.print("Do you want to swim or wait? ");
            String action = scanner.nextLine().toLowerCase();

            if (action.equals("wait")) {
                
                System.out.print("Which door, red, blue, or yellow? ");
                String door = scanner.nextLine().toLowerCase();

                if (door.equals("yellow")) {
                    System.out.println("You Win!");
                } else if (door.equals("red")) {
                    System.out.println("Burned by fire, game over");
                } else if (door.equals("blue")) {
                    System.out.println("Eaten by beasts, game over");
                } else {
                    System.out.println("game over");
                }
            } else {
                System.out.println("Attacked by trout, game over");
            }
        } else {
            System.out.println("Fall into a hole, game over");
        }

        
	}

}
