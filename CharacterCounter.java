import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class CharacterCounter{


    public static void main(String[] args){
        System.out.println("Hello World");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter in a string to see how many of each char is present. ");
        String input = scan.nextLine();
        System.out.println("Results: \n");
        countChars(input);
    }

    public static void countChars(String input){
        Map<Character, Integer> charset = new HashMap<>();
        for(int i = 0; i < input.length(); i++){
            Character n = input.charAt(i);
            if(n == ' ') continue;
            if(charset.containsKey(n)){
                charset.put(n, charset.get(n)+1);
            }
            else{
                charset.put(n, 1);
            }
        }
        printMap(charset);
    }

    public static void printMap(Map<Character, Integer> map){
        for(Character i : map.keySet()){
            Character n = i;
            Integer val = map.get(i);
            System.out.println(n + " - " + val);
        }
    }
}