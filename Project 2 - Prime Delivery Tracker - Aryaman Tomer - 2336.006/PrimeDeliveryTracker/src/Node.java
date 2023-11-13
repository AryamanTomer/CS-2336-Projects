//Aryaman Tomer axt210052

//This class will represent the nodes for the linked list and also represent the generic type parameter E
public class Node<E extends Comparable<E>>{
    //The private Nodes previous and next are the pointers towards the next or previous node in the Linked list
    //The payload is used to carry on metadata information consumed by a validation client
    private Node<E> prev;
    private Node<E> next;
    private E payload;

    public Node(){
        this.next = null;
        this.prev = null;
        this.payload = null;
    }
    public Node(E data){
        this.payload = data;
    }
    //returning the previous pointer of the node to the previous node in the Linked List
    public Node<E> getPrev(){
        return prev;
    }
    //returning the next pointer of the node to the next node
    public Node<E> getNext(){
        return next;
    }
    //returning the payload value of the node
    public E getPayload(Node<E> node){
        return node.payload;
    }
    //setting the previous node value to a new node
    public void setPrev(Node<E> node){
        this.prev = node;
    }
    //setting the next node to a new node
    public void setNext(Node<E> node){
        this.next = node;
    }
    //Taking the payload value to convert it into a string 
    public String toString(){
        String t = payload.toString();
        return(t);
    }
    //This method represents the Node object being compared
    public int compareTo(Node<E> other, int i){
        //Getting the payload values and comparing both of them
        String t1[] = this.payload.toString().split("\t");
        String t2[] = other.getPayload(other).toString().split("\t");

        if (t1.length != 2 || t2.length != 2) {
            // Handle the case where the strings cannot be split as expected
            return 0; // You might want to choose a suitable default value
        }
        String firstNodeTitle = t1[0];
        double firstNodeArea = Double.parseDouble(t1[1]);
        String secondNodeTitle = t2[0];
        double secondNodeArea = Double.parseDouble(t2[1]);
        //if the areas are equal, then it will return 0
        //if the second area is greater, then it will return -2
        //if the first area is greater, then it will return 2
        if(i == 1){
            if(firstNodeArea < secondNodeArea){
                return -2;
            }
            else if(firstNodeArea == secondNodeArea){
                return 0;
            }
            else{
                return 2;
            }
        }
        //if the names are equal, then it will return 0
        //if the second name is greater in length, then it will return a -1
        //if the first name is greater in length, then it will return a 1
        else if(i == 2){
            if(firstNodeTitle.length() < secondNodeTitle.length()){
                return -1;
            }
            else if(firstNodeTitle.length() == secondNodeTitle.length()){
                return 0;
            }
            else {
                return 1;
            }
        }
        else{
            return 0;
        }
    }
    //comparing the String to check for proper string values
    public int compareTo(String a){
        String t = this.getPayload(this).toString();
        String tArray[] = t.split("\t");
        String nodeName = tArray[0];
        if(nodeName.equals(a)){
            return 0;
        }
        else{
            return -1;
        }
    }
    //comparing the int to check for proper integer values
    public int compareTo(double i){
        //If we add a small margin of error, we can handle the floaing point comparisons in order to search for double values
        String t = this.getPayload(this).toString();
        String tArray[] = t.split("\t");
        double nodeValue = Double.parseDouble(tArray[1]);
        double epsilon = 0.0001; 
        if (Math.abs(nodeValue - i) < epsilon) {
            return 0;
        } else {
            return -1;
        }
    }
}