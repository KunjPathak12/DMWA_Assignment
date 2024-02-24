package com.DMWA_Assignment;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Query {
    private ArrayList<String> queryTypes = new ArrayList<>(Arrays.asList("create", "insert", "select", "exit"));
    static Scanner scan = new Scanner(System.in);
    FileOperations fops = new FileOperations();
    boolean checkDb = false;

    public class Create{
        String createQuery;
        public String dbName;
        public String table;
        public Create(String createQuery){
            this.createQuery = createQuery;
        }
    }

    public class CreateDB extends Create{
        String folderPath;
        String DbStatus;
        public CreateDB(String createQuery) {
            super(createQuery);
            dbName = createQuery.toLowerCase().trim().split("database")[1].split(";")[0].trim();
            folderPath = "com" + File.separator + "DMWA_Assignment" + File.separator + "resources" + File.separator + dbName;
            DbStatus = fops.createDbFolder(folderPath);
        }

        public String print(){
            return DbStatus;
        }
    }

    public class CreateTable extends Create{
        String tableName;
        public CreateTable(String createQuery) {
            super(createQuery);
            // System.out.println(createQuery);
            tableName = createQuery;
            // tableName = table;
        }

        public String print(){
            return tableName.toUpperCase();
        }

    }

    public boolean useDatabase(String useQuery){
        String dbname = useQuery.toLowerCase().split("use")[1].split(";")[0].trim();
        String path = "com" + File.separator + "DMWA_Assignment" + File.separator + "resources" + File.separator + dbname;
        System.out.println(path);
        if(fops.filePresent(path)){
            System.out.println("Selected Database is: "+dbname);
            return true;
        }
        else{
            System.out.println("Database Not Present on System");
            return false;
        }
        

    }

    public void exit(){

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

    public String parseQuery(){
        String initQuery = scan.nextLine();
        while(!initQuery.contains(";")){
            initQuery+=scan.nextLine();
        }
        
        String strArr[] = initQuery.toLowerCase().split(" ");
        String str = strArr[0];
        String resp="";
        if(str.equals("create")){
            
            if(initQuery.contains("database")){
                CreateDB create = new CreateDB(initQuery);
                resp = create.print();
                
            }

            else if(initQuery.contains("table")){
                // System.out.println(initQuery);
                if(checkDb){
                    // CreateTable newTable = new CreateTable(initQuery);
                    CreateDB data = new CreateDB(initQuery);
                    resp = data.print();
                }
                else{
                    resp = "Select database first";
                }
 
            }

            
        }

        else if(str.equals("use")){
            checkDb = useDatabase(initQuery);
            if(checkDb){
                resp = parseQuery();
            }
        }

        else if(initQuery.trim().contains("exit;")){
            exit();
        }

        else{
            resp = "Create not found in query";
        }
        
        return resp;
    }


    public static void main(String[] args) {
        
        Pattern ptn = Pattern.compile("");
        Matcher matcher = ptn.matcher("[int]");
        
        Query query = new Query();
        // CreateTable tb1 = query.new CreateTable("");
        // System.out.println(tb1.print());
        System.out.println(query.parseQuery());
        // System.out.println();

        // System.out.println(str1.split("table")[1].split(";")[0]);
    }
}
