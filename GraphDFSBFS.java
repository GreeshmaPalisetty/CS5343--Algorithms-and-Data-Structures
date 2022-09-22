//Importing all the necessary packages
import java.util.*;
//Main class which performs all the required operations
public class GraphDFSBFS {

    //Adjacency List Representation of the Graph
    //Declaring the adjacency_list which is linked list array which stores integers
    // We Use This adjacency list to represent the graph nodes and their paths and connections
    public final LinkedList<Integer>[] adjacency_list;

    //GraphDFSBFS() is a constructor which is used for creating a Linked List with number_of_vertices
    // Each location in the Linked list is again a linkedlist
    public GraphDFSBFS(int number_Of_vertices)
    {
        //Declaring the looping variables for iteration
        int looping_variable=1;
        //Creating the number_of_vertices+1 locations for the adjacency_list
        adjacency_list=new LinkedList[number_Of_vertices+1];
        //Iterating from 1 to number_of_vertices to initialise each location of adjacency_list as a Linked list
        for(looping_variable=1;looping_variable<=number_Of_vertices;looping_variable++)
        {
            //Initialising each location of adjacency_list as a linkedlist
            adjacency_list[looping_variable]=new LinkedList<>();
        }
    }
    //Adding edges to the graph
    public void Add_Edge_To_Graph(int source_vertex,int destination_vertex){
        //source_vertex is the starting vertex and destination_vertex is the ending vertex of an edge
        //Logic for directed graph
        //In the directed graph the path of an edge will be from source to destination
        //Destination_vertex is added to the Source_vertex location of the adjacency_list
        adjacency_list[source_vertex].add(destination_vertex);
        // Logic if the graph is a undirected graph
        // In undirected graph the edge between two nodes determines source is attached with destination
        // and destination is attached with source here there is no direction
        // SO both attachments are done
      //  adjacency_list[destination_vertex].adjacency_list(source_vertex);
    }

    //DFS traversal algorithm for the Graph
    public void Main_DFS_Print(int vertex, boolean visited_status_of_array[])
    {
        // Setting the status of the vertex to visited
        visited_status_of_array[vertex] = true;
        //Printing the vertex and then going through the depth
        System.out.print(vertex + "\t");

        // Checking for all the vertices adjacent to the vertex
        Iterator<Integer> i = adjacency_list[vertex].listIterator();
        while (i.hasNext()) {
            int present_node = i.next();
            //COndition to check if the node is visited or not
            if (!visited_status_of_array[present_node]) {
                //Recursive call only if the node is not visited
                Main_DFS_Print(present_node, visited_status_of_array);
            }
        }
    }

