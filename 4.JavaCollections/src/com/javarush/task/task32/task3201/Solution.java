package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(fileName, "w");
        long pos = Math.min(Long.valueOf(args[1]), raf.length());
        raf.seek(pos);
        raf.write(text.getBytes());
        raf.close();
    }
}
