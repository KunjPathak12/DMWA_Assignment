import Impl.ReutReaderImpl;
import Impl.TextExtractorImpl;
import Interfaces.ReutReader;
import Interfaces.TextExtractor;

import java.util.ArrayList;

public class MainProblem3 {
    public static void main(String[] args) {
        ReutReader reutReader = new ReutReaderImpl();
        TextExtractor textExtractor = new TextExtractorImpl();
        ArrayList<String> titleList = new ArrayList<>();
        for(String i: textExtractor.mappedData(reutReader.readFile("reut2-009.sgm"))){
            i = i.split(System.lineSeparator())[0];
            if(i.contains("Title: ")){
                i = i.replace("Title: ","");
                if(!i.isEmpty() || !i.isBlank()) titleList.add(i);
            }
        }
        System.out.println(titleList.toString());
        System.out.println(titleList.size());
    }

}
