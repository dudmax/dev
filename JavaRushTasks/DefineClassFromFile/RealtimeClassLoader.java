package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.FileInputStream;

//load class in realtime from file.class
public class RealtimeClassLoader extends ClassLoader {
    public Class<?> findClass(String name) {
        Class<?> result = null;
        File file = new File(name);
        try {
            //read all bytes from file and after this define a new class with "magic" method defineClass
            byte[] bytes = loadFileAsBytes(file);
            result = defineClass(null, bytes, 0, bytes.length);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
        }
        return result;
    }

    //this method just read all bytes from file in array and return this array
    public byte[] loadFileAsBytes (File file) {
        byte[] bytes = new byte[(int)file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(bytes, 0, bytes.length);
            return bytes;
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return bytes;
        }
    }
}
