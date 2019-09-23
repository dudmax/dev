package com.javarush.task.task21.task2102;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* 
Сравниваем модификаторы
*/
public class Solution {
    public static void main(String[] args) {
        int classModifiers = Solution.class.getModifiers();
        System.out.println(isModifierSet(classModifiers, Modifier.PUBLIC));   //true
        System.out.println(isModifierSet(classModifiers, Modifier.STATIC));   //false

        int methodModifiers = getMainMethod().getModifiers();
        System.out.println(isModifierSet(methodModifiers, Modifier.STATIC));      //true
    }

    public static boolean isModifierSet(int allModifiers, int specificModifier) {
        switch (Modifier.toString(specificModifier)) {
            case "abstract":
                return Modifier.isAbstract(allModifiers);
            case "final":
                return Modifier.isFinal(allModifiers);
            case "interface":
                return Modifier.isInterface(allModifiers);
            case "native":
                return Modifier.isNative(allModifiers);
            case "private":
                return Modifier.isPrivate(allModifiers);
            case "protected":
                return Modifier.isProtected(allModifiers);
            case "public":
                return Modifier.isPublic(allModifiers);
            case "static":
                return Modifier.isStatic(allModifiers);
            case "strict":
                return Modifier.isStrict(allModifiers);
            case "synchronized":
                return Modifier.isSynchronized(allModifiers);
            case "transient":
                return Modifier.isTransient(allModifiers);
            case "volatile":
                return Modifier.isVolatile(allModifiers);
        }
        return false;
    }

    private static Method getMainMethod() {
        Method[] methods = Solution.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase("main")) return method;
        }

        return null;
    }
}
