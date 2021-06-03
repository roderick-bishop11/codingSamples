import Helpers.Party;
import Models.Election;

import java.util.Scanner;

/*  TODO: Figure out alg for voting bias based on popularity --> wip
    TODO: Change up output -???
    todo: simulate how the same election would go with no ranked choice---just declare winner. taking 1st place votes only
    todo: learn about threshold ????
    todo: implement vote adding alg
    todo: use nice thread.sleep to make it better, like a story of some sort. -- ez to do

 */

public class RankedChoiceSimulator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Ranked-Choice Voting Simulator. Here we'll simulate how a county election could go if ranked choice was used." +
                "Enter in the name of your county");
        String name = scan.nextLine();
        System.out.println("Next the program needs to know how the county will lean. This will determine and simulate candidate popularity");
        System.out.println("Enter in D for democratic lean, R for republican lean, Green for green party lean, I for Independent Lean," +
                "and L for libertarian lean. Entering any other character will default the county to non-partisan lean");
        String choice = scan.nextLine();
        Election election = new Election(27000, name, favor(choice));
        election.fillCandidates();
    }

    public static Party favor (String choice){
        switch(choice.toUpperCase()){
            case "R":
                return Party.REP;
            case "D":
                return Party.DEM;
            case "L":
                return Party.LIB;
            case "I":
                return Party.IND;
            case "G":
                return Party.GREEN;
        }
        return Party.NON;
    }
}
