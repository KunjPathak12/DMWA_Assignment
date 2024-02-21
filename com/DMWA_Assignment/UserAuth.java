package com.DMWA_Assignment;
import java.util.*;
import java.math.BigInteger;
import java.security.*;

public class UserAuth{
    UserCredentials userCredentials;
    HashMap<String, String>credentialsMap;
    Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    public UserAuth(UserCredentials userCredentials){
        this.userCredentials = userCredentials;
        
    }

    public String fetchUsername(String username){
        userCredentials.setUserName(username);
        return(userCredentials.getUSerName());
    }

    public String fetchPassword(String password){
        
        userCredentials.setPassword(password);
        return(userCredentials.getPassword());
    }

    public String encrptdPassword(String password){
        try  {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(fetchPassword(password).getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        
        catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    public void signUp(String username, String password){
        if(credentialsMap == null){
            credentialsMap = new HashMap<>();
        }
        credentialsMap.put(fetchUsername(username), encrptdPassword(password));
        for (String i: credentialsMap.keySet()) {
            System.out.println(i+":"+credentialsMap.get(i));
        }
        System.out.println("User Created Successfully!");      
    }
    
    public String genCaptcha(){
        String initcaptcha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890@#$%&";
        String captcha ="";
        while (captcha.length()<6) {
            captcha+= initcaptcha.toCharArray()[rand.nextInt(initcaptcha.length())];
        }             
        return captcha;
    }

    public void login(String userName, String password){
        String captcha = genCaptcha();
        System.out.println("Enter the Captcha: "+ captcha);
        String Captcha = scan.nextLine();
        try {
            if(!credentialsMap.isEmpty()){
                if(!Captcha.equals(captcha)){
                    System.out.println("Captcha MissMatch");
                }
                else{

                    for (String i : credentialsMap.keySet()) {
                        if(i.equals(userName) && credentialsMap.get(i).equals(encrptdPassword(password))){
                            System.out.println("User Logged in Successful");
                            return;
                        }
    
                        else{
                            System.out.println("Credentials MissMatch");
                        }
                    }
                }
                
            }
        } 
        
        catch (Exception e) {
            System.out.println("Unsuccessfull login Attempt");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        UserCredentials userCred = new UserCredentials();
        UserAuth authObj = new UserAuth(userCred);
        
        // authObj.genCaptcha();
        authObj.login(scan.nextLine(), scan.nextLine());
        // authObj.signUp(scan.nextLine(),scan.nextLine());

        
    }
}