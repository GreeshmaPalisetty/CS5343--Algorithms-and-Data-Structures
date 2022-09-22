public class LinkedlistNode {
    //Declaring the Linkedlist Node Structure
    protected int data;
    protected LinkedlistNode next;

    //Initalising the Linkedlist node values to initial values of 0 in data and null in next field
    public LinkedlistNode(){
        next=null;
        data=0;
    }

    //Creating the memory for the linkedlist node and Assigning the values passed from the Insert fucntions to the Linkedlist Node
    public LinkedlistNode(int value,LinkedlistNode node){
        data=value;
        next=node;
    }

    //Setting the data field of LinkedlistNode to the value passed
    public void setData(int value){
        data=value;
    }

    //Returning the data of the Linkedlist Node
    public int getData() {
        return data;
    }

    //Returning the Linkedlist Node address
    public LinkedlistNode getNext() {
        return next;
    }

    // Setting the Linkedlist Node next field
    public void setNext(LinkedlistNode next) {
        this.next = next;
    }
}

Linkedlist.java
public class Linkedlist {

    //Declaring the head pointer pointing to the first node of the Linkedlist
    protected LinkedlistNode head_pointer;
    //Declaring the count variable
    public int count;

    //Initialising the head pointer to null and count=0 as no nodes
    public Linkedlist() {
        head_pointer = null;
        count = 0;
    }

    // Method to return true if the Linkedlist is empty or else false
    public boolean isEmpty() {
        if (head_pointer == null)
            return true;
        else
            return false;
    }

    // Method to insert values at the front of the Linkedlist
    public void InsertAtFront(int value) {
        LinkedlistNode temporary = new LinkedlistNode(value, null);
        temporary.setData(value);
        temporary.setNext(null);
        //The new node temporary becomes the new head
        if (head_pointer == null) {
            head_pointer = temporary;
        }
        //Logic for Insertion at the front of the linkedlist
        else {
            temporary.setNext(head_pointer);
            head_pointer = temporary;
        }
        count++;
    }

    // Method to insert values at the end of the Linked list
    public void InsertionAtEND(int value) {
        LinkedlistNode newnode = new LinkedlistNode();
        newnode.setData(value);
        newnode.setNext(null);
        //New node will be the new head if head pointer is null
        if (head_pointer == null) {
            head_pointer = newnode;
        }
        //Logic for Insertion at the end of the linkedlist
        else {
            LinkedlistNode temporary = head_pointer;
            while (temporary.next != null) {
                temporary = temporary.next;
            }
            temporary.next = newnode;
        }
        count++;
    }

    // Method to insert the value at a particular position in the linked list
    public void InsertionAtPosition(int position, int value) {
        LinkedlistNode newnode = new LinkedlistNode();
        newnode.setData(value);
        newnode.setNext(null);
        //Return if the position value is below 0 or above the size+1 of linkedlist
        if (position <= 0 || position > count + 1) {
            System.out.println("Position value cannot be  less than 0 or greater than "+count);
            return;
        }
        else {
            //Insertion at 1st position of the linked list
            if ( position == 1) {
                newnode.setNext(head_pointer);
                head_pointer = newnode;
            }
            //Insertion at any position between 1 and size+1
            else {
                LinkedlistNode current = head_pointer;
                LinkedlistNode previous = null;
                int i = 1;
                while (i < position && current!= null) {
                    previous = current;
                    current = current.next;
                    i++;
                }
                newnode.setNext(current);
                previous.setNext(newnode);
            }
            count=count+1;
        }
    }

    // Method to display the values in the linkedlist
    public void Traversing() {
        LinkedlistNode print_pointer = head_pointer;
        if (print_pointer == null) {
            System.out.println("Linkedlist is empty");
        }
        System.out.print("HEAD->");
        while (print_pointer != null) {
            System.out.print(print_pointer.getData() + "->");
            print_pointer = print_pointer.next;
        }
        System.out.println("NULL");
    }

