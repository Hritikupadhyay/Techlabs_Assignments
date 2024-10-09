package com.aurionpro.array;
import java.util.Scanner;


public class SecondLargestElementInArray {
	public static void main(String[] args)  {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the number of elements: ");
		int n = scanner.nextInt();
		int[] arr = new int[n];
		
		System.out.println("Enter the Elements: ");
		for (int i=0;i<n;i++) {
			arr[i]= scanner.nextInt();
		}
		
		Integer first = Integer.MIN_VALUE;
		Integer second = Integer.MIN_VALUE;
		
		for(int num : arr) {
			if (num>first) {
				second = first;
				first = num;
			}
			else if (num>second&& num<first) {
				second=num;
				
			}
			
		}
			if(second!=Integer.MIN_VALUE) {
				System.out.println("2nd largest is: "+ second);
			}else {
				System.out.println("there is no 2nd largest");
			}
		
	}

}
