package com.DMWA_Assignment;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;



public class Query {
    private ArrayList<String> queryTypes = new ArrayList<>(Arrays.asList("create", "insert", "select", "exit"));
    static Scanner scan = new Scanner(System.in);

    public class Create{
        String createQuery;
        public String dbName;
        public String table;
        public Create(String createQuery){
            this.createQuery = createQuery;
        }
    }

    public class CreateDB extends Create{
        String fileName;
        public CreateDB(String createQuery) {
            super(createQuery);
            // dbName = createQuery.toLowerCase().trim().split("database")[1].trim();
            fileName = dbName;
        }
        public String print(){
            // System.out.println();
            return "Database created";
        }
    }

    public class CreateTable extends Create{
        String tableName;
        public CreateTable(String createQuery) {
            super(createQuery);
            // table = createQuery.toLowerCase().trim().split("table")[1].trim();
            tableName =table;
        }

        public String print(){
            // System.out.println();
            return "Table Created";
        }

    }

    public String parseQuery(String initQuery){
        String str =initQuery.split(" ")[0].toLowerCase();
        // System.out.println(str);
        String resp="";
        if(str.equals("create")){
            
            if(initQuery.contains("database")){
                CreateDB create = new CreateDB(initQuery);
                resp=create.print();
                
            }

            else if(initQuery.contains("table")){
                CreateTable create = new CreateTable(initQuery);
                resp = create.print();
            }

            
        }

        else{
            resp = "Create not found in query";
        }
        // return "create Query Executed";
        return resp;
    }

    public void exit(){
        System.out.println("Enter proper exit command to exit");
        while(true){
            String keyBind = scan.nextLine();
            if(keyBind.toLowerCase().equals("exit")){
            
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
        }
    }

    public static void main(String[] args) {
        
        // System.out.println(create1.dbName);
        // System.out.println(create1.print()+" "+create2.print());
        Pattern ptn = Pattern.compile("");
        Matcher matcher = ptn.matcher("[int]");
        String str1 = scan.nextLine();
        while(!str1.contains(";")){
            str1+=scan.nextLine();
        }
        // System.out.println(str1);
        Query query = new Query();
        System.out.println(query.parseQuery(str1));
        // Create create = query.new Create(str1);
        // CreateDB create1 = query.new CreateDB();
        // CreateTable create2 = query.new CreateTable("Create database kunj1");
    }
}
