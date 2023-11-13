//Aryaman Tomer axt210052
import java.util.Scanner;
//This class will represent the linked list and also represent the generic type parameter T
public class LinkedList<E extends Comparable<E>> {
    private Node<E> head;
    private Node<E> tail;

    // Constructor without parameters
    public LinkedList(){
        this.head = null;
        this.tail = null;
    }
    //Overloaded Constructor with Parameters
    public LinkedList(Node<E> h, Node<E> t){
        //Setting the Head and Tail Nodes to the h and t values
        this.head = h;
        this.tail = t;
    }
    //Searches the LinkedList for a certain string
    public boolean searchString(String s) {
        Node<E> trav = this.getHead();
        while (trav != null) {
            String driverName = ((Driver) trav.getPayload(trav)).getName();
            if (driverName.equals(s)) {
                System.out.println(trav.toString()); // Print the found driver's information
                return true;
            }
            trav = trav.getNext();
        }
        return false; // If not found
    }
    
    //Searches every integer in order to confirm any integer value in the linked list
    public boolean searchInt(int d) {
        Node<E> trav = this.getHead();
        while (trav != null) {
            String t = trav.getPayload(trav).toString();
            String tArray[] = t.split("\t");
            double nodeValue = Double.parseDouble(tArray[1]);
            double epsilon = 0.0001;//Define a small margin of error for floating-point comparisons
        if (Math.abs(nodeValue - d) < epsilon) {
            return true;
        }
        trav = trav.getNext();
        }
        return false;
    }
    
    //Overriden toString Methods
    public String toString(LinkedList<E> l){
        String t = "";
        //Adds the Drivers name and Area to the LinkedList
        Node<E> trav = l.getHead();
        if(trav == null){
            return null;
        }
        while(trav.getNext() != null){
            t += trav.toString();
            trav = trav.getNext();
        }
        t += trav.toString();
        return t;
    }
    //This is just the reverse of the toString Method
    public String toStringReverse(LinkedList<E> r){
        String t = "";
        //Adds the Drivers name and Area in reverse order
        Node<E> tr = r.getTail();
        while(tr.getNext() != null){
            t += tr.toString();
            tr = tr.getPrev();
        }
        t += tr.toString();
        return t;
    } 
    //A sorting method to sort the method by ascending or descending by name or area
    public void sort(int s){
        boolean swap = true;
        while(swap == true){
            swap = false;
            Node<E> trav = head;
            while(trav != null){
                //If the LinkedList ends, stop the method.
                if(trav.getNext() == null){
                    break;
                }
                else if(s == 1){
                    //Comparing the value of the 2 nodes to get it in order
                    int com = trav.compareTo(trav.getNext(), s);
                    if(com == 2){
                        swap(trav, trav.getNext());
                        swap = true;
                    }
                }
                else if(s == 2){
                    //Comparing the value of 2 nodes to get it in order
                    int com = trav.compareTo(trav.getNext(), s);
                    if(com == 1){
                        swap(trav, trav.getNext());
                        swap = true;
                    }
                }
                trav = trav.getNext();
            }
        }
    };

    //This method would be swapping the two node positions
    public void swap(Node<E> one, Node<E> two){
        if(one.getPrev() == null){
            one.setPrev(one.getNext());
            one.setNext(two.getNext());
            one.getNext().setPrev(one);
            two.setNext(two.getPrev());
            two.setPrev(null);
            this.head = two;
        }
        else if(two.getNext() == null){
            two.setPrev(two.getNext());
            two.setNext(one.getNext());
            two.getNext().setPrev(two);
            one.setNext(one.getPrev());
            one.setPrev(null);
            this.head = one;
        }
        else{
            one.setNext(two.getPrev());
            two.setNext(two.getPrev());
            two.setPrev(one.getPrev());
            one.setPrev(two);
            two.getPrev().setNext(two);
            one.getNext().setPrev(one);
        }
    }

    public void insert(E i){
        //This is the temporary node we are going to be adding
        Node<E> temp = new Node<>(i);
        if(head == null){
            //If there is no head, insert the temporary node as the head
            this.head = temp;
            this.tail = head;
        }
        else{
            //Traversing the Linked List
            Node<E> trav = this.head;
            //Looping through the LinkedList and setting the lastValue to the tail
            while(trav.getNext() != null){
                trav = trav.getNext();
            }
            //Setting the tail to the next node
            trav.setNext(temp);
            trav.getNext().setPrev(trav);
            this.tail = trav.getNext();
        }
    }
    //Returns the head of the linkedlist
    public Node<E> getHead(){
        return head;
    }
    //To not lose data, use some input
    public void setHead(Node<E> value){
        //Input is used to set the head of the linked list to the value in this case
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to set the head of the linkedList? Y or N");
        String val = input.next();
        //If the input says yes, then the head will be set to a new value
        if(val.toLowerCase() == "y"){
            this.head = value;
            System.out.println("The head was changed.");
        }
        //Otherwise, don't change the head
        else if(val.toLowerCase() == "n"){
            System.out.println("The head didn't change");
        }
        else{
            System.out.println("The head didn't change");
        }

    }
    //Returns the tail of the linked list
    public Node<E> getTail(){
        return tail;
    }
    //To not lose any data in the tail, use some input
    public void setTail(Node<E> value){
        //Input is used to set the tail of the linked list to the value in this case
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to set the tail of the linkedList? Y or N");
        String val = input.next();
        //If the input says yes, then the head will be set to a new value
        if(val.toLowerCase() == "y"){
            this.tail = value;
            System.out.println("The tail was changed.");
        }
        //Otherwise, don't change the tail
        else if(val.toLowerCase() == "n"){
            System.out.println("The tail didn't change");
        }
        else{
            System.out.println("The tail didn't change");
        }
    }
}
