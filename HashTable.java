//Import all the necessary packages
import java.io.*;
//Declaring a public class HashTable
public class HashTable {
    //Declaring all the global variables
    //Declaring the Hashtablesize variable for storing the Hash Table Size
    static int Hashtablesize;
    //Declaring the Total_number_Of_collisions variable for storing
    // the total collisions while inserting data to hashtable
    static int Total_number_Of_collisions;
    //Declaring the Hashtable_data array for storing the data in the Hash Table
    static String [] Hashtable_data;
    //Table_Index_Calculation() method for calculating the index of the location for the word
    //To be stored in the Hash Table
    static int Table_Index_Calculation(String word_string)
    {
        //Declaring Hashvalue_Of_word_string and initializing it to 0
        //This variable is used to calculate the hash value of the word
        int Hashvalue_Of_word_string =0;
        //Declaring a looping variable and initialising to 0
        int looping_variable=0;
        //Logic for calculating the has value for the string
        //Looping through the string
        //Adding the ASCII value of each and every letter in string and
        //Calculating the total hash value for the string
        //Looping from 0 index of string till length of the string
        for(looping_variable = 0; looping_variable< word_string.length(); looping_variable++)
        {
            //Logic for converting the alphabet into integer
            //And adding each ascii value of alphabet to Hashvalue_Of_word_string
            //To finally calculate the hashvalue of the string
            Hashvalue_Of_word_string +=(int)(word_string.charAt(looping_variable));
        }
        //Initialising the looping variable to 0
        looping_variable=0;
        //Declaring index_location_value and intialising it to 0
        int index_location_value =0;
        //Printing the word
        System.out.print(word_string +" -> ");
        //Looping until the condition breaks
        //Logic for calculating the hashing value using open addressing-quadratic
        //and calculating the collisions if the data is already present in the location
        while(true)
        {
            //Open Addressing-Quadratic
            //Calculating the index value for the word to be stored at that particular index
            index_location_value =(Hashvalue_Of_word_string +looping_variable*looping_variable)% Hashtablesize;
            //Printing the index value
            System.out.print(index_location_value +",");
            //Condition to check if the location at index_location_value is null or not
            // If null then no need to loop break the loop and return
            if(Hashtable_data[index_location_value]==null) {
                //Breaks the while loop if the location is null
                break;
            }
            //This wil be executed only if the location at index_location_value is not null
            //Increating the looping variable
            looping_variable++;
            //Increating the collisions
            Total_number_Of_collisions++;
        }
        System.out.println();
        //Return the empty location index value
        return index_location_value;
    }
    //Insert_Data_Into_HashTable() Method for inserting the data into the Hashtable
    //This checks load factor and increases the table size and rehashes it
    static void Insert_Data_Into_HashTable(String[] group_Of_words_data)
    {
        //Declaring number_of_words and initialising it to the number of words in the group_Of_words_data string array
        int number_of_words = group_Of_words_data.length;
        //Declaring and intialising the looping variable to 0
        int looping_variable=0;
        //Logic for looping through each and every word and checking the load factor value and inserting the data to the hash table
        for(looping_variable = 0; looping_variable< number_of_words; looping_variable++)
        {
            //Declaring the loadfactor and calculating the loadfactor value
            //Loadfactor can be given by number of values inserted/tablesize
            double loadfactor=(double)(looping_variable)/(double)(Hashtablesize);
            //Logic to check if the loadfactor is greater than 0.5
            //If the value is greater then We need to break the insertions
            // Double the table size and reinsert the values
            if(loadfactor>0.5)
            {
                //This will be executed only if the loadfactor exceeds 0.5
                System.out.println();
                //Printing the load factor exceeded message
                System.out.println("!!!Load Factor Exceeded!!!. Rehash it!!");
                System.out.println();
                System.out.println("Data Insertion into Hashtable after increasing the table size");
                //Break the loop if the loadfactor is greater than 0.5
                break;
            }
            //This will be executed only if the load factor is not greater than 0.5
            //Inserts the data into the hash table
            Hashtable_data[Table_Index_Calculation(group_Of_words_data[looping_variable])]= group_Of_words_data[looping_variable];
        }
        //Doubling the table size
        Hashtablesize *=2;
        //Again initialising the  Hashtable_data to the new Hashtablesize
        Hashtable_data = new String[Hashtablesize];
        //Logic for reinserting the data into hashtable after increasing the table size
        //Looping through all the words
        for(looping_variable = 0; looping_variable< number_of_words; looping_variable++)
        {
            //Reinserting the data to the Hashtable_data with new size
            Hashtable_data[Table_Index_Calculation(group_Of_words_data[looping_variable])]= group_Of_words_data[looping_variable];
        }
    }
    // Main function which calls all the other functions
    public static void main(String[] args) throws IOException {
        //Declaring List_of_Words[] String array and initialising it with 20 memory locations
        String List_of_Words[]=new String[20];
        //Declaring an object for File and storing the file location value in the file object
        File file = new File("C:\\Users\\gxp210011\\IdeaProjects\\Assignment6Hashing\\src\\Input_file.txt");
        //Declaring a BufferedReader object for reading the contents of file
        BufferedReader br = new BufferedReader(new FileReader(file));
        //Declaring and initialising the location value to 0
        int location=0;
        //Declaring string
        String string;
        //Printing the data in the file
        System.out.println("Data in the File is:");
        //Logic for looping through the entire file and reading all the words in the file un till the end of the file
        while ((string = br.readLine()) != null) {
            //Storing the string in the List_of_Words[] array
            List_of_Words[location++] = string;
            //Printing the string
            System.out.println(string);
        }
        //Initialising the Total_number_Of_collisions to 0 as there are no collisions initially
        Total_number_Of_collisions =0;
        //Initialising the  Hashtablesize to 31
        Hashtablesize =31;
        //Creating a Hashtable_data with Hashtablesize
        Hashtable_data =new String[Hashtablesize];
        //Printing the intial hash table size
        System.out.println("\nInitial Table Size is "+Hashtablesize);
        //Printing the words inserted into hashtable before increasing the table size
        System.out.println("Data Insertion into Hashtable before increasing the table size");
        //Calling the Insert_Data_Into_HashTable() method for inserting the values
        Insert_Data_Into_HashTable(List_of_Words);
        //Logic for printing the hashtable data
        System.out.println("Hash Table Implementation with data is:");
        //Declaring and intialising the looping variable to 0
        int looping_variable=0;
        //Looping through the entire hashtable for hash table size
        for(looping_variable= 0; looping_variable< Hashtablesize; looping_variable++)
        {
            //Printing the data in the hash table
            System.out.println(looping_variable+": "+ Hashtable_data[looping_variable]);
        }
        //Printing the total number of collisions
        System.out.println("Total Number of Collisions in the HashTable= "+ Total_number_Of_collisions);
    }
}
