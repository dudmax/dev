package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader readerFile = new BufferedReader(new FileReader(reader.readLine()));
        //BufferedReader readerFile = new BufferedReader(new FileReader("/Users/Max/Desktop/Java/JavaRush/test.rtf"));
        reader.close();
        String data = readerFile.readLine();
        readerFile.close();
        StringBuilder result = getLine(data.trim().replaceAll("\\s+", " ").split(" "));
        //StringBuilder result = getLine("Киев", "Нью-Йорк", "Амстердам", "Вена", "Мельбурн");
        //StringBuilder result = getLine("Киев", "Амстердам", "Вена");
        if (result !=null)
            System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0) {
            return new StringBuilder();
        }
        ArrayList<StringBuilder> list = new ArrayList<>();
        for (String s : words) {
            String[] newWords = deleteString(words, s);
            pos(newWords, s, list);
        }
        list.sort(Comparator.comparing(StringBuilder::length));
        return list.get(list.size()-1);
    }

    public static void pos(String[] words, String first, ArrayList<StringBuilder> list) {
        char endFirst = first.toLowerCase().charAt(first.length()-1);
        for (String s : words) {
            StringBuilder result = new StringBuilder(first);
            if (s.toLowerCase().charAt(0) == endFirst) {
                result.append(" ");
                result.append(s);
                String[] newWords = deleteString(words, s);
                if (newWords.length == 0) {
                    //System.out.println(result.toString());
                    list.add(result);
                    return;
                }
                pos(newWords, result.toString(), list);
            }
        }
        list.add(new StringBuilder(first));
        //System.out.println(first.toString());
    }

    public static String[] deleteString(String[] words, String first) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(words));
        arrayList.remove(first);
        return arrayList.toArray(new String[0]);
    }
}
