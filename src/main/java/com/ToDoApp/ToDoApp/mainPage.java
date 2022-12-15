/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ToDoApp.ToDoApp;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jacob MacLeod
 */

// This line is important - it's the only way the code knows to run the function when the user goes to the main page
@Controller
public class mainPage {
    public String items = "";
	@GetMapping("/")
	public String index(Model model) {
            // If the @Controller tag is @Controller, it looks for index.html in resources. If not, it returns the string. For example, if it is @RestController
            // It returns the string
            model.addAttribute("items", "I hold your todo items");
		return "index";
	}
        
        @GetMapping("/uploadtodo")
        public String upload(@RequestParam(name="items", required=false, defaultValue="World") String itemUploaded, Model model) {
            items = items + "," + itemUploaded;
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