/*----------------------------------------------------------------
 *  Author:        Roderick Bishop
 *  Written:       10/1/2017
 *
 *
 * With a given principle, the program calculates total value of savings after the period. 
 *
 *
 *----------------------------------------------------------------*/
import java.util.Scanner; 
import java.text.DecimalFormat;

public class Homework5 {


	public static void main(String[] args) {

		double principle, period,rate,simpleInterest,accountValue;
// quick and dirty group declaration of my variables for the program

		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to the interest calculator!");
		
		System.out.println("Enter the amount you'll save: ");
		principle = input.nextDouble();
// Principle the user starts out with, and adds to the pot each month 
		
		System.out.println("Now input the period for which you will be saving in months: ");
		period = input.nextInt() ;
// Time in which the interest is paid over (in months).
		
		System.out.println("Now input the rate of interest in percent per month");
		rate = input.nextDouble();
		rate = rate/100;
//Rate of interest divided by time.

		simpleInterest = principle*((1+ (rate*period)));

		DecimalFormat df = new DecimalFormat("$###,###.##");
		
		System.out.println("After " + (int)period + " months, and accruing interest at a rate of " + rate*100 +" percent each month, your account value is: " + df.format(simpleInterest));


	}

}
