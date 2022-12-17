package com.ToDoApp.ToDoApp;
import java.io.*;
import java.util.Scanner;

public class loginHandler {
    
    // Used to verify the credentials that a user has inputted
    public static String verifyCredentials (String username, String password) throws IOException {
        /* Read the text file
           For each line in the text file, check if the username == the username that the user hass inputted
           If so, check the password
           If they match, return "PASS"
           If no match was found in the entire file, return "FAIL"*/
        
        // Set up initial code
        File file = new File("src/main/java/com/ToDoApp/ToDoApp/users.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        
        // For each line, st = the current line of the file
        // While the end of the file hasn't been reached
        while ((st = br.readLine()) != null) {
            // Check the username and password
            if (st.split(";")[0].equals(username)) {
                if (st.split(";")[1].equals(password)) {
                    return "PASS";
                }
            }
        }
        
        br.close();
        return "FAIL";
    }
    
    // Used to find all the todo items belongin to the user
    public static String findToDoItems (String username, String password) throws IOException {
        /*
        Read each line of the file
        If the current line contains the users username and password, look at the line in more detail
        The line consists of username;password;item1,item2,etc
        Split the line to get the list of items such as "item1,item2,etc" and return it
        If no items found, return ""
        */
        // Set initial values
        String todoitems = "";
        File file = new File("src/main/java/com/ToDoApp/ToDoApp/users.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        
        
        while ((st = br.readLine()) != null) {
            // Check the username and password
            if (st.split(";")[0].equals(username)) {
                if (st.split(";")[1].equals(password)) {
                    // Get the list of items and split it into the individual items
                    todoitems = st.split(";")[2];
                }
            }
        }
        
        br.close();
        return todoitems;
    }

    // Used to add a new to do item to the file
    public static void addNewItem(String username, String password, String itemToAdd) throws IOException {
        /*
        STEP 1:
            Read the file and save it to a string with each line seperated by "*"/array (string used instead of array since it is easier to add new items to a string in Java)
            For every line, check if the username and password are correct
            If they are, save the line to the array, but add the item to be added on the end
        
        STEP 2:
            Replace the file with the text in the array
        */
        
        // STEP 1
        String fileToSave = "";
        File file = new File("src/main/java/com/ToDoApp/ToDoApp/users.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int count = 0;
        
        // Look at every line in the file
        while ((st = br.readLine()) != null) {
            // If there is one line in the file:
            if (count != 0) {
                // Save the line + the item to array if the login credentials in the file match the users credentials
                // Otherwise, add the line to array without any extra data
                if (st.split(";")[0].equals(username) && st.split(";")[1].equals(password)) {
                    fileToSave = fileToSave + "*" + st.split(";")[0]+ ";" + st.split(";")[1]+ ";" + st.split(";")[2] + "," + itemToAdd; 
                } else {
                    fileToSave = fileToSave + "*" + st.split(";")[0]+ ";" + st.split(";")[1]+ ";" +st.split(";")[2];
                }
            // If there is more than one line in the file:
            } else {
                // Save the line + the item to array if the login credentials in the file match the users credentials
                // Otherwise, add the line to array without any extra data
                if (st.split(";")[0].equals(username) && st.split(";")[1].equals(password)) {
                    fileToSave = fileToSave+ st.split(";")[0]+ ";" + st.split(";")[1]+ ";" + st.split(";")[2] + "," + itemToAdd; 
            } else {
                fileToSave = fileToSave + st.split(";")[0]+ ";" + st.split(";")[1]+ ";" +st.split(";")[2];
            }
            count ++;
        }
        // This line causes the while loop to exit for no reason
        //br.close();
        }
        System.out.println();
        System.out.println();
        
        // STEP 2
        String fileToSaveArr [] = {fileToSave};
        
        // Turn the string storing the file into an array
        try {
            fileToSaveArr = fileToSave.split("*");
        } catch (Exception e) {e.printStackTrace();}
        
        // For every line in the array, write it into the file, replacing the text currently in the file with the new text
        try {
            File fileToWrite=new File("src/main/java/com/ToDoApp/ToDoApp/users.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i=0;i<fileToSaveArr.length;i++){
                writer.write (fileToSaveArr[i]);
            }
            writer.close();
        } catch(Exception e) {e.printStackTrace();}
    }
}




