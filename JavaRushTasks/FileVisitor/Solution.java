package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String folder = reader.readLine();
        reader.close();
        //String folder = "/Users/Max/Desktop/Java/JavaRush/test";

        Path file = Paths.get(folder);
        if (Files.isRegularFile(file)) {
            System.out.println(file.toString() + " - не папка");
            return;
        }
        MyFileVisitor myFileVisitor = new MyFileVisitor();
        Files.walkFileTree(file, myFileVisitor);
        System.out.println("Всего папок - " + (myFileVisitor.getDirCount()-1));
        System.out.println("Всего файлов - " + myFileVisitor.getFilesCount());
        System.out.println("Общий размер - " + myFileVisitor.getSize());

    }
}
