//Importing all the required packages
import java.util.*;
//Class for Topological sort of DFS,BFS for graphs
public class TopologicalSort_DFSBFS{
    //Declaring a hashmap for mapping characters with numbers
    public HashMap<Integer, Character> MappingOFCharacterswithNumbers;
    // Declaring number of graph vertices variable
    public int Number_of_Graph_Vertices;
    //Declaring TopologicalOrderOFGraphVertices for storing the topological order of Graph
    public Vector<Integer> TopologicalOrderOFGraphVertices;
    //Declaring AdjacencyList to store the directed edges of Graph
    public List<Integer> AdjacencyListRepresentationOFGraph[];
    //Here all the initialisations are done in the constructor which is called at the object creation for class
    TopologicalSort_DFSBFS(int total_number_of_vertices) {
        //Assigning total_number_of_vertices to  Number_of_Graph_Vertices
        Number_of_Graph_Vertices = total_number_of_vertices;
        //Initialisng the Adjacency List with the total number of vertices
        AdjacencyListRepresentationOFGraph = new ArrayList[total_number_of_vertices];
        //Declaring looping variable and intialising to 1
        int looping_variable = 1;
        //Again creating an adjacency list for each node to store the directed edges
        while (looping_variable <= total_number_of_vertices) {
            //Initialising each adjacency list location as adjacency list
            AdjacencyListRepresentationOFGraph[looping_variable - 1] = new ArrayList<Integer>();
            looping_variable++;
        }
    }
    //Method to add an edge to graph
    void addEdgeToGraph(int Source_Vertex, int Destination_Vertex) {
        //Edge is added from source vertex to destination vertex in the adjacency list
        AdjacencyListRepresentationOFGraph[Source_Vertex -1].add(Destination_Vertex);
    }
    //Method Graph_DFS_TopologicalSort_Main() performs DFS on the vertex and checks if the cycle exists
    boolean Graph_DFS_TopologicalSort_Main(int presentvertex, boolean[] vertex_visited_status_array, Stack<Integer> Graph_vertices_TopologicalOrder, boolean[] Labelled_Status_of_Vertices) {
       //Initialising the present vertex location of vertex_visited_status_array[] to true
        // As the vertex has been visited now
        vertex_visited_status_array[presentvertex] = true;
        //Here the adjacent vertices of the present vertex are the next to be visited in DFS
        Iterator<Integer> iterator_variable =  AdjacencyListRepresentationOFGraph[presentvertex -1].iterator();
        //Logic to visit all the adjacent vertex of the present vertex and check if cycle exists
        while(iterator_variable.hasNext()) {
            //Getting the next adjacent vertex of the present vertex
            int current_vertex_tobe_visited = iterator_variable.next();
            //Condition to check if current_vertex_tobe_visited if it has been visited or not
            if (!vertex_visited_status_array[current_vertex_tobe_visited]) {
                //Executes only if the current vertex has not been visited
                //Then the next Graph_DFS_TopologicalSort_Main() method will be called on the next vertex
                if(Graph_DFS_TopologicalSort_Main(current_vertex_tobe_visited, vertex_visited_status_array, Graph_vertices_TopologicalOrder, Labelled_Status_of_Vertices)) {
                    return true;
                }
            }
            //COndition to check if there is a loop
            //Loop is present only if the vertex has been visited and not labelled
            if(vertex_visited_status_array[current_vertex_tobe_visited] && !Labelled_Status_of_Vertices[current_vertex_tobe_visited]) {
                System.out.println("The Graph Has Cycle!! Loop Detected!!");
                return true;
            }
        }
        //Executes if there is no loop and pushes the vertex to stack
        Graph_vertices_TopologicalOrder.push(presentvertex);
        //Set the status of vertex to visited
        Labelled_Status_of_Vertices[presentvertex] = true;
        return false;
    }
    //Method to perform DFS Topological sort on graph
    void Graph_DFS_TopologicalSort() {
        //Declaring and initialising the vertex_visited_status_array
        //Here we intialise to vertices+1 locations as we are starting from 1
        boolean[] vertex_visited_status_array = new boolean[1+Number_of_Graph_Vertices];
        //Declaring and initialising the Labelled_Status_of_Vertices
        //Here we intialise to vertices+1 locations as we are starting from 1
        boolean[] Labelled_Status_of_Vertices = new boolean[1+Number_of_Graph_Vertices];
        //Declaring a stack to push the vertices visited in DFS Topological order
        Stack<Integer> Graph_DFS_TopologicalOrder = new Stack<>();
        //Declaring and intialising the looping variable to 1
        int looping_variable=1;
        //Loop for intialising all the locations of
        //vertex_visited_status_array[] and Labelled_Status_of_Vertices[] to false
        // As the nodes have not yet been visited and labelled
        while(looping_variable<= Number_of_Graph_Vertices) {
            //Initialising the vertex location  in vertex_visited_status_array with false
            //As the vertex has not been visited
            vertex_visited_status_array[looping_variable] = false;
            //Initialising the vertex location in Labelled_Status_of_Vertices with false
            //As the vertex has not been labelled
            Labelled_Status_of_Vertices[looping_variable] = false;
            //Incrementing looping variable to go for next vertex
            looping_variable++;
        }
        //Logic for performing DFS on a vertex if it has not been visited
        //Here we loop through all the vertices
        for(looping_variable = 1; looping_variable <= Number_of_Graph_Vertices; looping_variable++) {
            //Condition to check if the vertex has not been visited
            if(!vertex_visited_status_array[looping_variable]) {
                //Here the Graph_DFS_TopologicalSort_Main() function is called to perform DFS
                if(Graph_DFS_TopologicalSort_Main(looping_variable, vertex_visited_status_array, Graph_DFS_TopologicalOrder, Labelled_Status_of_Vertices)) {
                    //Ends if the loop has been detected
                    return;
                }
            }
        }
        //Condition to check the stack not empty or not and print the vertices in the stack
        //Executes only if loop has not been detected
        while (!Graph_DFS_TopologicalOrder.isEmpty()) {
            //Logic for printing the vertices
            System.out.print(MappingOFCharacterswithNumbers.get(Graph_DFS_TopologicalOrder.pop()) + "\t");
        }
    }
   //Method Graph_BFS_TopologicalSort() to perform BFS on the graph
    void Graph_BFS_TopologicalSort()
    {
        //Declaring the predecessor_count[] array and creating vertices+1 locations
        //This is used to store the number of predecessors for each vertex
        int predecessor_count[] = new int[1+Number_of_Graph_Vertices];
        //Declaring and intialisng the loping_variable to 1
        int looping_variable=1;
        //Logic to count the total number of predecessors for each vertex
        //Looping through all the vertices
        while(looping_variable<= Number_of_Graph_Vertices) {
            //Getting the list of vertices which has edges from the present vertex
            ArrayList<Integer> temporary_arraylist=(ArrayList<Integer>) AdjacencyListRepresentationOFGraph[looping_variable-1];
            looping_variable++;
            //Loop for increasing the count of predecessors for each vertex
            for (int vertex : temporary_arraylist) {
                predecessor_count[vertex]++;
            }
        }

        //Declaring a Queue as BFS uses Queue
        //For Enqueuing all the vertices with no predecessors
        //That is whose indegree is 0
        Queue<Integer> BFS_Queue = new LinkedList<Integer>();
        //Looping through all the vertices to check the predecessor count of each vertex
        //Adding it to queue only if the predecessor count is 0
        for (looping_variable = 1;looping_variable <= Number_of_Graph_Vertices; looping_variable++) {
            //Condition to check if the predecessor count  is 0
            if (predecessor_count[looping_variable] == 0){
                //Adds to queue only if the predecessor count is 0
                BFS_Queue.add(looping_variable);
            }
        }

        //Declaring and Initializing number_of_visited_vertices to 0
        int Number_of_visited_vertices = 0;
        //Declaring a vector to store the result of the BFS Topological Sort of Graph
        TopologicalOrderOFGraphVertices = new Vector<Integer>();
        //Looping until the queue is not empty
        //if queue is empty and still there are some vertices to be visited
        //Then it means there exists a loop
        while (!BFS_Queue.isEmpty()) {
            //Dequeue the front element from the queue as it the vertex to be visited in the BFS
            //Enqueue all the vertices who's predecessor count becomes 0 on removal of front element vertex
            int Front_elementof_Queue = BFS_Queue.poll();
            //Adding the front element of queue to TopologicalOrderOFGraphVertices
            TopologicalOrderOFGraphVertices.add(Front_elementof_Queue);

            //Logic for looping through all the adjacent nodes of the front element vertex of queue
            //and decreasing the predecessor count of each vertex which will be effected on removal of
            //front element vertex
            //Adding the elements to Queue if their predecessor count is 0
            for (int vertex :  AdjacencyListRepresentationOFGraph[Front_elementof_Queue -1]) {
                // Condition to check if predecessor count is 0
                if (--predecessor_count[vertex] == 0) {
                    //Add to queue only if the predecessor count is 0
                    BFS_Queue.add(vertex);
                }
            }
            //Increment the Number_of_visited_vertices as the vertex has been visited
            Number_of_visited_vertices++;
        }

        //Condition to check if loop exists
        //Loop exists only if the queue is empty and some of  the vertices has not been visited
        if (Number_of_visited_vertices != Number_of_Graph_Vertices) {
            //Prints the loop Detected
            //Executes only if cycle exists
            System.out.println("The Graph Has Cycle!! Loop Detected!!");
            return;
        }
        //Logic to print the vertices visited in BFS Topological Order
        //Executes if queue empty and all the vertices has been visited
        //and cycle does not exists
        for (int i : TopologicalOrderOFGraphVertices) {
            System.out.print(MappingOFCharacterswithNumbers.get(i) + "\t");
        }

    }
    // Main method which calls all the above methods
    public static void main(String args[])
    {
        //Graph1
        System.out.println("Topological Sorting For GRAPH1 is:");
        // Creating an object Graph1_DFS for TopologicalSort_DFSBFS for Graph 1 for DFS
        TopologicalSort_DFSBFS Graph1_DFS = new TopologicalSort_DFSBFS(8);
        //Adding the edges to graph by using the Graph1_DFS object of  TopologicalSort_DFSBFS
        Graph1_DFS.addEdgeToGraph(1, 2);
        Graph1_DFS.addEdgeToGraph(1, 5);
        Graph1_DFS.addEdgeToGraph(1, 6);
        Graph1_DFS.addEdgeToGraph(2, 5);
        Graph1_DFS.addEdgeToGraph(2, 3);
        Graph1_DFS.addEdgeToGraph(2, 7);
        Graph1_DFS.addEdgeToGraph(3, 4);
        Graph1_DFS.addEdgeToGraph(4, 5);
        Graph1_DFS.addEdgeToGraph(5, 8);
        Graph1_DFS.addEdgeToGraph(5, 7);
        Graph1_DFS.addEdgeToGraph(6, 5);
        Graph1_DFS.addEdgeToGraph(6, 8);
        Graph1_DFS.addEdgeToGraph(7, 4);
        Graph1_DFS.addEdgeToGraph(7, 8);
        //Calling the Graph_DFS_TopologicalSort() method to print the DFS Topological order of Graph1
        //Prints loop detected if cycle exists or prints the DFS order is no cycle
        System.out.println("DFS Topological Sort for Graph1:");
        //Function call to Graph_DFS_TopologicalSort()
        Graph1_DFS.Graph_DFS_TopologicalSort();
        System.out.println();
        // Creating an object Graph1_BFS for TopologicalSort_DFSBFS for Graph 1 for BFS
        TopologicalSort_DFSBFS Graph1_BFS = new  TopologicalSort_DFSBFS(8);
        //Adding the edges to graph by using the Graph1_BFS object of  TopologicalSort_DFSBFS
        Graph1_BFS.addEdgeToGraph(1, 2);
        Graph1_BFS.addEdgeToGraph(1, 5);
        Graph1_BFS.addEdgeToGraph(1, 6);
        Graph1_BFS.addEdgeToGraph(2, 5);
        Graph1_BFS.addEdgeToGraph(2, 3);
        Graph1_BFS.addEdgeToGraph(2, 7);
        Graph1_BFS.addEdgeToGraph(3, 4);
        Graph1_BFS.addEdgeToGraph(4, 5);
        Graph1_BFS.addEdgeToGraph(5, 8);
        Graph1_BFS.addEdgeToGraph(5, 7);
        Graph1_BFS.addEdgeToGraph(6, 5);
        Graph1_BFS.addEdgeToGraph(6, 8);
        Graph1_BFS.addEdgeToGraph(7, 4);
        Graph1_BFS.addEdgeToGraph(7, 8);
        //Calling the Graph_BFS_TopologicalSort() method to print the BFS Topological order of Graph1
        //Prints loop detected if cycle exists or prints the BFS order is no cycle
        System.out.println("BFS Topological Sort for Graph1:");
        Graph1_BFS.Graph_BFS_TopologicalSort();
        System.out.println();

        //Graph2
        System.out.println("Topological Sorting For GRAPH2 is:");
        // Creating an object Graph2_DFS for TopologicalSort_DFSBFS for Graph 2 for DFS
        TopologicalSort_DFSBFS Graph2_DFS=new TopologicalSort_DFSBFS(14);
        //Adding the edges to graph by using the Graph2_DFS object of  TopologicalSort_DFSBFS
        Graph2_DFS.addEdgeToGraph(1, 5);
        Graph2_DFS.addEdgeToGraph(1, 6);
        Graph2_DFS.addEdgeToGraph(1, 12);
        Graph2_DFS.addEdgeToGraph(2, 3);
        Graph2_DFS.addEdgeToGraph(2, 5);
        Graph2_DFS.addEdgeToGraph(2, 9);
        Graph2_DFS.addEdgeToGraph(3, 6);
        Graph2_DFS.addEdgeToGraph(3, 7);
        Graph2_DFS.addEdgeToGraph(3, 10);
        Graph2_DFS.addEdgeToGraph(4, 3);
        Graph2_DFS.addEdgeToGraph(4, 7);
        Graph2_DFS.addEdgeToGraph(4, 14);
        Graph2_DFS.addEdgeToGraph(5, 8);
        Graph2_DFS.addEdgeToGraph(6, 9);
        Graph2_DFS.addEdgeToGraph(6, 13);
        Graph2_DFS.addEdgeToGraph(7, 6);
        Graph2_DFS.addEdgeToGraph(9, 8);
        Graph2_DFS.addEdgeToGraph(10, 11);
        Graph2_DFS.addEdgeToGraph(10, 12);
        Graph2_DFS.addEdgeToGraph(11, 14);
        Graph2_DFS.addEdgeToGraph(13, 10);
        //Creating a HashMap for storing the integers and characters as the names of graph2 are
        //given in alphabets
        Graph2_DFS.MappingOFCharacterswithNumbers = new HashMap<>();
        Graph2_DFS.MappingOFCharacterswithNumbers.put(1,'m');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(2, 'n');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(3, 'o');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(4, 'p');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(5, 'q');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(6, 'r');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(7, 's');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(8, 't');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(9, 'u');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(10, 'v');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(11, 'w');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(12, 'x');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(13, 'y');
        Graph2_DFS.MappingOFCharacterswithNumbers.put(14, 'z');
        //Calling the Graph_DFS_TopologicalSort() method to print the DFS Topological order of Graph2
        //Prints loop detected if cycle exists or prints the DFS order is no cycle
        System.out.println("DFS Topological Sort for Graph2:");
        Graph2_DFS.Graph_DFS_TopologicalSort();
        System.out.println();
        System.out.println();
        // Creating an object Graph2_BFS for TopologicalSort_DFSBFS for Graph 2 for BFS
        TopologicalSort_DFSBFS Graph2_BFS = new  TopologicalSort_DFSBFS(14);
        //Adding the edges to graph by using the Graph2_BFS object of  TopologicalSort_DFSBFS
        Graph2_BFS.addEdgeToGraph(1, 5);
        Graph2_BFS.addEdgeToGraph(1, 6);
        Graph2_BFS.addEdgeToGraph(1, 12);
        Graph2_BFS.addEdgeToGraph(2, 3);
        Graph2_BFS.addEdgeToGraph(2, 5);
        Graph2_BFS.addEdgeToGraph(2, 9);
        Graph2_BFS.addEdgeToGraph(3, 6);
        Graph2_BFS.addEdgeToGraph(3, 7);
        Graph2_BFS.addEdgeToGraph(3, 10);
        Graph2_BFS.addEdgeToGraph(4, 3);
        Graph2_BFS.addEdgeToGraph(4, 7);
        Graph2_BFS.addEdgeToGraph(4, 14);
        Graph2_BFS.addEdgeToGraph(5, 8);
        Graph2_BFS.addEdgeToGraph(6, 9);
        Graph2_BFS.addEdgeToGraph(6, 13);
        Graph2_BFS.addEdgeToGraph(7, 6);
        Graph2_BFS.addEdgeToGraph(9, 8);
        Graph2_BFS.addEdgeToGraph(10, 11);
        Graph2_BFS.addEdgeToGraph(10, 12);
        Graph2_BFS.addEdgeToGraph(11, 14);
        Graph2_BFS.addEdgeToGraph(13, 10);
        //Creating a HashMap for storing the integers and characters as the names of graph2 are
        //given in alphabets
        Graph2_BFS.MappingOFCharacterswithNumbers = new HashMap<>();
        Graph2_BFS.MappingOFCharacterswithNumbers.put(1,'m');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(2, 'n');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(3, 'o');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(4, 'p');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(5, 'q');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(6, 'r');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(7, 's');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(8, 't');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(9, 'u');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(10, 'v');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(11, 'w');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(12, 'x');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(13, 'y');
        Graph2_BFS.MappingOFCharacterswithNumbers.put(14, 'z');
        //Calling the Graph_DFS_TopologicalSort() method to print the BFS Topological order of Graph2
        //Prints loop detected if cycle exists or prints the BFS order is no cycle
        System.out.println("BFS Topological Sort for Graph2:");
        Graph2_BFS.Graph_BFS_TopologicalSort();
    }
}