package com.DMWA_Assignment;

import java.util.Scanner;

public class Main {
    UserCredentials userCredentials;
    public static void main(String[] args) {
        UserCredentials userCreds = new UserCredentials();
        Scanner scan = new Scanner(System.in);
        UserAuth auth = new UserAuth(userCreds);
        String defStr = "Your SQL> ";
        Query query = new Query();
        while(true){
            System.out.println(defStr+"Welcome to Your SQL PLease Proceed with Login");
            System.out.println("Please enter your Authentication choice \n1:SignUp\n2:LogIn\n3:Exit");
            if(auth.authChoice(scan.nextLine())){
                while(true){
                    try {
                        query.parseQuery();
                    } catch (Exception e) {
                        // e.getLocalizedMessage();
                        // System.out.println("Exited YourSQL System");
                    }
                    
                }
                
            }
        }
    }
}
