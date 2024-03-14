import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReutReader {
    public String readFile(File file){

        try{
            BufferedReader bufferedReader =  new BufferedReader(new FileReader(file));
            String str;
            String textFile = "";
            try{
                while((str = bufferedReader.readLine())!=null){
                    if(!str.isEmpty()){
                        textFile +=str;
                    }
                    
                }
                bufferedReader.close();
            }
            catch (IOException e) {
                e.getMessage();
                return "File End";
            }
            return textFile;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return "File not Present";
        } 
        
    }


    public ArrayList<String> splitFileByTitle(String File){
        ArrayList<String> titleFile = new ArrayList<>();
        Pattern pattern = Pattern.compile("<TITLE>(.+?)</TITLE>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(File);
        while(matcher.find()){
            titleFile.add(matcher.group(1).replace("&lt;", "").replace(">", "").trim());
        }
        for (String i : titleFile) {
            if(i.contains("&lt;")){
                i = i.split("&lt;")[0];
            }
            else if(i.contains(">")){
                i = i.split(">")[0];
            }
        }

        return titleFile;
    }

    public ArrayList<String> splitFileByBody(String File){
        ArrayList<String> bodyFile = new ArrayList<>();
        Pattern pattern = Pattern.compile("<BODY>(.+?)</BODY>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(File);
        while(matcher.find()){
            bodyFile.add(matcher.group(1).replace("&#3;", "").replace(">", "").trim());
        }

        return bodyFile;
    }
    

    public static void main(String[] args) {
        ReutReader reader = new ReutReader();
        File file  = new File("reut2-009.sgm");
        for (String i : reader.splitFileByBody(reader.readFile(file))) {
            System.out.println(i + System.lineSeparator());   
        }
        System.out.println(reader.splitFileByBody(reader.readFile(file)).size());

    }

}
