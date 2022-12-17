/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ToDoApp.ToDoApp;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import com.ToDoApp.ToDoApp.loginHandler.*;

/**
 *
 * @author Jacob MacLeod
 */

// This line is important - it's the only way the code knows to run the function when the user goes to the main page
@Controller
public class mainPage {
    public String items = "";
    public String username = "";
    public String password = "";
    
    // When user first accesses the app get them to sign in
    @GetMapping("/")
    public String loadSignInPage(Model model) {
        return "signIn";
    }
    
    // When the user clicks the sign in button, going to http://localhost:8080/signIn?credentials=username,password
    @GetMapping("/signIn")
    public String signIn(@RequestParam(name="credentials", required=false, defaultValue="World") String credentials, Model model) {
        System.out.println(credentials);
        username = credentials.split(",")[0];
        password = credentials.split(",")[1];

        String passed = "FAIL";

        try {
            passed = loginHandler.verifyCredentials(username, password);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (passed.equals("PASS")) {
            try {
                items = loginHandler.findToDoItems(username, password);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            model.addAttribute("items", items);
            return "index";
        } else {
            return "signIn";
        }
    }
        
        @GetMapping("/uploadtodo")
        public String upload(@RequestParam(name="items", required=false, defaultValue="World") String itemUploaded, Model model) {
            items = items + "," + itemUploaded;
            try {
                loginHandler.addNewItem(username, password, itemUploaded);
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("items", items);
            return "index";
        }
        
        //deleteitem?itemToDelete=
        @GetMapping("/deleteitem")
        public String delete(@RequestParam(name="itemToDelete", required=false, defaultValue = "World") String itemToDelete, Model model) {
            // Delete the required item
            String[] itemsArr = items.split(",");
            String newItemList = "";
            
            // For line in itemsArr
            for (int i=0;i<itemsArr.length;i++){
                // if the item != the item to delete
                if (itemsArr[i].equals(itemToDelete) == false) {
                    newItemList = newItemList + itemsArr[i] + ",";
                }
            }
            
            items = newItemList;
            
            model.addAttribute("items", items);
            return "index";
        }
}
