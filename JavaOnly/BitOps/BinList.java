//singly linked list for binary digits
public class BinList {
    
    private class BinNode{
        int val; //value in the node
        BinNode next; //pointer to the next node
        public BinNode(int x){
            val = x;
        }
    }
    
    BinNode first; //most significant bit
    BinNode last; //least significant bit
    int size = 0; //default value of the list.
    
    public BinList(){ 

    }

    public void addNode(int val){
        BinNode node = new BinNode(val);
        if(this.size == 0){
            first = node;
            last = node;
            size++;
        }
        else{
            last.next = node; //connect the current last to the new node
            last = node; //make the last the node
            last.next = null; //now, make it null. 
            size++;
        }
    }


    public static void arithShift(Direction dir){
        
    }

    public static void logiShift(Direction dir){

    }

    public static void circShift(Direction dir){


    }

    public static void regShift(Direction dir){

    }

    //the direction for the shift
    enum Direction{
    LEFT,
    RIGHT
    }
}
