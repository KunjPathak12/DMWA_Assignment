package com.DMWA_Assignment;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class FileOperations {

    public boolean filePresent (String path){
        
        File fileName = new File(path);
        if(fileName.exists()){
            System.out.println("Already Present");
            return true;
        }
        else {
            return false;
        }
        
    }

    public String createDbFolder(String path){
        Path dirPath = Paths.get(path);
        File file = dirPath.toFile();
        if(!filePresent(path)){
            try {
                boolean createFolder = file.mkdirs();
                if(createFolder){
                    System.out.println("DB Successfully created at path");
                }
            } 
            
            catch (Exception e) {
                e.printStackTrace();
                return "Can't create DB";
            }
            return path;
        }

        else{
            return "Database already present";
        }
        
    }

    public String createCsvforDb(String path){
        if(!filePresent(path)){
            File file = new File(path);
            return("Database creation success: "+path);
        }
        else{
            return "Database Already Present can't overWrite existing DB!";
        }
    }

    // public static void main(String[] args) {
    //     FileOperations fops = new FileOperations();
    //     fops.checkFolder("com/DMWA_Assignment/resources/Kunj.txt");
    // }
}
