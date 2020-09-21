/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbevotecounter;
import java.util.*;
/**
 *
 * @author New PC
 */
public class NSBEVoteCounter {


 
    public static void main(String[] args) {
        //using hashmaps to haev a key (candidate name) and a value (votes)
        HashMap<String, Double> pres = new HashMap<String, Double>();
        addNames(pres, 0);
        countVotes(pres);
        System.out.println("The results of the Presidential election are: ");
        print(pres);
        HashMap<String, Double> vice =  new HashMap<String, Double>();
          addNames(vice, 1);
        countVotes(vice);
        System.out.println("The results of the election for Vice President are: ");
        print(vice);
        HashMap<String, Double> senator = new HashMap<String, Double>();
          addNames(senator, 2);
        countVotes(senator);
        System.out.println("The results of the election for senator are: ");
        print(senator);
    }
    
    public static void addNames(HashMap<String, Double> m, int message){
        boolean quit = false;
        Scanner scan = new Scanner(System.in);
        String temp;
        int count;
        switch(message){
            case 0:
                System.out.println("Enter in the names of the first candidate for President. Press \"q\" when you're done");
               
                break;
            case 1:
                System.out.println("Enter in the name of the first candidate for Vice President. Press \"q\" when you're done");
         
                break;
            case 2:
                System.out.println("Enter in the names of the first candidate for Senator. Press \"q\" when you're done");
                
                break;
            default:
                System.out.println("C'mon man, start the program over...");
             
        }
        //while loop to get as many candidates in the hashmap as you need
        while(!quit){
        temp = scan.next();     
        if(temp.equalsIgnoreCase("q")) break;
        m.put(temp, null); //temp's value is null for right now.
        System.out.println("Enter in the name of the next candidate. Enter \"q\" when you're done.");
        }
    }
    //this will count votes for each candidate, pass in both name, category and votes
    public static void countVotes(HashMap<String, Double> candidates){
        Scanner scan = new Scanner(System.in);
        double igVotes, groupMe;
        double totalVotes;
        //instagram votes are discounted by 50%
        
        for(String name: candidates.keySet()){
            System.out.println("Enter in " + name + "'s votes from instagram");
            igVotes = scan.nextDouble();
            System.out.println("Enter in " + name + "'s votes from GroupMe");
            groupMe = scan.nextDouble();
            totalVotes = Math.ceil(igVotes*.5) + groupMe;
            candidates.replace(name, totalVotes);
        }
   
    }
    
    public static void print(HashMap<String, Double> m){
        System.out.print(Collections.singleton(m) + "\n\n");
    }
    
}
