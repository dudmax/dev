package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/*
Проход по дереву файлов
*/
public class Solution {

    public static void checkFiles(String dirPath, TreeMap<String, File> treeMap) throws IOException {
        File dir = new File(dirPath);
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                if (file.length()<=50) {
                    treeMap.put(file.getName(), file);
                }
            }
            else {
                checkFiles(file.getCanonicalPath(), treeMap);
            }
        }
    }

//    public static void writeOutput(File outFile, TreeMap<String, File> treeMap) throws IOException {
//    }

    public static void main(String[] args) throws IOException {
        String dirPath = args[0];
        String outPath = args[1];

        TreeMap<String, File> treeMap = new TreeMap<>();
        checkFiles(dirPath, treeMap);

        File oldFile = new File(outPath);
        File outFile = new File(oldFile.getParent()+"/allFilesContent.txt");
        FileUtils.renameFile(oldFile, outFile);

        String output="";
        for (Map.Entry<String, File> entry : treeMap.entrySet()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(entry.getValue()));
            while (bufferedReader.ready()) {
                output+=bufferedReader.readLine();
            }
            bufferedReader.close();
            output+="\n";
        }
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);;
        fileOutputStream.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile));
        bufferedWriter.write(output);
        bufferedWriter.close();
    }
}
