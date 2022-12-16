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
 
            if (st.split(",")[0].equals(username)) {
                if (st.split(",")[1].equals(password)) {
                    return "PASS";
                }
            }
        }
        br.close();

        return "FAIL";
    }

}

