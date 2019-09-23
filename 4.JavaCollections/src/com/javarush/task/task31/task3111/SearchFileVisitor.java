package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;

    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String content = new String(Files.readAllBytes(file)); // размер файла: content.length

        boolean containsPartOfName = false;
        boolean containsPartOfContent = false;
        boolean isGoodSize = false;

        if (partOfName != null && partOfContent != null && maxSize != 0) {
            if (file.getFileName().toString().contains(partOfName)) {
                containsPartOfName = true;
            }
            if (content.contains(partOfContent)) {
                containsPartOfContent = true;
            }
            if (content.length() > minSize && content.length() < maxSize) {
                isGoodSize = true;
            }
            if (containsPartOfName && containsPartOfContent && isGoodSize) {
                foundFiles.add(file);
            }
            return super.visitFile(file, attrs);
        }
        if (partOfName != null) {
            if (file.getFileName().toString().contains(partOfName)) {
                foundFiles.add(file);
            }
            return super.visitFile(file, attrs);
        }
        if (partOfContent != null) {
            if (content.contains(partOfContent)) {
                foundFiles.add(file);
            }
            return super.visitFile(file, attrs);
        }
        if (maxSize != 0) {
            if (content.length() > minSize && content.length() < maxSize) {
                foundFiles.add(file);
            }
            return super.visitFile(file, attrs);
        }
        if (minSize != 0) {
            if (content.length() > minSize) {
                foundFiles.add(file);
            }
            return super.visitFile(file, attrs);
        }
        return super.visitFile(file, attrs);
    }
}
