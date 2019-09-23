package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        LinkedList<File> linkedList = new LinkedList<>();
        ArrayList<String> files = new ArrayList<>();
        linkedList.add(new File(root));
        int num = 0;
        while (linkedList.size() > num) {
            File folder = linkedList.get(num);
            for (File file : folder.listFiles()) {
                if (!file.isDirectory()) {
                    //System.out.println(file);
                    files.add(file.getAbsolutePath());
                } else {
                    linkedList.addLast(file);
                }
            }
            num++;
        }
        return files;
    }


    public static void main(String[] args) throws IOException {

        List<String> list = getFileTree("/Users/Max/Desktop/Java/JavaRush/test");

        for (String s : list) {
            System.out.println(s);
        }
    }
}
