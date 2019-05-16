/* Author: Roderick Bishop
 * Date: 2/5/2019
 * Updated 5/15/2019 **V2 Fixes a logic error within the ternary statement of the findIntMethod
 * Program: arrayPractice.java; A program that uses 1D and 2D arrays with values given by the user and performs operations with them using primitives
 *  Operations that are carried out in this program: sum, average,count occurences of values, max, min, and max by row
 *  jagged 2d arrays 
 *  
 *  notes:  1) This time I used a lot of overloaded methods, I would like to find a way to see if there is a way that I can use an alternative to that
 *    2) I will use java.collections next time to do a lot of these operations, I just wanted to show that I could do it the long and hard way
 *    3) I should probably use another class to clean up this one, there is A LOT going on. 
 *    4) I would like to find a way to randomly fill a 2D jagged array,  I think those are cool
 *    5) ArrayLists are superior... I think I'll do one like this with ArrayLists so that I can mutate them throughout the program effortlessly
 *    6) Lastly, I think that I could have entered in numbers into a method that has a variable length parameter list and get an array that way\ 
 */
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class arrayPractice {

 public static void main(String[] args) {
  Scanner input = new Scanner(System.in);
  System.out.println("Welcome to arrayPractice.java. Enter in the size of the 1D array that you want to use: \t");
  int size = input.nextInt();
  int [] arr1D = new int [size];

  System.out.println("Now let's populate that 1D array of ints. Type in " + size + " integer numbers. ");

  for(int i = 0; i<arr1D.length;i++) {
   arr1D[i] = input.nextInt();
  }

  System.out.print("This is your array: ");
  printArray(arr1D);
  System.out.println("Now the program will print the average, sum, min, max, and the row that has the highest sum or elements");
  System.out.println("Sum of elements: " + sum(arr1D));
  System.out.println("Average: " + average(arr1D));
  System.out.println("Min: " + min(arr1D));
  System.out.println("Max: " + max(arr1D));
  System.out.println("------------------------------------------------------------------------------");
  System.out.println();
  System.out.println("Now let's use an array with all random values. First enter how large you want the random array to be");
  int randSize = input.nextInt();
  int [] randArray1D = new int [randSize];
  System.out.println("Now enter in the maximum random value (for use with Math.random()) ");
  int rand = input.nextInt();

  for(int i = 0; i<randArray1D.length; i++){
   randArray1D[i] = (int)(Math.random()*rand);
  }
  System.out.print("Here is the array with random values: ");
  printArray(randArray1D);
  System.out.println("Now the program will print the average, sum, min,and max");
  System.out.println("Sum of elements: " + sum(randArray1D));
  System.out.println("Average: " + average(randArray1D));
  System.out.println("Min: " + min(randArray1D));
  System.out.println("Max: " + max(randArray1D));
  System.out.println("\n \n");
  System.out.println("Now let's try to find a specific number within the random array");
  System.out.println("Type in a number that you'd like to find and the program will tell you what index the number appears in");
  int target = input.nextInt();
  System.out.println("The index(es) where " + target + " shows up are " + findInt(randArray1D,target));
  System.out.println("-------------------------------------------------------------------------------");
  System.out.println();
  System.out.println("Now let's do some 2D array stuff! The program will populate a 2d array for you");
  System.out.println("Enter in the size of the 2Darray, this number will be both the height and width");
  randSize = input.nextInt();
  System.out.println("Now, enter in the maximum value for the elements in the array");
  rand = input.nextInt();
  System.out.println("Here is your 2d Array:");
  int [][] rand2D =  create2DArray(rand, randSize);
  printArray(rand2D);
  System.out.println("Now the program will print the sum, average, max by row, average, minumum value, and max value. ");
  System.out.println("Sum of elements: " + sum(rand2D));
  System.out.println("Average: " + average(rand2D));
  System.out.println("Min: " + min(rand2D));
  System.out.println("Max: " + max(rand2D));
  System.out.println("Maxes within each row: ");
  printArray(maxByRow(rand2D));
  System.out.println("Sums of each row: ");
  printArray(sumByRow(rand2D));

 }
 public static int[][] create2DArray(int max, int size){
  int[][] rand2D = new int[size][size];
  for(int i = 0; i<rand2D.length; i++) {
   for(int j = 0; j<rand2D[i].length; j++) {
    rand2D[i][j] = (int)(Math.random()*max);
   }
  }
  return rand2D;
 }
 public static void printArray(int [] a) {
  for(int num:a) {
   System.out.print(num + " ");
  }
  System.out.println("\n \n");
 }
 

 public static void printArray(int[][]a) {
  for(int row = 0; row<a.length; row++) {
   for(int col = 0; col <a[row].length; col++ ) {

    if(a[row][col]<10) {
     System.out.print(a[row][col] + "  ");
    }
    else {
     System.out.print(a[row][col] + " ");
    }
   }
   System.out.println();
  }
 }
 public static int max(int[]arr) {
  int max = arr[0];
  for(int num:arr) {
   if(num>max) {
    max = num;
   }
  }
  return max;
 }

 public static int max(int[][] nums) {
  int max = nums[0][0];
  for( int i = 0; i<nums.length;i++) {
   for(int j = 0; j<nums[i].length;j++) {
    if(nums[i][j]>max) {
     max = nums[i][j];
    }
   }
  }
  return max;
 }

 public static int min(int[] nums) {
  int min = nums[0];

  for(int num:nums) {
   if(num<min) min = num;
  }
  return min;
 }

 public static int min(int [][] nums) {
  int min = nums[0][0];
  for( int i = 0; i<nums.length;i++) {
   for(int j = 0; j<nums[i].length;j++) {
    if(nums[i][j]<min) {
     min = nums[i][j];
    }
   }
  }
  return min;
 }

 public static int average(int [] a) {
  int length = a.length;
  int sum = 0;
  for(int num:a) {
   sum+=num;
  }
  return sum/length;
 }

 public static int average(int [][] a) {
  int length = a.length*a.length;
  int numSum = 0;
  for(int row = 0; row<a.length; row++) {
   for(int col = 0; col<a[row].length;col++) {
    numSum+= a[row][col];
   }
  }
  return numSum/length;
 }

 public static int sum(int[] a) {
  int sum = 0;
  for(int num:a){
   sum+= num;
  }
  return sum;
 }
 public static int sum(int[][] a) {
  int sum = 0;
  for(int row = 0; row<a.length; row++) {
   for(int col = 0; col<a[row].length;col++) {
    sum+= a[row][col];
   }
  }
  return sum;
 }


 public static List<Integer> findInt(int[] a, int target) {
  List<Integer> nums = new ArrayList<Integer>();
  for(int i = 0; i<a.length;i++) {
   if(a[i] == target) {
    nums.add(i);
   }
  }
  
   return(nums.isEmpty())? Collections.emptyList():nums; 
   /*fancy little boolean expression called a ternary statement that evaluates the statement before the ? 
   then does an if/else with arguments separated by the colon. The if is the Collections.EMPTY_LIST and the else is
   just simplu printing out nums
   */
   
  
 }
 
 public static int[] maxByRow(int[][]arr) {
  int [] maxes = new int [arr[0].length];
  maxes[0] = arr[0][0];
  for(int row = 0; row<arr.length;row++) {
   for(int col = 0; col<arr[row].length;col++) {
    if(maxes[row]<arr[row][col]) {
     maxes[row] = arr[row][col];
    }
   }
  }
  return maxes;

 }

 public static int [] sumByRow(int [][] arr) {
  int [] rowSum = new int[arr[0].length];
  int sum = 0;
  for(int row = 0; row<arr.length; row++) {
   for (int col = 0; col<arr[row].length; col++) {
    sum+=arr[row][col];
   if(col==(arr[row].length-1)) {
    rowSum[row]=sum;
    sum=0;
   }
   }

  }
  return rowSum;
 }


}
