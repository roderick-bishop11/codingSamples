import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class RandomNumbers {
public static void main(String[] args) throws sizeTooSmallException {
    System.out.println("hello world");
    RandomGenerator rand = new RandomGenerator();
    System.out.println("Running getIntegerArray(int):\t "  + Arrays.toString(rand.getIntegerArray(10)));
    System.out.println("Running getIntegerArray(int. int, int):\t "  + Arrays.toString(rand.getIntegerArray(10, -100, 100)));
    System.out.println("Running getIntegerArray(int, int, int, false):\t"  + Arrays.toString(rand.getIntegerArray(5, 0, 7, false)));
    System.out.println("Running getIntegerArray(int, int, int, true):\t"  + Arrays.toString(rand.getIntegerArray(5, 0,4, true)));
    ArrayList<Integer> nums = rand.getIntegerArrayList(7);
    System.out.println("Running getIntegerArrayList(int):\t "  +  nums.toString());
    Vector<Integer> vec = rand.getIntegerVector(7);
    System.out.println("Running getIntegerVector(int):\t "  + vec.toString());
    System.out.println("\nRunning getIntegerArray(int, int, int, false) when range is too small will throw exception....");
    System.out.println(Arrays.toString(rand.getIntegerArray(5, 0, 4, false)));





}


}
