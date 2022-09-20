package com.company;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        String[] fileNames = {"input1.txt", "input2.txt"};  // files to be read

        List<String> allStrings = readFromFile(fileNames);
        processStrings(allStrings);
    }
    public static List<String> readFromFile(String[] fileNames){
        List<String> allStrings = new ArrayList<String>();
        FileInputStream fileStream = null;
        Scanner scanner = null;

        for(String fileName : fileNames){
            try {
                fileStream = new FileInputStream(fileName);
                scanner = new Scanner(fileStream, "UTF-8");
                while (scanner.hasNextLine()) {
                    allStrings.add(scanner.nextLine());
                }
                fileStream.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        }

        return allStrings;
    }
    
    public static void processStrings(List<String> allStrings){

        for (String str : allStrings) { // checking every string
            List<Integer> allNumbers = new ArrayList<Integer>();
            String digitSequence = "";
            boolean lastCharWasDigit = false;

            char[] chars = str.toCharArray();
            for (int i = 0;i<chars.length;i++){ // checking every character
                char ch = chars[i];
                if((int)ch >= 48 && (int)ch <=57){ // character is a number
                    digitSequence += ch;
                    if(!lastCharWasDigit){
                        lastCharWasDigit=true;
                    }
                }
                if(((int)ch < 48 || (int)ch >57) || i == chars.length-1){ // character is not a number or it's the end of the line
                    if(lastCharWasDigit){
                        int number = Integer.parseInt(digitSequence);
                        allNumbers.add(number);
                        digitSequence="";
                        lastCharWasDigit = false;
                    }
                }
            }
            
            for(int number : allNumbers){   // checking every number found
                if(number >= 10){
                    System.out.println(str);
                    break;
                }
            }
        }
    }
}
