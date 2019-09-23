package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        int pos = Integer.valueOf(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.seek(pos);
        byte[] b = new byte[text.length()];
        raf.read(b, 0 , text.length());
        raf.seek(raf.length());
        String s = new String(b);
        if (text.equals(s)) {
            raf.write("true".getBytes());
        }
        else {
            raf.write("false".getBytes());
        }
        raf.close();
    }
}
