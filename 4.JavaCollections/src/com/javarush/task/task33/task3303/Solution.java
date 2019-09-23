package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, clazz);
    }

    @JsonAutoDetect
    public static class Cat {
        @JsonProperty("wildAnimal")
        public String name;
        @JsonIgnore
        public int age;
        @JsonProperty("over")
        public int weight;

        Cat() {
        }
    }

    public static void main(String[] args) throws IOException {
        String fileName = "/Users/Max/Desktop/Java/JavaRush/test.rtf";
        Cat cat = null;
        cat = convertFromJsonToNormal(fileName, Cat.class);
        System.out.println(cat.name + " мяу, я вешу " + cat.weight);
    }
}
