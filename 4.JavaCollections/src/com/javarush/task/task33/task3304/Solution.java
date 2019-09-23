package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/* 
Конвертация из одного класса в другой используя JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {
//        Second s = (Second) convertOneToAnother(new First(), Second.class);
//        First f = (First) convertOneToAnother(new Second(), First.class);
//        System.out.println("second " + s.i);
//        System.out.println("first " +  f.i);
        First first = new First();
        first.list.add("qwe");
        System.out.println(first.list);
        Second s = (Second) convertOneToAnother(first, Second.class);
        System.out.println(s.list);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, one);
        System.out.println(writer.toString());
        String json = writer.toString().replaceFirst(one.getClass().getSimpleName().toLowerCase(), resultClassObject.getSimpleName().toLowerCase());
        return mapper.readValue(json,resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=First.class,  name="first"))
    public static class First {
        public int i = 1;
        public String name;
        public List list = new ArrayList();
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=Second.class, name="second"))
    public static class Second {
        public int i = 2;
        public String name;
        public List list = new ArrayList<>();
    }
}
