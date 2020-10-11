/*
 * Author: Roderick Bishop
 * 
 * Created on: 2/5/2018
 * 
 * When given a date from the user, the program returns the alphanumeric date. In the case of a date that is out of range, the method dateConverter throws exceptions DayException & MonthException
 * 
 * 
 */
import java.util.*;
public class Homework3 {
	public static void main(String[] args) throws MonthException, DayException{
	  Scanner input = new Scanner(System.in);
	  String date = "";
	  
	  System.out.println("Welcome to Roderick's date converter!");
	  
	  System.out.println("\nEnter a numeric date formatted as month/day or type \"Exit\" to quit.");
	  date = input.next();
	  
	  
	  //while loop to continually accept new dates
	  while(!date.equalsIgnoreCase("Exit"))
	  {
		  System.out.println(dateConverter(date));
		
	   System.out.println("\nEnter a numeric date formatted as month/day or \"Exit\" to quit.");
	   date = input.next();
	  
	  }
	  
	  System.out.println("See ya later!");
	  
	}
	



	public static String dateConverter(String str)  throws DayException, MonthException {
		int day;
		int month;
		String []dateFormat = str.split("/");
		String monthString = "";
		String dayString= "";
		String date="";

		// if the string is too large, this if statement will throw a pseudo-exception and make you do it again
	if(str.length()==6) {
		return "Try again with a valid date. Use the format \"mm/dd\" or \"m/dd\"" ;
	}
	
	month= Integer.parseInt(dateFormat[0]);
	day= Integer.parseInt(dateFormat[1]);
		
		
		// switch statement is located within the try statement for exception handling.
		try {
		
		switch(month) {
		case 1: monthString = "January";
		if(day >=1 && day <=31) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 2:  monthString = "February";
		if(day >=1 && day <= 29) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 3:  monthString = "March";
		if(day >=1 && day <=31) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 4:  monthString = "April";
		if(day >=1 && day <=30) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 5:  monthString = "May";
		if(day >=1 && day <=31) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 6:  monthString = "June";
		if(day >=1 && day <=30) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 7:  monthString = "July";
		if(day >=1 && day <=31) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 8:  monthString = "August";
		if(day >=1 && day <=31) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 9:  monthString = "September";
		if(day >=1 && day <=30) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 10: monthString = "October";
		if(day >=1 && day <=31) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 11: monthString = "November";
		if(day >=1 && day <=30) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
        break;
        
		case 12: monthString = "December";
		if(day >=1 && day <=31) {
			dayString = Integer.toString(day);
		}
		else {
			throw new DayException();
		}
		break;
		default: throw new MonthException() ;
		
		}
		
	}
		catch(DayException e)
		   {
		    date = "";
		   }
		  
		  catch(MonthException e)
		  {
		   date = "";
		  }
		return date = monthString+ " " + dayString;
	}
	
	
	
}


