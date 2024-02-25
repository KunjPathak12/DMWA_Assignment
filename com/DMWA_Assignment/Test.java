package com.DMWA_Assignment;
import java.util.*;

public class Test {
    // Online Java Compiler
// Use this editor to write, compile and run your Java code online
    public static void main(String[] args) {
        String command= "";
        boolean checkCommit = false;
        ArrayList<String> queryList = new ArrayList(); 
        Scanner scan = new Scanner(System.in);
        while(true){
            command = scan.nextLine();
            queryList.add(command);
            if(command.toLowerCase().trim().equals("rollback;")){
               queryList.clear();
               break;
            }
            else if(command.toLowerCase().trim().equals("commit;")){
                checkCommit = true;
                break;
            }

            if(checkCommit){
                for(String i: queryList){
                    System.out.println(i);
                }
            }
        }
    }

}