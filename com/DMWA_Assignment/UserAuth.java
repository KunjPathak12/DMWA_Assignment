package com.DMWA_Assignment;
import java.util.*;
import java.math.*;
import java.security.*;
import java.io.*;

public class UserAuth{
    UserCredentials userCredentials;
    HashMap<String, String>credentialsMap;
    Random rand = new Random();
    String Path = "com/DMWA_Assignment/credentials/userInfo";
    static Scanner scan = new Scanner(System.in);
    Scanner scan1 = new Scanner(System.in);
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

    public void storeCreds(HashMap<String,String>credentials, String filePath){

        try {
            File fileObj = new File(filePath);
            FileOutputStream fileOut= new FileOutputStream(fileObj);
           

            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(credentials);
            outputStream.flush();
            outputStream.close();
            fileOut.close();
        }

         
            
        catch (IOException e) {
            e.getMessage();
            System.out.println(e);
        }   

    }

    public void signUp(String username, String password){
        if(credentialsMap == null){
            credentialsMap = new HashMap<>();
        }
        credentialsMap.put(fetchUsername(username), encrptdPassword(password));
        for (String i: credentialsMap.keySet()) {
            System.out.println(i+":"+credentialsMap.get(i));
        }
        storeCreds(credentialsMap, Path);
        System.out.println("User Created Successfully!");      
    }

    

    public HashMap<String,String> fetchCreds(String filePath, HashMap<String,String>credentials){
        try {
            File readFile = new File(filePath);
            FileInputStream fileIn = new FileInputStream(readFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            credentials = (HashMap<String,String>)inputStream.readObject();

            inputStream.close();
            fileIn.close();
            // for(Map.Entry<String,String> m :credentials.entrySet()){
            //     System.out.println(m.getKey()+" : "+m.getValue());
            // }
            
        } 
        
        catch (Exception e) {
            e.getMessage();
            System.out.println(e);
        }
        return credentials;
    }


    public String genCaptcha(){
        String initcaptcha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890@#$%&";
        String captcha ="";
        while (captcha.length()<6) {
            captcha+= initcaptcha.toCharArray()[rand.nextInt(initcaptcha.length())];
        }             
        return captcha;
    }

    public boolean login(String userName, String password){
        
        credentialsMap = fetchCreds(Path, credentialsMap);
        String captcha = genCaptcha();
        System.out.println("Enter the Captcha: "+ captcha);
        String Captcha = scan1.nextLine();
        try {
            if(!credentialsMap.isEmpty()){
                if(!Captcha.equals(captcha)){
                    System.out.println("Captcha MissMatch");
                }
                else{

                    for (String i : credentialsMap.keySet()) {
                        if(i.equals(userName) && credentialsMap.get(i).equals(encrptdPassword(password))){
                            System.out.println("User Logged in Successful");
                            return true;
                        }
    
                        else{
                            System.out.println("Credentials MissMatch");
                            return false;
                        }
                    }
                }
                
            }
        } 
        
        catch (Exception e) {
            System.out.println("Unsuccessfull login Attempt");
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void authChoice(int choice){
        switch (choice) {
            case 1:
                signUp(scan1.nextLine(), scan1.nextLine());
                break;

            case 2:
                
                if(!login(scan1.nextLine(), scan1.nextLine())){
                    System.out.println("Can't Proceed further Login attempt failed");
                }
                else{
                    System.out.println("Welcome to YourSQL");
                }

            default:
                break;
        }
    }

    public static void main(String[] args) {
        UserCredentials userCred = new UserCredentials();
        UserAuth authObj = new UserAuth(userCred);
        authObj.authChoice(scan.nextInt());
        
    }
}