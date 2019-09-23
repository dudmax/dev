package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        String resultFileName = args[0];
        List<String> list = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            list.add(args[i]);
        }

        Collections.sort(list);
        Vector<FileInputStream> files = new Vector<>();

        for (String s : list) {
            files.addElement(new FileInputStream(s));
        }

        FileOutputStream output = new FileOutputStream(resultFileName);
        ZipInputStream stream = new ZipInputStream(new SequenceInputStream(files.elements()));

        byte[] buf = new byte[1024];
        while ((stream.getNextEntry()) != null) {
            int b;
            while((b = stream.read(buf)) != -1) {
                output.write(buf, 0 , b);

            }
        }
        stream.close();
        output.close();
    }
}
