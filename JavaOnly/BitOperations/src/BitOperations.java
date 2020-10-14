import Exceptions.InvalidChoiceException;
import Exceptions.InvalidNumberException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BitOperations {
    //if i use arrays, then all of my operations are linear.... if i use linkedLists, then all of my operations have better time
//TODO: set up the main in a loop so that I can continually run different numbers
    public static Scanner sc = new Scanner(System.in);
    public static final int MAX_ATTEMPTS = 5;
    public static void  main(String[] args) throws InvalidNumberException, InvalidChoiceException {
        System.out.println("Welcome to the bit operations operator!");
        String num = enterNum();
       String decision = enterChoice();
        BinList binaryList = createList(num);
        selectOP(validateString(decision), binaryList);
    }

    public static String enterNum() throws InvalidNumberException{
        String num = "";
        boolean isValid = false;
        for(int i = 1;i<=MAX_ATTEMPTS;i++){
            System.out.println("First, enter in a binary number");
            num = sc.nextLine();
            try{
                 isValid = validateNumber(num);
            }
            catch(InvalidNumberException e){
           if(i != MAX_ATTEMPTS){
               System.out.println(num + " is an invalid binary number. Please enter in a new number with only 0's and 1's. You have " + (MAX_ATTEMPTS-i) + " attempts left");
           }
           else{
              System.out.println("You have exceeded the number of attempts to enter a correct number. The system will now exit with the following exception:");
               throw new InvalidNumberException(e.getMessage());
           }
            }
        }
        return num;
    }

    //TODO: COMPLETE with try/catch for retries
    public static String enterChoice() throws InvalidChoiceException{
        String choice = "";
        for(int i  = 0; i < MAX_ATTEMPTS; i++){
            System.out.println("Now which operation would you like to do to your number? Enter in \"c\" for circular shift, \"l\" for logical shift, and \"a\" for arithmetic shift");
            choice = sc.next();
            System.out.println("Now enter in a direction that you want to perform the shift in. \"l\" for left and \"r\" for right");
            choice+=sc.next();
            try{
                validateString(choice);
            }
            catch(InvalidChoiceException e){
                
            }
        }
        return choice;
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
    //TODO: Complete validation method with exception thrownw
    //just some basic validation on the decision
    public static String validateString(String dec) throws InvalidChoiceException{
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

    public static boolean validateNumber(String num) throws InvalidNumberException{
        boolean valid = false;
        for(char i: num.toCharArray()){
            if(i != '0' && i != '1'){
                throw new InvalidNumberException(num);
            }
        }
        return true;
    }

    public static BinList createList(String s){
        BinList list = new BinList();
        s.chars().forEach(i -> list.addNode(Character.getNumericValue(i))); //using java streams API to iterate and run the addNode function at the same time.
        return list;
    }
}
