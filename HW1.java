/*
 * Author: Roderick Bishop
 * 
 * Created: 1/20/2018
 * 
 * Given two integers from the user, the program recursively finds the greatest common divisor. 
 */
import java.util.Scanner;

public class HW1 {

	public static void main(String[] args) {
		
		System.out.println("Input two integers to find out their Greatest Common Divisor");
		
		Scanner input = new Scanner(System.in);
		
		int m = input.nextInt();																	   //1st integer
		int n = input.nextInt();																	   // 2nd integer

		
		System.out.println( "The greatest common divisor of " + m + " and " + n + " is " + GCD(m,n));  //Print statement prints out the GCD to the console using GCD method

	}


																									   // Method returns the GCD of two integers, using recursion. 
	public static int GCD(int a, int b) {
		if((a % b) == 0) 
			return b;
		else 
			return GCD(b, a % b);
	}

}
