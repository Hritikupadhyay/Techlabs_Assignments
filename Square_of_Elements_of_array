package com.aurionpro.array;
import java.util.Arrays;
import java.util.Scanner;

public class SortedSquaresOfSortedInputs {
 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of elements in array: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter elements of array (sorted): ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int index = n - 1;

        
        while (left <= right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];

            if (leftSquare > rightSquare) {
                result[index--] = leftSquare;
                left++;
            } else {
                result[index--] = rightSquare;
                right--;
            }
        }
        System.out.println("Sorted squares: " + Arrays.toString(result));
    }

		
}

