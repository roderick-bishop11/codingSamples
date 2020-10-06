import java.util.Scanner;
public class BitOperations {
    //if i use arrays, then all of my operations are linear.... if i use linkedLists, then all of my operations have better time

    public static void  main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the bit operations operator!\nFirst, enter in a binary number");
        String num = sc.nextLine();
        System.out.println("Now which operation would you like to do to your number? Enter in \"c\" for circular shift, \"l\" for logical shift, and \"a\" for arithmetic shift");
        String decision = sc.next();
        System.out.println("Now enter in a direction that you want to perform the shift in. \"l\" for left and \"r\" for right");
        decision+=sc.next();
        BinList binaryList = createList(num);
        selectOP(decision, binaryList);
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
        }
    }
    //TODO: complete validation method, remove spaces and make sure only the letters in the set of permissible values are in there
    //just some basic validation on the inputs
    public void validateString(String dec){

    }

    public static void validateNumber(String num){

    }

    public static BinList createList(String s){
        BinList list = new BinList();
        s.chars().forEach(i -> list.addNode(Character.getNumericValue(i))); //using java streams API to iterate and run the addNode function at the same time.
        return list;
    }
}
