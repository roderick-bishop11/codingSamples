/*
Author: @RoderickBishop

Last modified: 10-14-2020


BitOperations program. Idea came from my Intro to Computer Organization class, taken at SCSU in tha fall of 2020.
This program does all of the basic bit shift operations: logical, arithmetic, and circular shifts.

Enjoy!!!
 */
import Exceptions.InvalidChoiceException;
import Exceptions.InvalidNumberException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BitOperations {
    public static Scanner sc = new Scanner(System.in);
    public static final int MAX_ATTEMPTS = 5;

    public static void main(String[] args) throws InvalidNumberException, InvalidChoiceException {
        System.out.println("Welcome to the bit operations operator! At anytime enter \"q\" to quit");
        while (true) { //this will terminate if you enter in a q
            String num = enterNum();
            String decision = enterChoice();
            BinList binaryList = createList(num);
            selectOP(decision, binaryList);
        }
    }

    public static String enterNum() throws InvalidNumberException {
        String num = "";
        boolean isValid = false;
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            System.out.println("\n\nEnter in a binary number");
            num = sc.nextLine();
            try {
                isValid = validateNumber(num);
                if(isValid) break;
            } catch (InvalidNumberException e) {
                if (i != MAX_ATTEMPTS) {
                    System.out.println(num + " is an invalid binary number. Please enter in a new number with only 0's and 1's. You have " + (MAX_ATTEMPTS - i) + " attempts left");
                } else {
                    System.out.println("You have exceeded the number of attempts to enter a correct number. The system will now exit with the following exception:");
                    throw new InvalidNumberException(e.getMessage());
                }
            }
        }
        return num;
    }

    public static String enterChoice() throws InvalidChoiceException {
        String choice = "";
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            System.out.println("Now which operation would you like to do to your number? Enter in \"c\" for circular shift, \"l\" for logical shift, and \"a\" for arithmetic shift");
            choice = sc.next();
            checkForQuit(choice);
            System.out.println("Now enter in a direction that you want to perform the shift in. \"l\" for left and \"r\" for right");
            choice += sc.next();
            try {
                choice = validateString(choice);
                break; //breaks out if there is no issue
            } catch (InvalidChoiceException e) {
                if (i != MAX_ATTEMPTS) {
                    System.out.println(choice + " is an invalid operation choice. Please retry. You have " + (MAX_ATTEMPTS - i) + " attempts left");
                } else {
                    System.out.println("You ran out of permissable attempts. The program will now exit.");
                    throw new InvalidChoiceException(e.getMessage());
                }
            }
        }
        return choice;
    }

    private static void checkForQuit(String choice) {
        if(Character.toLowerCase(choice.charAt(0)) == 'q') {
            System.out.println("Thanks for using the bit operations program!");
            System.exit(0);
        }
    }

    public static void selectOP(String s, BinList list) {
        switch (s) {
            case "cr":
                System.out.print("You chose to circularly shift right and your new value is: \t");
                list.circShift(Direction.RIGHT);
                break;
            case "cl":
                System.out.print("You chose to circularly shift left and your new value is: \t");
                list.circShift(Direction.LEFT);
                break;
            case "al":
                System.out.print("You chose to arithmetically shift left and your new value is: \t");
                list.arithShift(Direction.LEFT);
                break;
            case "ar":
                System.out.print("You chose to arithmetically shift right and your new value is: \t");
                list.arithShift(Direction.RIGHT);
                break;
            case "lr":
                System.out.print("You chose to logically shift right and your new value is: \t");
                list.logiShift(Direction.RIGHT);
                break;
            case "ll":
                System.out.print("You chose to logically shift left and your new value is: \t");
                list.logiShift(Direction.LEFT);
                break;
            case "q":
                System.out.println("Thanks for using the bit operations program!");
                System.exit(0);
        }
    }

    //just some basic validation on the decision
    public static String validateString(String dec) throws InvalidChoiceException {
        Character[] chars = {'l', 'r', 'c', 'a', 'q'};
        Set<Character> validChars = new HashSet<Character>(Arrays.asList(chars));
        StringBuilder validated = new StringBuilder();
        checkForQuit(dec); //checking for quit.
        for (char i : dec.toCharArray()) {
            if (i == ' ') {
                System.out.println("Space skipped");
                continue; //skip the spaces
            }
            //if the character is not a space and is not in the set of permissable chars.
            if (!validChars.contains(Character.toLowerCase(i))) {
                throw new InvalidChoiceException(dec);
            } else {
                validated.append(Character.toLowerCase(i)); //add it to the validated string
            }
        }
        return validated.toString();
    }

    public static boolean validateNumber(String num) throws InvalidNumberException {
        for (char i : num.toCharArray()) {
            if (num.length() == 1 && Character.toLowerCase(i) == 'q') { //for the ability to quit while entering a number
                System.out.println("Thanks for using the bit operations program!");
                System.exit(0);
            }
            if (i != '0' && i != '1') {
                throw new InvalidNumberException(num);
            }
        }
        return true;
    }

    public static BinList createList(String s) {
        BinList list = new BinList();
        s.chars().forEach(i -> list.addNode(Character.getNumericValue(i))); //using java streams API to iterate and run the addNode function at the same time.
        return list;
    }
}
