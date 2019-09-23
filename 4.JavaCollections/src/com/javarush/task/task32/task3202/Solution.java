package com.javarush.task.task32.task3202;

import java.io.*;
import java.nio.BufferOverflowException;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("JavaRush/test.rtf"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
//        if (is == null) {
//            return new StringWriter();
//        }
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter pw = new PrintWriter(stringWriter);
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
//        String temp = null;
//        while ( (temp = bufferedReader.readLine()) != null) {
//            pw.println(temp);
//        }
//        bufferedReader.close();
//        pw.close();
//        return stringWriter;

        if (is == null) {
            return new StringWriter();
        }
        StringWriter stringWriter = new StringWriter();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String temp = null;
        while ( (temp = bufferedReader.readLine()) != null) {
            stringWriter.write(temp);
        }
        bufferedReader.close();
        return stringWriter;
    }
}