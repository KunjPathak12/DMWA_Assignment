package com.DMWA_Assignment;

import java.util.Scanner;

public class UserAuth{
    UserCredentials userCredentials;
    public UserAuth(UserCredentials userCredentials){
        this.userCredentials = userCredentials;
    }

    public String fetchUsername(String username){
        userCredentials.setUserName(username);
        return(userCredentials.getUSerName());
    }

    public static void main(String[] args) {
        UserCredentials userCred = new UserCredentials();
        UserAuth authObj = new UserAuth(userCred);
        Scanner scan = new Scanner(System.in);
        System.out.println(authObj.fetchUsername(scan.nextLine()));
    }
}