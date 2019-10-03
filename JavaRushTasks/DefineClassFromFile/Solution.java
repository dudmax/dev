package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.URLDecoder;
import java.util.*;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        Set<Object> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    //this method get a new instance of a new class in some folder (in realtime)
    public static Set<Object> getAllAnimals(String pathToAnimals) throws UnsupportedEncodingException {
        HashSet<Object> hashSet = new HashSet<>();
        //Encode path to file to UTF-8, if you have space in name and etc.
        File folder = new File(URLDecoder.decode(pathToAnimals, "UTF-8"));
        //take all files from folder
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                //we can't define a new class from file with simple ClassLoader, so we extends from him
                RealtimeClassLoader realtimeClassLoader = new RealtimeClassLoader();
                Class newClass = realtimeClassLoader.findClass(file.getAbsolutePath());
                //check if this class extends from Animal
                if (Animal.class.isAssignableFrom(newClass)) {
                    //this try because we new class can have only private constructor
                    try {
                        Constructor constructor = newClass.getConstructor();
                        Object newObject = constructor.newInstance();
                        hashSet.add(newObject);
                    } catch (Exception e) {
                    }
                }
            }
        }
        return hashSet;
    }

}