    // Selection Sort to sort the values of Linkedlist in sorting order
    // 4 cases are to be considered
    // case 1:if the 2 nodes are adjacent and the first node is not the starting node
    // case 2:if the 2 nodes are adjacent and the first node is the starting node
    // case 3:if the 2 nodes are not adjacent and the first node is not the starting node
    // case 4:if the 2 nodes are not adjacent and the first node is the starting node
    public void SelectionSortFORLinkedlist() {
        LinkedlistNode temporary1, temporary2, temporary3, temporary4, temporary5;

        temporary1 = temporary2 = head_pointer;
        if (head_pointer == null) {
            return;
        }
        else {
            // Loop until temporary2 is not pointing to last node
            while (temporary2.next != null) {

                temporary3 = temporary4 = temporary2.next;

                // Loop until temporary4 is pointing to a valid node
                while (temporary4 != null) {

                    if (temporary2.data > temporary4.data) {

                        // If temporary 4 and temporary 2 are adjacent to each other
                        if (temporary2.next == temporary4) {

                            // check Case 1 if temporary1 and temporary2 are adjacent temporary2 is not the head of the linked list
                            if (temporary2 != head_pointer) {

                                // Bringing temporary4 ahead of temporary2
                                temporary2.next = temporary4.next;
                                temporary4.next = temporary2;
                                temporary1.next = temporary4;

                                // Interchanging temporary4 and temporary2 pointers
                                temporary5 = temporary2;
                                temporary2 = temporary4;
                                temporary4 = temporary5;

                                temporary3 = temporary4;

                                // Update temporary4 to next node as temporary2 and temporary4 are in order
                                temporary4 = temporary4.next;
                            }

                            // check Case 2 if temporary1 and temporary2 are adjacent and temporary2 is the head of the linked list
                            if (temporary2 == head_pointer) {

                                // Bringing temporary4 ahead of temporary2
                                temporary2.next = temporary4.next;
                                temporary4.next = temporary2;

                                // Interchanging temporary2 and temporary4 pointers
                                temporary5 = temporary2;
                                temporary2 = temporary4;
                                temporary4 = temporary5;

                                temporary3 = temporary4;

                                // Updating the head to the new node
                                head_pointer = temporary2;

                                // Updating temporary4 to the next node as the two nodes are already in order
                                temporary4 = temporary4.next;
                            }
                        }

                        // if temporary2 and temporary4 are not adjacent to each other and have some nodes between them
                        else {
                            // check Case 3 if temporary2 is not the head of the linked list
                            if (temporary2 != head_pointer) {

                                // Updating temporary4 and temporary2 next field
                                temporary5 = temporary2.next;
                                temporary2.next = temporary4.next;
                                temporary4.next = temporary5;
                                temporary3.next = temporary2;
                                temporary1.next = temporary4;

                                // Updating temporary4 and temporary2 pointers
                                temporary5 = temporary2;
                                temporary2 = temporary4;
                                temporary4 = temporary5;

                                temporary3 = temporary4;

                                // Updating temporary4 to the next node as the nodes are in order
                                temporary4 = temporary4.next;
                            }
                            // check Case 4 if temporary4 is the head of the linked list
                            if (temporary2 == head_pointer) {

                                // Update temporary2.next and temporary4.next
                                temporary5 = temporary2.next;
                                temporary2.next = temporary4.next;
                                temporary4.next = temporary5;
                                temporary3.next = temporary2;

                                // Updating temporary2 and temporary4 pointers
                                temporary5 = temporary2;
                                temporary2 = temporary4;
                                temporary4 = temporary5;

                                temporary3 = temporary4;

                                // Updating temporary4
                                temporary4 = temporary4.next;

                                // Pointing to the new head
                                head_pointer = temporary2;
                            }
                        }
                    } else {

                        // update temporary3 pointer as the the nodes are in order.
                        temporary3 = temporary4;
                        temporary4 = temporary4.next;
                    }
                }

                temporary1 = temporary2;
                temporary2 = temporary2.next;
            }
        }
    }
}

Main.java
public class Main {
    public static void main(String[] args)
    {
        // Creating an object for Linkedlist class
        Linkedlist list = new Linkedlist();
        list.InsertAtFront(54);
        list.InsertionAtEND(61);
        list.InsertionAtPosition(1,44);
        list.InsertAtFront(71);
        list.InsertionAtEND(91);
        list.InsertionAtPosition(3,0);
        list.InsertAtFront(21);
        list.InsertAtFront(710);
        list.InsertionAtEND(910);
        list.InsertionAtPosition(4,10);
        list.InsertAtFront(504);
        list.InsertionAtEND(621);
        list.InsertionAtPosition(10,34);
        list.InsertAtFront(330);
        list.InsertionAtEND(901);
        list.InsertionAtPosition(3,18);
        list.InsertAtFront(45);
        list.InsertAtFront(81);
        list.InsertionAtEND(53);
        list.InsertionAtPosition(6,100);
        list.InsertAtFront(63);
        list.InsertionAtEND(620);
        list.InsertionAtPosition(11,820);
        list.InsertAtFront(120);
        list.InsertionAtEND(951);
        list.InsertionAtPosition(1,55);
        list.InsertAtFront(1000);
        list.InsertAtFront(82);
        list.InsertionAtEND(77);
        list.InsertionAtPosition(3,177);
        System.out.println("The Size of the linked list is: "+ list.count);

        System.out.println("Linked list before Sorting");
        //Displaying the unsorted values of the linkedlist data field
        list.Traversing();

        //Performing the selection sort to sort the linkedlist nodes
        list.SelectionSortFORLinkedlist();

        System.out.println("Linked list after Selection Sort");
        //Displaying the sorted values of the linkedlist data field
        list.Traversing();
    }
}
