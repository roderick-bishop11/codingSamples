/*
 * Author: Roderick Bishop
 * 
 * Created: 11/19/2017
 * 
 * When given a set of numbers, the program returns true if the list is sorted 
 * 
 */
import java.util.Scanner;
public class HW12 {

	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		System.out.println("Enter in a list of numbers, the first number is the size of the list.");
		int [] nums = new int[inp.nextInt()];

		for( int i = 0; i < nums.length; i++) {
			nums[i] = inp.nextInt();
		}
		
		
		if(isSorted(nums) == false) System.out.println("The list is not already sorted.");
		else System.out.println("The list is sorted.");

	}

	public static boolean isSorted(int[] a) {
		for(int i = 0; i < a.length-1; i++) {
			if(a[i]>a[i+1]) return false;
		}
		return true;
	}
}

