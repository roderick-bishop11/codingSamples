/*
 * Author: Roderick Bishop
 * 
 * Created: 2/19/2018
 * 
 * The program prints out a list of all prime numbers less than or equal to 100,000
 * 
 * 
 */

import java.util.Iterator;

//class PrimeIterator with a constructor that takes in an int. 
public class PrimeIterator implements Iterator {

	private int number;
	private int currentNum;
	public PrimeIterator(int number) {
		
		this.number = number;
	}
	
	

	
//hasNext checks if the currentNum is <= number and if the next number is prime, otherwise it goes up to the next number. 	
	@Override
	public boolean hasNext() {
		currentNum++;
		while (true) {
		 if (isPrime(currentNum))
	          break;
	        currentNum++;
	      }
	      
	      if (currentNum >= number)
	        return false;
	      else 
	        return true;
	}
	
	//next() is a method that is apart of the Iterator Interface and we have to have it. it returns the current number
	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return currentNum;
	}
	
	
	//isPrime checks if the currentNum is prime, returns true if the remainder after dividing currentNum by i is 0. 
	static boolean isPrime(int x) {
		for(int i = 2;i<x;i++) {
			if(x%i==0)
				return false;
		}
		
		return true;
		
	}
	
	public static void main(String[] args) {
		 Iterator<Integer> it = new PrimeIterator(100000);
		    while (it.hasNext()) {
		      System.out.println(it.next());
	}
	
	
}
}
