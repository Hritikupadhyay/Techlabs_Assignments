package com.aurionpro.sol1;
import java.util.Scanner;

public class RollerCoaster {

	public static void main(String[] args) {
	
	Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your height in cm: ");
        int height = scanner.nextInt();

        
        if (height > 120) {
            System.out.println("You can ride.");
            
           
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            int baseCost = 0;

            
            if (age < 12) {
                baseCost = 5;
            } else if (age >= 12 && age < 18) {
                baseCost = 7;
            } else if (age >= 18) {
                baseCost = 12;
                
                if (age >= 45 && age <= 55) {
                    System.out.println("Special offer applied. No extra charge.");
                    baseCost = 0; 
                }
            }

            
            System.out.print("Do you want photos? (yes/no): ");
            String photoOption = scanner.next();

            int photoCost = 0;
            if (photoOption.equalsIgnoreCase("yes")) {
                photoCost = 3;
            }

            
            int totalBill = baseCost + photoCost;

            
            System.out.println("The total bill is $" + totalBill);
        } else {
            System.out.println("Sorry, you can't ride.");
        }

        
        

	}

}
