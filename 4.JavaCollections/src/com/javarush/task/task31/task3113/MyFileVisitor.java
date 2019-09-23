package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {

    private long size;
    private int filesCount;
    private int dirCount;

    public long getSize() {
        return size;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public int getDirCount() {
        return dirCount;
    }


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        dirCount++;
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        filesCount++;
        size+= Files.size(file);
        return super.visitFile(file, attrs);
    }
}
