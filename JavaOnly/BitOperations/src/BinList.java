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

    //constructor
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

    public void addFirst(int val){
        BinNode newHead = new BinNode(val);
        newHead.next = first;
        first = newHead;
    }

    public void removeLast(){
        BinNode ptr = first;
        while(ptr.next != last){
            ptr = ptr.next;
        }
        last = ptr;
        last.next = null;
    }

    public void arithShift(Direction dir){
        if(dir == Direction.LEFT){
            //dump sign bit (check for overflow) and addLast a zero
        }

        else{
            //addFirst the sign bit, remove last
        }
    }

    public void logiShift(Direction dir){
        if(dir == Direction.LEFT){
            //add last, remove first
        }

        else{
            addFirst(0);
            removeLast();
            printList();
        }
    }

    public void circShift(Direction dir){
    if(dir == Direction.LEFT){
        //move current last to the beginning
    }
    else{
        //move the current first to the end
    }

    }

    public void printList(){
        BinNode ptr = first;
        while(ptr != null){
            System.out.print(ptr.val);
            ptr = ptr.next;
        }
    }
}

