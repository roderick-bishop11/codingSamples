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

    public void removeFirst(){
        first = first.next;
    }

    public void arithShift(Direction dir){
        if(dir == Direction.LEFT){
            //dump sign bit (check for overflow) and addLast a zero
            boolean overflow = first.val == 1; //will evaluate the value of the sign bit and then set the flag to true or false
            removeFirst();
            addNode(0);
            if(overflow) System.out.println("Overflow detected on this arithmetic shift");
            printList();
        }

        else{
            //addFirst the sign bit, remove last
            addFirst(first.val); //adds the sign bit again to multiply by 2
            removeLast(); //removes the last digit
            printList();
        }
    }

    public void logiShift(Direction dir){
        if(dir == Direction.LEFT){
            //add last, remove first
            addNode(0);
            removeFirst();
            printList();
        }

        else{
            addFirst(0);
            removeLast();
            printList();
        }
    }

    public void circShift(Direction dir){
    if(dir == Direction.LEFT){
        //move first to the end
        addNode(first.val);//adds the first node value to the back
        removeFirst(); //removes it
        printList();
    }
    else{
        //move the current last to the beginning
        addFirst(last.val);
        removeLast();
        printList();
    }

    }

    public void printList(){
        BinNode ptr = first;
        while(ptr != null){
            System.out.print(ptr.val);
            ptr = ptr.next;
        }
        System.out.println();
    }
}

