package Impl;

import Interfaces.FileOperations;
import Interfaces.ReutReader;
import Interfaces.SentimentUtils;
import Interfaces.TextExtractor;

import java.util.ArrayList;

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
        System.out.println(titleList.toString());
        System.out.println(titleList.size());
        return titleList;
    }

    @Override
    public ArrayList<String>getTitles(String fileName){
       return titleExtractor(textExtractor.mappedData(reutReader.readFile(fileName)));
    }


    public static void main(String[] args) {
        SentimentUtils obj = new SentiMentUtilsImpl();
        obj.getTitles("reut2-009.sgm");
        FileOperations fops = new FileOperationsImpl();
        ArrayList<String> negativeWords = fops.getWordArray("negative-words.txt");
        ArrayList<String> positiveWords = fops.getWordArray("positive-words.txt");
        System.out.println(negativeWords.toString()+System.lineSeparator()+positiveWords.toString());
    }
}
