import java.util.*;
public class TernarySearch {
   static int ternarysearchlist(int numbers_array[], int first_index, int last_index,int key){


       if(first_index<=last_index) {
           //Calculating the mid1,mid2
           int mid_position1 = first_index + (last_index - first_index) / 3;
           int mid_position2 = last_index- (last_index - first_index) / 3;
           // if the key value is present at mid1
           if (key == numbers_array[mid_position1])
               return mid_position1;
           //if the key value is present at mid2
           if (key == numbers_array[mid_position2])
               return mid_position2;
           //if the key value is less than the value at mid1 then recursively call from first index till mid1-1
           if (key < numbers_array[mid_position1])
               return ternarysearchlist(numbers_array, first_index, mid_position1 - 1, key);
           //if the key value is greater than the value at mid1 then recursively call from mid2+1 till last index
           if (key > numbers_array[mid_position2])
               return ternarysearchlist(numbers_array, mid_position2 + 1, last_index, key);
               //If key value is greater than value at mid1 and less than value at mid2 then recursively call from mid1+1 till mid2-1
           else
               return ternarysearchlist(numbers_array, mid_position1 + 1, mid_position2 - 1, key);
           //if key value is not found then return -1 determining unsuccessful search
       }
           return -1;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int array_length,i;
        //Taking the input length of an array
        System.out.println("Enter the length of an array ");
        array_length= sc.nextInt();
        int[] numbers_array=new int[array_length];
        //Taking the array elements as input
        System.out.println("Enter the "+array_length+ " Array elements");
        for(i=0;i<array_length;i++)
            numbers_array[i]=sc.nextInt();
        //Printing array elements before sorting
        System.out.println("The Array Elements  before sorting are: ");
        System.out.println(Arrays.toString(numbers_array));
        //Printing array elements after sorting
        Arrays.sort(numbers_array);
        System.out.println("Sorted Array elements are:");
        System.out.println(Arrays.toString(numbers_array));
        //Scanning the value to be searched in the array from the user
        System.out.println("Enter the key value to be searched");
        int key_value=sc.nextInt();
        //ternarysearchlist() returns the index of the element if found in the array or returns -1 if the element not found in the array
        int position= ternarysearchlist(numbers_array,0,array_length-1,key_value);
        //Successfull search which determines key value is found in the array
        if(position>=0)
            System.out.println(" Found at position "+ (position+1)+ " in the array");
        //Unsuccessful search which determines key value not found in the array
        else
            System.out.println("Not Found in the array");
    }
}
