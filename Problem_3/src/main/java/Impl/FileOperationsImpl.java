package Impl;

import Interfaces.FileOperations;

import java.io.*;
import java.util.ArrayList;

public class FileOperationsImpl implements FileOperations {

    private ArrayList<String> readFile(String filePath){
        File file = new File(filePath);
        ArrayList<String> wordList = new ArrayList<>();
        try {
            BufferedReader readFile = new BufferedReader(new FileReader(file));
            String word = "";
            while((word = readFile.readLine()) != null)
            {
                    wordList.add(word);
            }
            readFile.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return wordList;
    }

    @Override
    public ArrayList<String> getWordArray(String filePath){
        return readFile(filePath);
    }
}
