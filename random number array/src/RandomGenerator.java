import java.lang.Math;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class RandomGenerator {

    public RandomGenerator() {
    }

    public int[] getIntegerArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100) + 1;
        }
        return arr;
    }

    public int[] getIntegerArray(int size, int from, int to) {
        int[] arr = new int[size];
        int range = (to - from) + 1; // this gives us a range of numbers
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * range) + from;
        }
        return arr;
    }

    /*i chose to use set so that I can prevent duplicates from even going into the collection. It's better than allowing them
    and then removing them and running again.
    */
    public int[] getIntegerArray(int size, int from, int to, boolean allowDup) throws sizeTooSmallException {
        int range = to - from;
        if (range < size && allowDup == false) {
            throw new sizeTooSmallException("the range(" + range + ") is smaller than the size(" + size + ") for a nonduplicating set, try again with a higher range");
        }
        Set<Integer> nums = new HashSet<>(); //sets cannot allow duplicates
        int[] withDups = new int[size]; //arrays can have duplicates
        int rand = 0;
        for (int i = 0; i < size; i++) {
            rand = (int) (Math.random() * (range) + 1) + from;
            if (!allowDup) { // if we are not allowing duplicates then allowDup is false
                if (!nums.contains(rand)) {
                    nums.add(rand);
                } else {
                    //keep trying a new number until you find one that works
                    while (nums.contains(rand)) {
                        rand = (int) (Math.random() * (range) + 1) + from;
                    }
                    nums.add(rand);
                }
            } else {
                withDups[i] = rand;
            }
        }
        return nums.size() > 0 ? toIntArray(nums) : withDups;
    }

    public ArrayList getIntegerArrayList(int size) {
        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int rand = (int) (Math.random() * 100) + 1;
            nums.add(rand);
        }
        return nums;
    }

    public Vector getIntegerVector(int size) {
        Vector<Integer> nums = new Vector<>();

        for (int i = 0; i < size; i++) {
            int rand = (int) (Math.random() * 100) + 1;
            nums.add(rand);
        }
        return nums;
    }


    //util
    public int[] toIntArray(Set<Integer> a) {
        Integer[] arr = a.stream().toArray(Integer[]::new); //using java streams API from java 8
        int index = 0;
        int[] ans = new int[arr.length]; //create an int array the same size as Integer array
        for (Integer i : arr) {
            ans[index++] = i; //this will set the value of i to the index then postincrement
        }
        return ans;
    }
}
