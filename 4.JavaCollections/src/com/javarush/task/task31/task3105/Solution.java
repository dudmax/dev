package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
//        String filePath = "/Users/Max/Desktop/Java/JavaRush/test/a1.rtf";
//        String zipPath = "/Users/Max/Desktop/Java/JavaRush/test/2.zip";
        String filePath = args[0];
        String zipPath = args[1];
        String newFilePath = "new/" + filePath.substring(filePath.lastIndexOf("/")+1);

        Map<ZipEntry, ByteArrayOutputStream> map = new HashMap<>();

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath));
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            if (!zipEntry.toString().equals(newFilePath)) {

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipInputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, count);
                }
                byteArrayOutputStream.close();

                map.put(zipEntry, byteArrayOutputStream);
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath));
        for (Map.Entry<ZipEntry, ByteArrayOutputStream> entry : map.entrySet()) {
            zipOutputStream.putNextEntry(new ZipEntry(entry.getKey().getName()));
            zipOutputStream.write(entry.getValue().toByteArray());
        }
        zipEntry = new ZipEntry(newFilePath);
        zipOutputStream.putNextEntry(zipEntry);
        Files.copy(Paths.get(filePath), zipOutputStream);
        zipOutputStream.close();
    }
}
