import java.util.Scanner;

import BinList.Direction;
public class BitOperations {
    //if i use arrays, then all of my operations are linear.... if i use linkedLists, then all of my operations have better time

    public static void  main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the bit operations operator!\nFirst, enter in a binary number");
        String num = sc.nextLine(); 
        System.out.println("Now which operation would you like to do to your number? Enter in \"c\" for circular shift, \"l\" for logical shift, \"a\" for arithmetic shift, and \"s\" for a regular bit shift");
        String decision = sc.next();
        System.out.println("Now enter in a direction that you want to perform the shift in. \"l\" for left and \"r\" for right");
        decision+=sc.next();
    }

  public void selectOP(String s, BinList list){
      switch(s){
          case "cr":
          list.circShift(Direction.RIGHT);
          case "cl":
          list.circShift(Direction.LEFT);
          case "al":
          list.arithShift(Direction.LEFT);

          case "ar":
          list.arithShift(Direction.RIGHT);

          case "lr":
          list.logiShift(Direction.RIGHT);

          case "ll":
          list.logicShift(Direction.LEFT);

          case "sr":
          list.regShift(Direction.RIGHT);

          case "sl":
          list.regShift(Direction.LEFT);

      }
  }
}
