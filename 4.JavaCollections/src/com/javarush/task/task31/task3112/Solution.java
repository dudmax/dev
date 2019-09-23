package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

/*
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("/Users/Max/Desktop/Java/JavaRush"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();
        Path tempFile = Files.createTempFile("temp-",".txt");
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();
        Path file = Paths.get(downloadDirectory.toAbsolutePath().toString()+urlString.substring(urlString.lastIndexOf("/")));
        return Files.move(tempFile, file, StandardCopyOption.REPLACE_EXISTING);
    }
}
