import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BitOperations {
    //if i use arrays, then all of my operations are linear.... if i use linkedLists, then all of my operations have better time
//TODO: set up the main in a loop so that I can continually run different numbers
    public static void  main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the bit operations operator!\nFirst, enter in a binary number");
        String num = sc.nextLine();
        validateNumber(num);
        System.out.println("Now which operation would you like to do to your number? Enter in \"c\" for circular shift, \"l\" for logical shift, and \"a\" for arithmetic shift");
        String decision = sc.next();
        System.out.println("Now enter in a direction that you want to perform the shift in. \"l\" for left and \"r\" for right");
        decision+=sc.next();
        BinList binaryList = createList(num);
        selectOP(validateString(decision), binaryList);
    }

    public static void selectOP(String s, BinList list){
        switch(s){
            case "cr":
                System.out.println("You chose to circularly shift right and your new value is: \t");
                list.circShift(Direction.RIGHT);
                break;
            case "cl":
                System.out.println("You chose to circularly shift left and your new value is: \t");
                list.circShift(Direction.LEFT);
                break;
            case "al":
                System.out.println("You chose to arithmetically shift left and your new value is: \t");
                list.arithShift(Direction.LEFT);
                break;
            case "ar":
                System.out.println("You chose to arithmetically shift right and your new value is: \t");
                list.arithShift(Direction.RIGHT);
                break;
            case "lr":
                System.out.println("You chose to logically shift right and your new value is: \t");
                list.logiShift(Direction.RIGHT);
                break;
            case "ll":
                System.out.println("You chose to logically shift left and your new value is: \t");
                list.logiShift(Direction.LEFT);
                break;
            case "q":
                System.out.println("Thanks for using the bit operations program!");
                System.exit(0);
        }
    }
    //TODO: complete validation method, remove spaces and make sure only the letters in the set of permissible values are in there
    //just some basic validation on the decision
    public static String validateString(String dec){
        Character[] chars = {'l','r','c','a','q'};
        Set<Character> validChars = new HashSet<Character>(Arrays.asList(chars));
        StringBuilder validated = new StringBuilder();
        //we want to remove spaces,
        for(char i: dec.toCharArray()){
            if(i == ' '){
                System.out.println("Space skipped");
                continue; //skip the spaces

            }

            //add the exception here
            if(!validChars.contains(i)){
                System.out.println("You entered a bad number, try again"); //we want to throw the exception here
                break;
            }
            else{
                validated.append(i); //add it to the validated string
            }
        }
        return validated.toString();
    }

    //TODO: validate that the number is a BINARY number, will use exceptions here
    public static void validateNumber(String num){
        for(char i: num.toCharArray()){
            //throw exception here
            if(i != '0' && i != '1'){
                System.out.println("You didn't enter a binary number. Try again");
                break;
            }
        }
    }

    public static BinList createList(String s){
        BinList list = new BinList();
        s.chars().forEach(i -> list.addNode(Character.getNumericValue(i))); //using java streams API to iterate and run the addNode function at the same time.
        return list;
    }
}
