/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs280.pkgfinal.project;

/**
 *
 * @author roderickbishop
 */
public class NeighborTable {

    //The node class is the car
    private static class CarNode {

        private int vid; //vehicle ID 
        private double x; //x-cordinate 
        private double y; //y-coordinate 
        private double speed;
        private double acc; //acceleration
        private double ts; //timestmap
        private CarNode next;

        public CarNode() {
        }

        public CarNode(int id, double pos_x, double pos_y, double s, double a) {
            vid = id;
            x = pos_x;
            y = pos_y;
            ts = System.currentTimeMillis();
            speed = s;
            acc = a;
            next = null;

        }

    }
    /* This is the end of the Node class, back to the NeighborTable Class */

    private CarNode head = null;
    private CarNode tail = null;
    private int size = 0;

    public NeighborTable() {
    }

//adds this car at the end
    public void addNeighbor(Vehicle vehicle1) {
        CarNode node = new CarNode(vehicle1.getID(), vehicle1.getPosX(), vehicle1.getPosY(), vehicle1.getSpeed(), vehicle1.getAcc());
      
//this will check if the vehicle is in the table
        if(isInTable(vehicle1)) return;
        if (tail == null) {
            head = node;
            tail = node;
            size++;
        } else {
            tail.next = node;
            tail = node;
            size++;
        }
    }

    public void removeNeighbor(Vehicle vehicle1) {
        if (this.size == 0) {
            return;
        }
        CarNode ptr = this.head;

        //check if ptr.next = null
        while (ptr.next != null) {

            //checks for the one that we want to delete from our list.
            if (ptr.next.vid == vehicle1.getID()) {
                if (ptr.next == tail) {
                    ptr = tail;
                } else {
                    ptr = ptr.next.next;
                }
            }
            ptr = ptr.next;
        }
    }

//neighbor table is a linkedList. we'll print the vehicle ID of each neighbor.
    public void showNeighborTable() {
        CarNode ptr = this.head;
        if (ptr == null) {
            System.out.println("There aren't any neighbors for this vehicle");
            return;
        }
        while (ptr != tail) {
            System.out.print(ptr.next.vid + ",");
            ptr = ptr.next;
        }

    }

    public boolean isInTable(Vehicle veh1) {
        CarNode ptr = this.head;
        while (ptr != null) {
            if (ptr.vid == veh1.getID()) {
                ptr.x = veh1.getPosX();
                ptr.y = veh1.getPosY();
                ptr.acc = veh1.getAcc();
                ptr.speed = veh1.getSpeed();
                return true;
            } else {
                ptr = ptr.next;
            }
        }
        return false;
        
    }
}
