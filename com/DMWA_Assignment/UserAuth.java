package com.DMWA_Assignment;
import java.util.*;
import java.math.BigInteger;
import java.security.*;

public class UserAuth{
    UserCredentials userCredentials;
    HashMap<String, String>credentialsMap = new HashMap<>();
    Random rand = new Random();
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
    public void login(String uerName, String Password, String Captcha){
        
    }

    

    public static void main(String[] args) {
        UserCredentials userCred = new UserCredentials();
        UserAuth authObj = new UserAuth(userCred);
        Scanner scan = new Scanner(System.in);
        // authObj.signUp(scan.nextLine(), scan.nextLine());
        System.out.println(authObj.genCaptcha());
        scan.close();
    }
}