/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs280.pkgfinal.project;

import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Thread;
/**
 *
 * @author roderickbishop
 */
public class CS280FinalProject {
//TODO: find out how to make a TImerTask to run at the interval
//TODO: find otu how to make the interval for the timertask be specific to the object---BIG ONE
//TODO: test the object creation and methods, 
//TODO: find out how to run through the neighbors, do we need an array to hold them and check?
    
    /**
     * Have a timestamp that will print out the details of each vehicle and a
     * neighbordList(for range of other vehicles) have 10-20 cars every 100 m/s
     * we need s new printout, but each car cannot do it at the same time, so we
     * need random times to printout within the time interval
     */

    //setting up the box that we'll be running on. 
    final int MAX_X = 8000;
    final int MAX_Y = 24;
    public static Vehicle car []=new Vehicle[10];

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        //utilities

        int i;
        
        //these are our cars
    //    car = new Vehicle[10];
      //this is an array of cars so that we can loop through them to eheck neighbors
       // Vehicle[] cars = {car1, car2, car3, car4, car5, car6, car7, car8, car9, car10};
        //timertask is abstract, cannot be instantiated
       // TimerTask carPosition = new TimerTask();
 
//creates the timer for each car and schedule
        System.out.println("****Starting Connected Car Simulation*****");
        Timer time [] = new Timer [10];
        long t[]=new long[10];
        for(i=0;i<=9;i++)
        {
            car[i]=new Vehicle(i); 
            time[i]=new Timer();
            t[i]=(long)(1000*Math.random());
            System.out.println("Vehicle "+i+": start time(ms):"+ t[i]);
            
            //cars run every 1 second
           time[i].schedule(car[i], t[i], 1000);  
            
           //current version
           
           
        }
        
        long start = System.currentTimeMillis();
        long end = start + 20000;
        while (System.currentTimeMillis() < end) {
          
           }
       System.out.println("****Simulation ends*****");
       System.exit(0);
    }
}
    
