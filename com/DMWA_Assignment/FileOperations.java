package com.DMWA_Assignment;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class FileOperations {

    public boolean filePresent (String path){
        
        File fileName = new File(path);
        if(fileName.exists()){
            // System.out.println("File Present check ok");
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
            return "Database already present can't overwrite the existing one";
        }
        
    }

    public String createCsvforDb(String path){
        if(!filePresent(path)){
            File file = new File(path);
            try {
                file.createNewFile();
                return("Table Created: "+path);
            } 
            
            catch (IOException e) {
                e.printStackTrace();
                return "Table Creation error";
            }

            
        }
        else{
           
            return "Table Already Present can't create existing Table!";
        }
    }

    public String writeIntoCsv (String path, String data){
        try {
            Path out = Paths.get(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile(),true));
            writer.write(data+System.lineSeparator());
            writer.close();
            return "Table Written";
        } catch (Exception e) {
            e.printStackTrace();
            return "Couldn't write the table";
        }
        
        
    }

    public String readColumnLength(String path){
        String column;
        try {
            BufferedReader brTest = new BufferedReader(new FileReader(path));
            column = brTest.readLine();
            return column;
        } 
        
        catch (IOException e) {
            e.printStackTrace();
            return "Empty File";
        }
        
    }

    public String readFileForSelect(String Path){
        String content = null;
        if(filePresent(Path)){
            File file = new File(Path);
            try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
                while( (content = br.readLine()) != null) {
                    System.out.println(content); 
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error reading the table";
            }
            return System.lineSeparator()+"Table Fetching Complete";
        }

        else{
            return "Error reading the table";
        }
    }

    // public String writetoTableCsv(String path, String data){
    //     CSWriter 
    //     return "";
    // }

}
