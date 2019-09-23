package com.javarush.task.task32.task3204;

import java.io.*;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {

        String pass = "";
        int length = 8;
        Random random = new Random();
        boolean numb = false;
        boolean low = false;
        boolean upper = false;
        while (!(numb && low && upper)) {
            pass = "";
            for (int i=0 ; i<length; i++) {
                char next = 0;
                int range = 10;
                switch (random.nextInt(3)) {
                    case 0:
                        next = 'a';
                        range = 26;
                        low = true;
                        break;
                    case 1:
                        next = 'A';
                        range = 26;
                        upper = true;
                        break;
                    case 2:
                        next = '0';
                        range = 10;
                        numb = true;
                        break;
                }
                pass += (char) (next + random.nextInt(range));
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));
        bufferedWriter.write(pass);

        bufferedWriter.close();
        return byteArrayOutputStream;

    }
}