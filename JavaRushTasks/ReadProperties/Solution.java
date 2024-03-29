package com.javarush.task.task31.task3109;

import java.io.*;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            //fileReader.close();
            Properties prop = new Properties();
            if (fileName.substring(fileName.lastIndexOf(".")+1).equalsIgnoreCase("xml")) {
                prop.loadFromXML(fileInputStream);
            }
            else {
                prop.load(fileInputStream);
            }
            fileInputStream.close();
            return prop;
        }
        catch (Exception e) {
            return new Properties();
        }
    }
}
