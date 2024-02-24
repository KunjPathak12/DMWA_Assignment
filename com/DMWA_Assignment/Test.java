package com.DMWA_Assignment;

public class Test {
    // Online Java Compiler
// Use this editor to write, compile and run your Java code online
    public static void main(String[] args) {
        String createQuery = "CREATE TABLE Persons (    PersonID int,    LastName varchar(255),    FirstName varchar,    Address varchar,    City varchar;";
        
        // System.out.println(createQuery.toLowerCase().trim().split("table")[1].split("\\(")[1].trim().split(",")[0].trim());
        String content = createQuery.split("^([^(]+[(])")[1];
        StringBuilder contentToWrite = new StringBuilder();
        String[] arrayToIterate = content.split(",");
        for (int i = 0; i < arrayToIterate.length; i++) 
        {
            switch (arrayToIterate[i].trim().split(" ")[1].split("\\(")[0]) 
            {
                case "int":
                    contentToWrite.append("I_");
                    break;
                case "float":
                    contentToWrite.append("F_");
                    break;
                case "varchar":
                    contentToWrite.append("S_");
                    break;
                case "double":
                    contentToWrite.append("D_");
                    break;
                case "char":
                    contentToWrite.append("C_");
                    break;
            }

            if (i == arrayToIterate.length - 1) {
                contentToWrite.append(arrayToIterate[i].trim().split(" ")[0]);
            } else {
                contentToWrite.append(arrayToIterate[i].trim().split(" ")[0]).append(",");
            }
        }

        contentToWrite.append("\r\n");
        System.out.println(contentToWrite.toString());
    }
}