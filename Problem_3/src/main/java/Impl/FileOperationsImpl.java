package Impl;

import Interfaces.FileOperations;

import java.io.*;
import java.util.ArrayList;

public class FileOperationsImpl implements FileOperations {

    private ArrayList<String> readFile(String filePath){
        File file = new File(filePath);
        ArrayList<String> wordList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader readFile = new BufferedReader(fileReader);
            while (!(readFile.readLine() == null)){
                wordList.add(readFile.readLine());
            }

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

//    public static void main(String[] args) {
//        FileOperations fops = new FileOperationsImpl();
//        System.out.println(fops.getWordArray("negative-words.txt"));
//    }
}