    // The Method DFS_Graph_Traversal() checks the visit status of the vertex and then call the recursive function
    //To visit its neighbours
    void DFS_Graph_Traversal(int Vertex) {
        //Declaring and intialising the variables
        int looping_variable = 0;
        // Visit boolean array which checks the status of the node
        //Declaring the boolean visit array
        boolean visited_status_of_array[] = new boolean[Vertex + 1];

        // Calling the Main_DFS_Print() method recursively if the vertex is not visited
        // Loop traversal starting from all the vertices
        for (looping_variable = 1; looping_variable <= Vertex; looping_variable++) {
            //Checking the status of the node
            if (visited_status_of_array[looping_variable] == false) {
                //Recursive Main_DFS() method to print the nodes only if the vertex is not visited
                Main_DFS_Print(looping_variable, visited_status_of_array);
            }
        }
        System.out.println("");
    }
    public  void BFS_Graph_Traversal(){
        // Taking node 1 as the starting vertex and then visiting the nodes by going through breadth
        // that is nodes at same level
        // Boolean vertex_visited_status_array which stores the status of visit of a node
        // True if the node is visited so it will be easy to not go through the node again
        boolean[] vertex_visited_status_array=new boolean[adjacency_list.length];
        //BFS uses queue
        //Declaring a Queue for storing the nodes
        Queue<Integer> auxilary_queue =new LinkedList<>();
        //Looping through all the vertices
        for(int i=1;i<=15;i++) {
            if (vertex_visited_status_array[i] == false) {
                //Enqueuing the  vertex into the auxilary_queue
                auxilary_queue.add(i);
                //Setting the status of the starting vertex as visited in the vertex_visited_status_array[] to true
                vertex_visited_status_array[i] = true;
                //Looping until the queue is not empty
                while (!auxilary_queue.isEmpty()) {
                    //Dequeue the front element of the queue as it has to visited and enqueue all its neighbours
                    int front_element = auxilary_queue.poll();
                    //Printing the front element as it is the node that has been visited
                    System.out.print(front_element + " \t");
                    //Enqueuing all the neighbours of the vertex to queue if they are not visited
                    for (int neighbours_of_vertex : adjacency_list[front_element]) {
                        //Condition to check if the node has been visited or not
                        //Enqueue only if not visited
                        if (!vertex_visited_status_array[neighbours_of_vertex]) {
                            //Adding the node to the queue as it it not visited
                            auxilary_queue.add(neighbours_of_vertex);
                            //Setting the visit status of node to true in the boolean array
                            vertex_visited_status_array[neighbours_of_vertex] = true;
                        }
                    }
                }
            }
        }
        System.out.println(" ");
    }
    //Logic for printing the edges between the nodes
    public void Print_AdjacencyList(int number_of_edges,int number_of_vertices){
        //Declaring the variables
        int looping_variable1=0,looping_variable2=0;
        //Looping through all the vertices
        for (looping_variable1 = 1; looping_variable1 <=number_of_vertices; looping_variable1++) {
            //COndition to check if the adjacency_list[looping_variable1] size is greater than 0
            //as each location will again be a linkedlist
            if(adjacency_list[looping_variable1].size()>0) {
                //Printing the edges attached to the node
                System.out.print("Vertex " + looping_variable1 + " is connected to: ");
                //Looping untill the adjacency linked list of a node is null
                //Printing the node value of the adjacency list
                for (looping_variable2 = 0; looping_variable2 < adjacency_list[looping_variable1].size(); looping_variable2++) {
                    System.out.print(adjacency_list[looping_variable1].get(looping_variable2) + " ");
                }
                System.out.println();
            }
        }
    }
    //Logic for printing the edges between the nodes
    public void Print_Edges(int number_of_edges,int number_of_vertices){
        //Declaring the variables
        int looping_variable1=0,looping_variable2=0;
        //Looping through all the vertices
        for (looping_variable1 = 1; looping_variable1 <=number_of_vertices; looping_variable1++) {
            //COndition to check if the adjacency_list[looping_variable1] size is greater than 0
            //as each location will again be a linkedlist
            if(adjacency_list[looping_variable1].size()>0) {

                for (looping_variable2 = 0; looping_variable2 < adjacency_list[looping_variable1].size(); looping_variable2++) {
                    System.out.print(looping_variable1+"->"+adjacency_list[looping_variable1].get(looping_variable2) + ", ");
                }
                System.out.println();
            }
        }
    }
    //Logic for Printing the vertices
    public void Print_Nodes(int number_of_vertices){
        //Declaring the variables
        int looping_variable;
        //Looping through the nodes for printing the nodes
        for (looping_variable= 1; looping_variable <=number_of_vertices ; looping_variable++) {
                System.out.print( looping_variable+",\t");
            }
        System.out.println();
    }
    //Main class
    public static void main(String[] args) {
        //Declaring the number of vertices variable and initialising to 15
        //Considering 15 nodes in the graph
        int number_of_vertices=15;
        //Printing the total number of vertices
        System.out.println("Total number of Vertices : "+number_of_vertices);
        //Creating an object for the GraphDFSBFS class
        // and passing the number of vertices variable which stores total number of vertices count
        //This calls the constructor and initialises the adjacency lists
        GraphDFSBFS g=new GraphDFSBFS(number_of_vertices);
        //Declaring the number of edges variable and initialising to 37
        int number_of_edges=37;
        //Printing the total number of edges
        System.out.println("Total number of Edges : " +number_of_edges);
        //Adding edges to the graph
        //By Passing the source and destination vertices to the Add_Edge_To_Graph() method of GraphDFSBFS class
        g.Add_Edge_To_Graph(1,2);
        g.Add_Edge_To_Graph(1,4);
        g.Add_Edge_To_Graph(1,5);
        g.Add_Edge_To_Graph(1,6);
        g.Add_Edge_To_Graph(1,7);
        g.Add_Edge_To_Graph(2,4);
        g.Add_Edge_To_Graph(2,3);
        g.Add_Edge_To_Graph(3,4);
        g.Add_Edge_To_Graph(3,2);
        g.Add_Edge_To_Graph(4,5);
        g.Add_Edge_To_Graph(5,6);
        g.Add_Edge_To_Graph(6,14);
        g.Add_Edge_To_Graph(6,12);
        g.Add_Edge_To_Graph(7,6);
        g.Add_Edge_To_Graph(7,13);
        g.Add_Edge_To_Graph(7,14);
        g.Add_Edge_To_Graph(7,15);
        g.Add_Edge_To_Graph(8,1);
        g.Add_Edge_To_Graph(8,2);
        g.Add_Edge_To_Graph(8,7);
        g.Add_Edge_To_Graph(9,2);
        g.Add_Edge_To_Graph(9,8);
        g.Add_Edge_To_Graph(9,10);
        g.Add_Edge_To_Graph(10,2);
        g.Add_Edge_To_Graph(10,3);
        g.Add_Edge_To_Graph(10,11);
        g.Add_Edge_To_Graph(11,3);
        g.Add_Edge_To_Graph(11,12);
        g.Add_Edge_To_Graph(12,2);
        g.Add_Edge_To_Graph(12,9);
        g.Add_Edge_To_Graph(12,8);
        g.Add_Edge_To_Graph(13,9);
        g.Add_Edge_To_Graph(13,10);
        g.Add_Edge_To_Graph(14,7);
        g.Add_Edge_To_Graph(14,15);
        g.Add_Edge_To_Graph(15,8);
        g.Add_Edge_To_Graph(15,9);
        //Printing all the nodes in the graph
        System.out.println("Nodes in the Graph are: ");
        //Prints the nodes in the graph by calling Print_Nodes()
        g.Print_Nodes(number_of_vertices);
        //Printing all the edges in the graph
        System.out.println("Edges in the Graph are: ");
        //Prints the edges in the graph by calling Print_Edges()
        g.Print_Edges(number_of_edges,number_of_vertices);
        //Printing Adjacency List of the graph
        System.out.println("Adjacency List of the Graph is: ");
        //Prints the Adjacency List of the graph by calling Print_AdjacencyList()
        g.Print_AdjacencyList(number_of_edges,number_of_vertices);
        //Printing the DFS Traversal of the Graph
        System.out.println("DFS Traversal of the Graph:");
        //Calling the DFS_Graph_Traversal() method for printing the DFS traversal of the graph
        g.DFS_Graph_Traversal(number_of_vertices);
        //Printing the BFS Traversal of the Graph
        System.out.println("BFS Traversal of the Graph:");
        //Calling the BFS_Graph_Traversal() method for printing the BFS traversal of the graph
        g.BFS_Graph_Traversal();
    }
}
