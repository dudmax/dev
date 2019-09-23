package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        String[] a = getTokens("level22.lesson13.task01", ".");
        for (String s : a) {
            //System.out.println(s);
        }
    }

    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);
        ArrayList<String> arrayList = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return arrayList.toArray(new String[0]);
    }
}
