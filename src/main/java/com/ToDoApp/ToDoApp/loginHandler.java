package com.ToDoApp.ToDoApp;
import java.io.*;
import java.util.Scanner;

public class loginHandler {
    public static String saveCredentials (String username, String password) throws IOException {
        // Read the text file
  
        // File path is passed as parameter
        File file = new File(
            "src/main/java/com/ToDoApp/ToDoApp/users.txt");
 
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
 
        // Creating an object of BufferedReader class
        BufferedReader br
            = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
 
            if (st.split(";")[0].equals(username)) {
                if (st.split(";")[1].equals(password)) {
                    return "PASS";
                }
            }
        }
        br.close();

        return "FAIL";
    }

    public static String findToDoItems (String username, String password) throws IOException {
                // Read the text file
        String todoitems = "";
        // File path is passed as parameter
        File file = new File(
            "src/main/java/com/ToDoApp/ToDoApp/users.txt");
 
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
 
        // Creating an object of BufferedReader class
        BufferedReader br
            = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
 
            if (st.split(";")[0].equals(username)) {
                if (st.split(";")[1].equals(password)) {
                    todoitems = st.split(";")[2];
                }
            }
        }
        br.close();

        return todoitems;
    }


    public static void addNewItem(String username, String password, String itemToAdd) throws IOException {
        // Read the txt file
        String fileToSave = "";

                // File path is passed as parameter
                File file = new File(
                    "src/main/java/com/ToDoApp/ToDoApp/users.txt");
         
                // Note:  Double backquote is to avoid compiler
                // interpret words
                // like \test as \t (ie. as a escape sequence)
         
                // Creating an object of BufferedReader class
                BufferedReader br
                    = new BufferedReader(new FileReader(file));
         
                // Declaring a string variable
                String st;
                // Condition holds true till
                // there is character in a string
                int count = 0;
                while ((st = br.readLine()) != null) {
                    if (count != 0) {
                        if (st.split(";")[0].equals(username) && st.split(";")[1].equals(password)) {
                                fileToSave = fileToSave + "*" + st.split(";")[0]+ ";" + st.split(";")[1]+ ";" + st.split(";")[2] + "," + itemToAdd; 
                        } else {
                            fileToSave = fileToSave + "*" + st.split(";")[0]+ ";" + st.split(";")[1]+ ";" +st.split(";")[2];
                        }
                    } else {
                        if (st.split(";")[0].equals(username) && st.split(";")[1].equals(password)) {
                            fileToSave = fileToSave+ st.split(";")[0]+ ";" + st.split(";")[1]+ ";" + st.split(";")[2] + "," + itemToAdd; 
                    } else {
                        fileToSave = fileToSave + st.split(";")[0]+ ";" + st.split(";")[1]+ ";" +st.split(";")[2];
                    }
                    }
                    count ++;
                }
                    br.close();
        

        System.out.println("---------------------------------");
        System.out.println(fileToSave);
        String fileToSaveArr [] = {fileToSave};
        // Write the contents of file to save to the file
        try {
         fileToSaveArr = fileToSave.split("*");
        } catch (Exception e) {
        }
        try {
            File fileToWrite=new File("src/main/java/com/ToDoApp/ToDoApp/users.txt");
        
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i=0;i<fileToSaveArr.length;i++){
                writer.write (fileToSaveArr[i]);
            }
        
            //Close writer
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        

    }
}




