/*----------------------------------------------------------------
 *  Author:        Roderick Bishop
 *  Written:       10/03/2017
 *
 *
 * Given a day of the week and days since given day, print out what day the future day is
 *
 *
 *----------------------------------------------------------------*/
import java.util.Scanner;

public class HW6 {

	public static void main(String[] args) {
		Scanner inp = new Scanner (System.in);

		System.out.println("Welcome to the day of the week calculator! In this program, Sunday is 0, Monday is 1... and Saturday is 6. " );
		System.out.println();

		//Current day is a number between 1 and  6 that indicates the starting day.
		System.out.println("Enter in the current day:");
		int currentDay = inp.nextInt();

		// If and while loop checks to see if the user's input for currentDay is within the bounds to make this program work. 
		if(currentDay>6) {
			while(currentDay>6) {
				System.out.println("Pick a number between 1 and 6 for the days of the week to continue");
				currentDay = inp.nextInt();
			}
		}

		// prints out what the user chose. 
		System.out.println("You chose " + dayOfWeek(currentDay));
		System.out.println();
		System.out.println("Now enter in days since the Current Day");

		// newDay is the number of days since currentDay 
		int newDay = inp.nextInt();

		System.out.println("The current day is " + dayOfWeek(currentDay) + ", " + newDay  +  " days from now will be " + dayOfWeek(currentDay + (newDay%7)));
	}

	// the dayOfWeek helper method takes an int and returns a string for the day of the week. 
	public static String dayOfWeek (int a){
		String b = "";
		if(a == 0) b = "Sunday";
		if(a == 1) b = "Monday";
		if(a == 2) b = "Tuesday";
		if(a == 3) b = "Wednesday";
		if(a == 4) b = "Thursday";
		if(a == 5) b = "Friday";
		if(a == 6) b = "Saturday";

		return b;
	}

}