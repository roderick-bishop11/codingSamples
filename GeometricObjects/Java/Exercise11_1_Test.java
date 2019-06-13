/* Author: Roderick Bishop
*Class: CS 170-01
*Assignment 4
* Due Date: April 8th, 2019, 2:00 PM
* I certify that this lab is entirely my own work
*/
import java.util.*;

public class Exercise11_1_Test{


  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);

    System.out.println("Welcome to the triangle creator!");
    System.out.println("Enter in three numbers for the sides of the triangle we are creating");

    double s1 = scan.nextDouble();
    double s2 = scan.nextDouble();
    double s3 = scan.nextDouble();

    Triangle t1 = new Triangle(s1, s2, s3);

    System.out.println("Now enter in a color");
    String color = scan.next();

    System.out.println("Enter in true or false for whether the triangle is filled or not");
    boolean fill = scan.nextBoolean();

    t1.setColor(color);
    t1.setFilled(fill);


    System.out.println(t1);
    System.out.println("Area is:\t" + t1.getArea() + " units squared.");
    System.out.println("Perimiter is:\t" + t1.getPerimeter() + " units,");
    System.out.println("Color:\t" + t1.getColor());
    System.out.println("isFilled?:\t " + t1.isFilled());


  }
}
