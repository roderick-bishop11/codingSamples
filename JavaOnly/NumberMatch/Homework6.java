/*
 * Author: Roderick Bishop
 * 
 * Created on: 2/25/2018
 * 
 * When given numbers by the user, the program awards points based on how many numbers match.  
 * 
 */

import java.util.*;
public class Homework6 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean quit = false;
		List<Integer> userNums = new ArrayList<Integer>();
		List<Integer> compNums = new ArrayList<Integer>();
		List<Integer> sortedUserNums = new ArrayList<Integer>();
		int score = 0;
		Integer compNum1 = (int)(Math.random()*10);
		Integer compNum2 = (int) (Math.random()*10);
		Integer compNum3 = (int)(Math.random()*10);
		Integer userNum1 = 0;
		Integer userNum2 = 0;
		Integer userNum3 = 0;

		//sorts compNums so that they can be compared
		compNums.add(compNum1);
		compNums.add(compNum2);
		compNums.add(compNum3);
		Collections.sort(compNums);

		System.out.println("Welcome to the lottery! Enter in 3 numbers,1-10 to see if you have won! Type \"quit\" to exit. ");
		String inp= input.next();


		if(inp.equalsIgnoreCase("quit")) {
			System.out.println("See you later!");
			System.exit(0);
		}

		else {
			userNum1 = Integer.parseInt(inp);
			userNums.add(userNum1);
			sortedUserNums.add(userNum1);
			userNum2 = input.nextInt();
			userNums.add(userNum2);
			sortedUserNums.add(userNum2);
			userNum3 = input.nextInt();
			userNums.add(userNum3);
			sortedUserNums.add(userNum3);
		}
		Collections.sort(sortedUserNums);
		
		//system.exit so that score doesn't change if another criteria in another if statement is met
		if(compNums.equals(userNums)) {
			score=10000;
			System.out.println("Congratulations, you matched all three exactly!!! Your score is " + score);
			System.exit(0);
		}
		else if(compNums.containsAll(userNums)){
			score = 3000;
			System.out.println("All the digits match! your score is "+ score);
			System.exit(0);
		}
		else if(compNums.containsAll(sortedUserNums.subList(0,2))||compNums.containsAll(sortedUserNums.subList(1, sortedUserNums.size()))){
			score = 2000;
			System.out.println("Two of your digits matched. Your score is " + score);
			System.out.println("The lottery numbers were:" + compNums);
			System.exit(0);
		}
		else if(compNums.contains(userNum1)||compNums.contains(userNum2)||compNums.contains(userNum3)) {
			score = 1000;
			System.out.println("Only one digit matches. " + score);
			System.out.println("The numbers were: " + compNums);
			System.exit(0);
		}
		else {
			System.out.println("Sorry, try again next time! The numbers were: " + compNums);

		}



	}






}
