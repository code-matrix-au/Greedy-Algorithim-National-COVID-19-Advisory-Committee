import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

/**
 * COMP3010 Assignment 1 
 * Name: Ashwin Bhanderi
 * ID: 44164971
 * Date: 04/09/2021
 */

public class ass1_comp3010 {
    static int gSize; // group size 
    static HashMap<Long, ArrayList<Integer>> frequency = new HashMap<Long, ArrayList<Integer>>(); // holds frequency of unique number and which group they are from.
    static ArrayList<Long> finalList = new ArrayList<Long>(); // Holds the final list to be printed at end.

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // read the input keyboard
        System.out.println("Enter the number of groups from which you must find representative");
        gSize = in.nextInt(); // Store the first number as group size
        System.out.println("Group size set to:" + gSize);
        System.out.println("Now enter the list of members of each group (one group per line, each terminated by 0)");

        while (gSize != 0) { // read the input until all group have been received.

            long input = in.nextInt();
            if (input == 0) { // reduce the group size if 0 is read
                gSize--; // reduces until while loop exit condition is met.
            } else {
                if (frequency.containsKey(input)) { // check if the key exist in our database
                    
                    ArrayList<Integer> temp = frequency.get(input);  // To hold the arraylist from database
                    temp.add(0, gSize);
                    frequency.put(input, temp);
                } else { // if the item does not exist create new entry and add to the database.
                    ArrayList<Integer> eTemp = new ArrayList<Integer>();
                    eTemp.add(gSize);
                    frequency.put(input, eTemp);
                }
            }
        }

        while(frequency.size() != 0){ //loop until all the items are checked and removed.
            int size = 0; // the size of the first group
            Long id= 0L; // store the user
            ArrayList<Integer> temp = new ArrayList<Integer>(); //list of items to be removed
            ArrayList<Long> remove = new ArrayList<Long>(); // list of groups to be removed

            for (HashMap.Entry<Long, ArrayList<Integer>> entry : frequency.entrySet()) {
                if (entry.getValue().size() == 0){// if list is empty, add group to remove list
                    remove.add(0, entry.getKey()); 
                }
                else if (entry.getValue().size() >= size) { //get largest group of unique numbers.
                    size = entry.getValue().size();
                    id = entry.getKey();
                    temp = entry.getValue();
                }
            }

            for(Long group : remove){ // remove groups that are empty
                frequency.remove(group);
            }

            if(id != 0L){
                finalList.add(id); // Add the representitive on the final list
                frequency.remove(id); // remove the largest group selected from list.
            }

            for (HashMap.Entry<Long, ArrayList<Integer>> entry : frequency.entrySet()) {
                entry.getValue().removeAll(temp); // remove values in temp found in all groups 
            }
        }

        System.out.println(finalList.size()); // print the final list
        for(Long num : finalList){
            System.out.print(num +" ");
        }
    }
}
