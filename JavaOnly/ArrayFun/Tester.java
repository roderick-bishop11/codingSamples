import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Author: Roderick Bishop
 * 
 * Created on: 1/29/2018
 * 
 * Given various values by the user, executes the methods in 19.6-19.9.
 * 
 * modified on: 9/30/2020 as follows: 
 * 
 * Description: More specifically, takes in values from the users and runs max, min, and binary searche functions on 
 * user filled arrays  
 * 
 */

public class Tester {

	public static void main(String[] args) {


		ArrayList <Integer> intList = new ArrayList<>();
		ArrayList <String> stringList = new ArrayList<>();
		Integer [] nums1d = new Integer[4]; 
		Integer[][] nums2d = new Integer[3][3]; 

		Scanner input = new Scanner(System.in);

		System.out.println("First enter in 4 digits for nums 1d");

		// adds values to nums1d
		for(int i = 0; i< nums1d.length; i++) {
			nums1d[i] = input.nextInt();
		}
		//// sorts nums1d before using binarySearch		
		Arrays.sort(nums1d);
		System.out.println("Your sorted numbers in nums1d are " + Arrays.toString(nums1d));

		// adds words entered by the user to strings1d
		System.out.println("Now enter 4 words ");
		String input1 = input.next();
		String input2 = input.next();
		String input3 = input.next();
		String input4 = input.next();

		String[] strings1d= {input1, input2, input3, input4};

		System.out.println("now enter in a number you'd like to search for in nums1d");
		Integer iKey = input.nextInt();
		System.out.println("binarySearch(nums1d," + iKey + ") returns: "+ binarySearch(nums1d,iKey));

		System.out.println("Now enter in a word that you'd like to search for in strings1d");
		String sKey = input.next();
		// sorts strings1d before using binarySearch		
		Arrays.sort(strings1d);
		System.out.println("Your sorted words are: ");
		for(String word: strings1d) 
			System.out.println(word);
		System.out.println("binarySearch(strings1d, " + sKey + ") returns: " +  binarySearch(strings1d,sKey));



		// for loop for adding values to nums2d 
		System.out.println("Now enter in 9 numbers for nums2d ");
		for(int i = 0; i< nums2d.length;i++) {
			for(int j = 0; j < nums2d[i].length; j++){
				nums2d[i][j] = input.nextInt();
			}
		}

		System.out.println("Your numbers are " + Arrays.deepToString(nums2d));
		System.out.println("findMax(nums2d) returns: "+ findMax(nums2d));


		//adds values to strings2d
		System.out.println("Now enter in 6 words for strings2d ");
		String str1 = input.next();
		String str2 = input.next();
		String str3 = input.next();
		String str4 = input.next();
		String str5 = input.next();
		String str6 = input.next();

		String[][] strings2d = { {str1, str2, str3} , {str4, str5, str6}};	
		System.out.println("The max value in strings2d is: " + findMax(strings2d));
		System.out.println("The max value in nums2d is: " + findMax(nums2d));


		// adds values to intList
		System.out.println("Now populate intList with 4 whole number integers");
		Integer intList1 = input.nextInt();
		Integer intList2 = input.nextInt();
		Integer intList3 = input.nextInt();
		Integer intList4 = input.nextInt();
		intList.add(intList1);
		intList.add(intList2);
		intList.add(intList3);
		intList.add(intList4);

		// sorts and shuffles intLst
		System.out.println("Your original intList is: " + intList);
		System.out.println("Your sorted intList is: ");
		sort(intList);
		System.out.println(intList);
		System.out.println("Your shuffled list is: ");
		shuffle(intList);
		System.out.println(intList);


		// adds values to stringList
		System.out.println("Lastly, enter in 4 words to populate stringList");
		String stringList1 = input.next();
		String stringList2 = input.next();
		String stringList3 = input.next();
		String stringList4 = input.next();

		stringList.add(stringList1);
		stringList.add(stringList2);
		stringList.add(stringList3);
		stringList.add(stringList4);

		// sorts and shuffles intList
		System.out.println("Your original stringList is: " + stringList);
		System.out.println("Your sorted stringList is: ");
		sort(stringList);
		System.out.println(stringList);
		System.out.println("Your shuffled list is: ");
		shuffle(stringList);
		System.out.println(stringList);

	}


	// Generically finds the max of a 2d array

	public static <E extends Comparable<E>> E findMax(E[][] list) {
		E max = list[0][0];
		for(int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].length; j++) {
				if (list[i][j].compareTo(max) > 0)
					max = list[i][j];
			}			
		}

		return max;
	}

	// Generically searches a sorted 1d array
	public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
		int low = 0;
		int high = list.length - 1;
		int mid;

		while (low <= high) {
			mid = (low + high) / 2;

			if (list[mid].compareTo(key) < 0) {
				low = mid + 1;
			} else if (list[mid].compareTo(key) > 0) {
				high = mid - 1;
			} else {
				return mid;
			}
		}

		return -1;
	}

	// Generically shuffles an arrayList using a temp value and basic swap algorithm
	public static <E> void shuffle(ArrayList<E> list) {
		for(int i = 0; i< list.size();i++) {
			int element = (int)(Math.random()*list.size());
			E temp = list.get(i);
			list.set(i, list.get(element));
			list.set(element, temp);
			// You could also use Collections.shuffle(list);
		}
	}

	//Generically sorts an arrayList
	public static <E extends Comparable<E>>void sort(ArrayList<E> list){
		E temp;
		for(int i = 0; i < list.size();i++) {
			for(int j = 0; j<list.size()-1; j++) {
				int currentIndex = j; 
				int nextIndex = j+1;
				if(list.get(nextIndex).compareTo(list.get(currentIndex)) > 0){
					temp = list.get(currentIndex);
					list.set(currentIndex, list.get(nextIndex));
					list.set(nextIndex, temp);
				}
			}
		}
	}


}
