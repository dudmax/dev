package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
        System.out.println(getPartOfString("Амиго и Диего лучшие!"));
    }

    public static String getPartOfString(String string) {
        if (string == null) {
            throw new TooShortStringException();
        }

        int start = string.indexOf(' ');
        int temp = start;
        int count = 1;
        while (count != 4) {
            if (temp == -1) {
                throw new TooShortStringException();
            }
            temp = string.indexOf(' ', temp+1);
            count++;
        }
        if (temp == -1) {
            throw new TooShortStringException();
        }
        if ((temp = string.indexOf(' ', temp+1)) != -1) {
            return string.substring(start+1,temp);
        }
        else {
            return string.substring(start+1);
        }
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
