package com.DMWA_Assignment;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Query {
    // private ArrayList<String> queryTypes = new ArrayList<>(Arrays.asList("create", "insert", "select", "exit"));
    static Scanner scan = new Scanner(System.in);
    FileOperations fops = new FileOperations();
    boolean checkExit = false;
    String selectedDB= "testdb";
    // ArrayList<String> tableData;
    public class Create{
        String createQuery;
        public String dbName;
        public String tableDB;
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
        String tablePath;
        String tableName;
        String tableStatus;
        public CreateTable(String createQuery) {
            super(createQuery);
            if(selectedDB.equals("No DB Selected")){
                System.out.println("select db first");
            }
            // table = 
            tableDB = selectedDB;
            tableName = createQuery.toLowerCase().trim().split("table")[1].split("\\(")[0].trim();
            tablePath = "com" + File.separator + "DMWA_Assignment" + File.separator + "resources" + File.separator + tableDB + File.separator + tableName + ".csv";
            tableStatus = fops.createCsvforDb(tablePath);
        }
        String columns = createQuery.split("^([^(]+[(])")[1];
        StringBuilder columnInfo = new StringBuilder();
        String [] strArr = columns.split(",");
        public String print(){
            for(int i=0;i<strArr.length;i++){
                switch (strArr[i].trim().split(" ")[1].split("\\(")[0]) 
                {
                    case "int":
                        columnInfo.append("INT_");
                        break;
                    case "float":
                        columnInfo.append("FLOAT_");
                        break;
                    case "text":
                        columnInfo.append("TEXT_");
                        break;
                    case "double":
                        columnInfo.append("DOUBLE_");
                        break;
                    case "long":
                        columnInfo.append("LONG_");
                        break;
                    case "varchar":
                        columnInfo.append("TEXT_");
                        break;
                    default:
                        break;
                }

                if(i == strArr.length-1){
                    columnInfo.append(strArr[i].trim().split(" ")[0]);
                }
                else{
                    columnInfo.append(strArr[i].trim().split(" ")[0]).append(",");
                }
            }

            columnInfo.append("\r\n");
            
            // tableData = new ArrayList<>(Arrays.asList(columnInfo.toString().split(",")));
            String tableData = columnInfo.toString();
            // System.out.println(str);
            fops.writeIntoCsv(tablePath, tableData);
            return tableStatus;
        }

    }

    public String useDatabase(String useQuery){
        String dbname = useQuery.toLowerCase().split("use")[1].split(";")[0].trim();
        String path = "com" + File.separator + "DMWA_Assignment" + File.separator + "resources" + File.separator + dbname;
        // System.out.println(path);
        if(fops.filePresent(path)){
            System.out.println("Selected Database is: "+dbname);
            return dbname;
        }
        else{
            String rval = "Database Not Present on System";
            return rval;
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

            else if(initQuery.toLowerCase().contains("table")){
                System.out.println(initQuery);
                CreateTable newTable = new CreateTable(initQuery);
                    // CreateDB data = new CreateDB(initQuery);
                resp = newTable.print();
 
            }

            
        }

        else if(str.equals("use")){
            selectedDB = useDatabase(initQuery);
            // resp = parseQuery();
            
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
        System.out.println(query.parseQuery());
    }
}
