public class MinimumHeapSortAlgorithm {
    //Builds Minheap from the array_numbers
    public void MinHeapBuild_FloydsAlgorithm(int array_numbers[],int array_numbers_length){
        //Declaring and initialising the looping variable to 0
        int iterating_variable=0;
        //Loop starts from the first parent from the last of the heap
        //Skips all the leaf nodes as they already maintains heap property
        //The first non leaf node is present at the array_numbers_length/2 -1 location
        for(iterating_variable=array_numbers_length/2 -1 ; iterating_variable>=0; --iterating_variable){
            //HeapifyAlgorithm() function to maintain the heap property on the affected subtree
            HeapifyAlgorithm(array_numbers,array_numbers_length,iterating_variable);
        }
    }
    //DecsendingsortingOFHeap() function sorts the heap in decsending order
    public void DecsendingsortingOFHeap(int array_numbers_heap[],int array_numbers_heap_length)
    {
        //Declaring and intialising the looping variable to 0
        int iterating_variable=0;
        //Declaring and initialising the temporary_variable to 0
        int temporary_variable=0;
        //Here this loop swaps the first value of heap with last value of heap
        // and again perform heapify on the affected subtree
        //Decrements the size of heap by 1 and again perform
        // heapify on the reduced tree as the last value is in correct position
        for (iterating_variable = array_numbers_heap_length - 1; iterating_variable > 0; iterating_variable--) {
            //Swapping the first and last values of the heap
            temporary_variable = array_numbers_heap[0];
            array_numbers_heap[0] = array_numbers_heap[iterating_variable];
            array_numbers_heap[iterating_variable] = temporary_variable;

            //HeapifyAlgorithm() function to again maitain heap property on the affected and modified subtree
            HeapifyAlgorithm(array_numbers_heap,iterating_variable, 0);
        }
    }

    //HeapifyAlgorithm() function is used to rearrange
    //the affected and modified subtree of heap and maintaining the heap order property
    public void HeapifyAlgorithm(int heap_array_numbers[], int reduced_heap_length, int starting_index)
    {
        //Declaing and initialising the min_index with the starting index
        //This min_index is used to check the minheap property at min_index
        int min_index = starting_index;
        //Declaring and initialising the temporary value to 0
        int temporary_variable=0;
        //Left Child of Min_index node is present at 2*starting_index+1
        int left_child = 2 * starting_index + 1;
        //Right Child of Min_index node is present at 2*starting_index+2
        int right_child = 2 * starting_index + 2;

        //Checks if left_child index is less than the reduced_heap_length
        //and checks if left_child value is less than the value at min_index
        if (left_child < reduced_heap_length && heap_array_numbers[left_child] < heap_array_numbers[min_index])
            min_index = left_child;

        //Checks if right_child index is less than the reduced_heap_length
        //and checks if right_child value is less than the value at min_index
        if (right_child < reduced_heap_length && heap_array_numbers[right_child] < heap_array_numbers[min_index])
            min_index = right_child;

        // Check if min_index is not equal to the starting_index
        // Perform swap if the min_index and starting_index are not equal
        // and again perform heapify() on the affected subtree
        if (min_index != starting_index) {
            //Logic for swapping the starting_index value and min_index value
            temporary_variable = heap_array_numbers[starting_index];
            heap_array_numbers[starting_index] = heap_array_numbers[min_index];
            heap_array_numbers[min_index] = temporary_variable;
            //HeapifyAlgorithm() is called to maintain the heap order property on the effected subtree
            HeapifyAlgorithm(heap_array_numbers, reduced_heap_length, min_index);
        }
    }

    // PrintHeap_ArrayNumbers() is used to print the heap_array values
    static void PrintHeap_ArrayNumbers(int heap_array[],int heap_array_length )
    {
        //Declaring a looping variable and initialising to 0
        int iterating_variable = 0;
        //Looping through the heap_array and Printing the heap_array values
        for (iterating_variable = 0; iterating_variable < heap_array_length; iterating_variable++)
            System.out.print("    " +heap_array[iterating_variable] );
        System.out.println();
    }

    public static void main(String args[])
    {
        // Declaring an array and initialising the values in the array to convert into heap
        int array_numbers[] = { 21,20,22,14,15,16,18,24,32,54,76,52,64,31,17 };
        //Calculating the length of an array_numbers
        int array_numbers_length=array_numbers.length;
        System.out.println("Array before performing Minheap Build using Floyd's ALgorithm:-->");
        // Pinting the array numbers before building MinHeap
        PrintHeap_ArrayNumbers(array_numbers,array_numbers_length);
        //Declaring an object for the class MinimumHeapSortAlgorithm
        MinimumHeapSortAlgorithm minheapsortalgorithm = new MinimumHeapSortAlgorithm();
        //Building the Minheap by converting the array_numbers array into minheap
        // by using Floyds ALgorithm
        minheapsortalgorithm.MinHeapBuild_FloydsAlgorithm(array_numbers,array_numbers_length);
        System.out.println("Array after performing Minheap Build using Floyd's ALgorithm:-->");
        //Pinting the array numbers after building MinHeap and converting to a minheap
        PrintHeap_ArrayNumbers(array_numbers,array_numbers_length);
        System.out.println("Array before performing descending order sorting on Min Heap ");
        //Printing the sorted array
        PrintHeap_ArrayNumbers(array_numbers,array_numbers_length);
        //Performing sorting on MinHeap and sorting it in descending order
        minheapsortalgorithm.DecsendingsortingOFHeap(array_numbers,array_numbers_length);
        System.out.println("Array after performing descending order sorting on Min Heap ");
        //Printing the sorted array
        PrintHeap_ArrayNumbers(array_numbers,array_numbers_length);
    }
}