package Impl;

import Interfaces.FileOperations;
import Interfaces.ReutReader;
import Interfaces.SentimentUtils;
import Interfaces.TextExtractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SentiMentUtilsImpl implements SentimentUtils {

    ReutReader reutReader = new ReutReaderImpl();
    TextExtractor textExtractor = new TextExtractorImpl();
    private ArrayList<String> titleExtractor(ArrayList<String> cleandText){

        ArrayList<String> titleList = new ArrayList<>();
        for(String i: cleandText){
            i = i.split(System.lineSeparator())[0];
            if(i.contains("Title: ")){
                i = i.replace("Title: ","");
                if(!i.isEmpty() || !i.isBlank()) titleList.add(i);
            }
        }

        return titleList;
    }

    @Override
    public ArrayList<String>getTitles(String fileName){
       return titleExtractor(textExtractor.mappedData(reutReader.readFile(fileName)));
    }


    public static void main(String[] args) {
        SentimentUtils obj = new SentiMentUtilsImpl();
        FileOperations fops = new FileOperationsImpl();
        ArrayList<String> negativeWords = fops.getWordArray("negative-words.txt");
        ArrayList<String> positiveWords = fops.getWordArray("positive-words.txt");
        HashMap<String, Integer> titleSentiment = new HashMap<String, Integer>();
        for (String i: obj.getTitles("reut2-009.sgm")){
            int counter = 0;
            List<String> eachTitle = Arrays.asList(i.toLowerCase().replace(",","").split(" "));
            for(String j: eachTitle){
                if (positiveWords.contains(j)) counter++;
                else if(negativeWords.contains(j)) counter--;
            }
            titleSentiment.put(i, counter);
        }
//        System.out.println(titleSentiment.values());


    }
}
