import java.util.Scanner;

public class StringToHex{

public static void main (String[] args ){
Scanner np = new Scanner(System.in);
System.out.println("Enter in a string to print out the hex value: ");
String in = np.nextLine();
System.out.print("Your string \""+ in + "\" comes out to ");
printHex(in);
System.out.print(" in hex.\n");
}

public static void printHex(String s ){
    for(char c : s.toCharArray()){
        if(c == ' '){
            System.out.print(" ");
        }
    System.out.format("%H", c);
    }
}




}