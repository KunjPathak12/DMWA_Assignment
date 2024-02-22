package com.DMWA_Assignment;
import java.awt.*;
import java.awt.event.*;
import java.util.*;



public class Query {
    private ArrayList<String> queryTypes = new ArrayList<>(Arrays.asList("create", "insert", "select", "exit"));
    static Scanner scan = new Scanner(System.in);

    public String create(String CreateQuery){
        // switch (CreateQuery) {
        //     case value:
                
        //         break;
        
        //     default:
        //         break;
        // }
        
        return "created";
    }

    public void exit(){
        System.out.println("Enter proper exit command to exit");
        while(true){
            String keyBind = scan.nextLine();
            if(keyBind.toLowerCase().equals("exit")){
            
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_C);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_C);
                    System.out.println("Exiting from YourSQL Bye!");
                    return ;
                } 

                catch (AWTException e) {
                    e.getMessage();
                    exit();
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
