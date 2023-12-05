/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import TOOL.Inputter;

/**
 *
 * @author RLappc.com
 */
public class MainMenu {

    public String title;
    public int count = 1;
    public String[] optionList = new String[20];

    public MainMenu() {
        title = "Menu";
        optionList[0] = "Exit";
    }

    public MainMenu(String title) {
        this.title = title;
        optionList[0] = "Exit";
    }

    public void addOptions(String options) {
        optionList[count] = options;
        this.count++;
    }

//    public void printMenu(){
//        
//    }
//    
//    public void printSide(){
//        
//    }
    public void printMenu() {
        System.out.println("------------$ " + title + " $------------");
        for (int i = 1; i <= count - 1; i++) {
            System.out.println(i + ". " + optionList[i]);
        }
        System.out.println("0. Exit");
    }

    public int getOption() {
        return Inputter.getInteger("Please input your choice :", "The value should be between 0 and " + (count-1), 0, count-1);
    }
}
