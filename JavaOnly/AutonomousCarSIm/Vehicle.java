/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs280.pkgfinal.project;
import static cs280.pkgfinal.project.CS280FinalProject.car;
import java.lang.Math;
import java.sql.Timestamp;
import java.util.TimerTask;
import java.util.Date;
import java.util.Timer;
import java.text.DecimalFormat;
/**
 *
 * @author roderickbishop
 */
//the vehicle objects come from here
public class Vehicle extends TimerTask {
    
    DecimalFormat f = new DecimalFormat("#,###.###");
    
    //is there a way to run this 20 times?
    @Override
    public void run(){
        Timestamp ctime= new Timestamp(System.currentTimeMillis());
       System.out.println("\nBeacon Interval: "+beacon_interval);
       beacon_interval++;
       System.out.println("Vehicle " + vid + ":"+ "(x="+f.format(x)+",y="+f.format(y)+")"+ "at "+ ctime);
        updatePos();
        updateNeighborTable();
        System.out.println("Neighbor Table:");
        display();
        System.out.println("\n");
        
    }

    private int vid; //vehicle ID 
    private double x; //x-cordinate 
    private double y; //y-coordinate 
    private double speed;
    private double acc; //acceleration
    private double ts; //timestmap
    private int beacon_interval=1;
    public NeighborTable neighbors=new NeighborTable();
    public Vehicle(int id) {
        vid = id;
        x = randomPosX();
        y = randomPosY();
        ts = System.currentTimeMillis();
        speed = speed();
        acc = accel();
                  
    }

    public double randomPosX() {
        double position = Math.random() * 8000;
        return position;
    }

    public double randomPosY() {
        double position = Math.random() * 24;
        return position;
    }

    public double speed() {
        return (Math.random() * 70) + 40;
    }

    public double accel() {
        return (Math.random() * 4) + 2;
    }

    //calculates the euclidian distance for the current car
    public void checkNeighbor(Vehicle veh1){
       double dist = (Math.sqrt((Math.pow((this.x-this.y),2)+ Math.pow((veh1.x-veh1.y), 2))));
        if(Math.abs(dist) >=300 ){
            this.neighbors.addNeighbor(veh1);
        }
        else{
            this.neighbors.removeNeighbor(veh1);
        }
            
}
    //runs through the array of cars(named car) and checks to be added to "this" object
    public void updateNeighborTable()
    {
      int i;
      for(i = 0; i< car.length; i++){
            if(this.vid!=i)
            {
                
                checkNeighbor(car[i]);
            }
        
    }
   }
       public void display()
       {
           this.neighbors.showNeighborTable();
       }
               
 
        public void updatePos(){
            this.speed = (this.speed + this.acc*0.1);
         //   this.x = this.x + this.speed*.1;
            this.x = this.x + this.speed*0.1;
            ///if the new position is greater than 8000, then bring it back to the start
            if(this.x > 8000)this.newStart();
        }
        
        
        //this method will start the new car within the first half of our 8km track with a new accel
    public void newStart(){
        this.x = Math.random()*4000;
        this.y = Math.random()*12;
        this.acc = (Math.random()*4)+2;
    }
    
    
    
    //getPosX- double
    public double getPosX(){
        return this.x;
    }
    
    //getPosY-double
    public double getPosY(){
        return this.y;
        
    }
    
    
    //getSpeed- double
    public double getSpeed(){
        return this.speed;
                
    }
    
    //getAcc- double
    public double getAcc(){
        return this.acc;
    }
    
    //getTime- double
    public double getTime(){
        return this.ts;
    }
            
    
    //getID- int
    public int getID(){
        return this.vid;
       
    }
    
    public void setSpeed(double s){
        this.speed = s;
    }
     
    public void setID (int vid){
        this.vid = vid;
    }
    
    public void setPos (double x){
        this.x = x;
    }
    
    public void setPosX (double y){
        this.y = y;
    }
    
    
   public void setAcc (double acc){
       this.acc = acc;
   }
   
   public void setTime (int ts){
       this.ts = ts;
   }
   
   
}
