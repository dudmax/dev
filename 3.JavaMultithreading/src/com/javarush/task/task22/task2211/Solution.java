package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String winFile = args[0];
        String utfFile = args[1];

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), Charset.forName("Windows-1251")));
        String data = "";
        while (bufferedReader.ready()) {
            data+=bufferedReader.readLine();
        }
        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
        bufferedWriter.write(data);
        bufferedWriter.close();
    }
}
