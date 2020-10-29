/*----------------------------------------------------------------
 *  Author:        Roderick Bishop
 *  Written:       9/21/2017
 *
 *
 * Using a number entered in by the user, prints out the sum of the individual digits of that integer
 *
 *
 *----------------------------------------------------------------*/
import java. util.Scanner;
public class HomeWork4 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Type an integer from 0 to 1000");

		int userInput= input.nextInt();
		int sum = 0;


		// while loop recursively extracts the last digit and adds it to the sum. Uses two instance variables, newNumber & i to do it. 
		while (userInput!=0) {
			// if/else checks if the number is within the 1 to 1000 constraints.
			if(userInput<=1000){
				// i is the extracted digit
				int i; 
				// newMumber is the new number after extracting the digit
				int newNumber; 
				i = userInput%10;
				newNumber = userInput/10;
				sum+=i;
				i=newNumber;
				//assigns newNumber value to userInput to satisfy the while loop when the extracting is done
				userInput = newNumber;
			}
			// if number is not in the constraints then thsi allows the user to try again
			else { 
				System.out.println("That number is not from 0 to 1000, enter in a new number");
				userInput = input.nextInt();

			}

		}
		System.out.println("The sum of the digits is "+ sum);
	}
}
