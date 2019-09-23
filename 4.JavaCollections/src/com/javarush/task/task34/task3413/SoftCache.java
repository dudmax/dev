package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);

        //напишите тут ваш код
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }

    public AnyObject put(Long key, AnyObject value) {
        SoftReference<AnyObject> tempReference = cacheMap.get(key);
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        if (tempReference == null) {
            return null;
        }
        AnyObject any = tempReference.get();
        tempReference.clear();
        return any;
        //напишите тут ваш код
    }

    public AnyObject remove(Long key) {
        //напишите тут ваш код
        SoftReference<AnyObject> tempReference = cacheMap.get(key);
        SoftReference<AnyObject> softReference = cacheMap.remove(key);
        if (tempReference == null) {
            return null;
        }
        AnyObject any = tempReference.get();
        tempReference.clear();
        return any;
    }
}