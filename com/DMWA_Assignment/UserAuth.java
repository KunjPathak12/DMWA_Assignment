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
    Query exitQuery = new Query();
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

    public HashMap<String,String> fetchCreds(String filePath, HashMap<String,String>credentials){
        try {
            File readFile = new File(filePath);
            FileInputStream fileIn = new FileInputStream(readFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            credentials = (HashMap<String,String>)inputStream.readObject();
            // for(Map.Entry<String,String> m :credentials.entrySet()){
            //     System.out.println(m.getKey()+" : "+m.getValue());
            // }
            inputStream.close();
            fileIn.close();
           
            
        } 
        
        catch (Exception e) {
            e.getMessage();
            System.out.println(e);
        }
        return credentials;
    }

    public void signUp(String username, String password){
        credentialsMap = fetchCreds(Path, credentialsMap);
        if(credentialsMap == null){
            credentialsMap = new HashMap<>();
        }

        else if(credentialsMap.keySet().contains(username)){
            System.out.println("User Already exist Please proceed to login");
            login(username, password);
        }

        else{
            credentialsMap.put(fetchUsername(username), encrptdPassword(password));
            // for (String i: credentialsMap.keySet()) {
            //     System.out.println(i+":"+credentialsMap.get(i));
            // }
            storeCreds(credentialsMap, Path);
            System.out.println("User Created Successfully!\nPlease Proceed to Login now");      
        }   
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
        // for (String i: credentialsMap.keySet()) {
        //         System.out.println(i+":"+credentialsMap.get(i));
        //     }
        if(!credentialsMap.containsKey(userName)){
            signUp(userName, password);
            return false;
        }

        String captcha = genCaptcha();
        System.out.println("Enter the Captcha: "+ captcha);
        String Captcha = scan1.nextLine();
        try {
            if(!credentialsMap.isEmpty()){
                if(!Captcha.equals(captcha)){
                    System.out.println("Captcha MissMatch");
                }
                else{

                    // for (String i : credentialsMap.keySet()) {
                        if(credentialsMap.containsKey(userName)){

                            credentialsMap.get(userName).equals(encrptdPassword(password));
                            
                            System.out.println("User Logged in Successful");
                            // System.out.println("Welcome to YourSQL");2
                            // break;
                        }
    
                        else{
                            // System.out.println(credentialsMap.get(i));
                            System.out.println("Credentials MissMatch");
                            return false;
                        }

                    // }
                    return true;
                }
                
            }

            
        }
        
        catch (Exception e) {
            System.out.println("Unsuccessfull login Attempt");
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean authChoice(String choice){
        switch (choice) {
            case "1":
                signUp(scan1.nextLine(), scan1.nextLine());
                scan1.close();
                return false;

            case "2":
                
                if(!login(scan1.nextLine(), scan1.nextLine())){
                    System.out.println("Can't Proceed further Login attempt failed");
                    scan1.close();
                    // break;
                    return false;
                }

                else{
                    System.out.println("Welcome to YourSQL");
                    return true;
                    // break;
                }
                
            case "3":
                // System.out.println("Print Exit to Exit");
                exitQuery.exit();
                return true;
                // break;
                

            default:
                return false;
        }
    }
    // public static void main(String[] args) {
    //     UserCredentials userCred = new UserCredentials();
    //     UserAuth authObj = new UserAuth(userCred);
    //     System.out.println("1:SignUp\n2:LogIn\n3:LogOut");
    //     authObj.authChoice(scan.nextLine());
        
    // }
}