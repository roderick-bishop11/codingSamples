/* Author: Roderick Bishop
 * Date: 1/31/2019
 * Program: codingMadLibProgram.java; The java version of codingMadLibProgram.cs and .py. This program allows the user to make up their own story by inputting parts of speech to make a story. This is a very straightforward
 *                                   way of doing the mad lib and will be revisited with more advanced text formatting, story, etc.  
 */
package codingMadLibProgram;
import java.util.*;	

public class CodingMadLib {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String name1, name2, place, verb1, food1, food2, verb2, adjective, adjective2;

		System.out.println("Welcome to the Java coding madLib! First, read the story and type in the parts of speech and see your finished story!");
		System.out.println("Story: \n {Name1} and {name2} went to the {place} to {verb1}. \r After that, they went to eat {food1} and {food2} and the food was {adjective1}. \r To close out their day together, {name1} and {name2} went {verb 2/gerund} and had an {adjective} time! \n");

		System.out.println("Insert the first name that you want to use");
		name1 = scan.nextLine();

		System.out.println("Insert the second name that you want to use");
		name2 = scan.nextLine();

		System.out.println("Insert the place that you want to use");
		place = scan.nextLine();

		System.out.println("Insert the first verb name that you want to use");
		verb1 = scan.nextLine();

		System.out.println("Insert the first food item that you want to use");
		food1 = scan.nextLine();

		System.out.println("Insert the second food item that you want to use");
		food2 = scan.nextLine();

		System.out.println("Insert the first adjective that you want to use");
		adjective = scan.nextLine();

		System.out.println("Insert the second verb that you want to use");
		verb2 = scan.nextLine();

		System.out.println("Insert the second adjective that you want to use");
		adjective2 = scan.nextLine();

		System.out.println("Here is your finished madLib! \n");
		System.out.println(name1.substring(0,1).toUpperCase() + name1.substring(1) + " and " + name2 + " went to the " + place+ " to " + verb1 + ". \r After that, they went to eat " + food1 + " and " + food2 + " and the food was " + adjective + ". \r To close out their day together, " + name1 + " and " + name2 + " went " + verb2 + " and had an " + adjective + " time! \n");
	}
}
