package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(bufferedReader.readLine()));
//        BufferedReader reader = new BufferedReader(new FileReader("/Users/Max/Desktop/Java/JavaRush/test.rtf"));
        bufferedReader.close();

        StringBuilder s = new StringBuilder();
        while (reader.ready()) {
            s.append(reader.readLine()).append(" ");
        }
        reader.close();
        System.out.println(s.toString());

        String[] strings = s.toString().trim().split(" +");
        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(strings));

        for (int i =0; i<stringArrayList.size()-1; i++) {
            for (int j=i+1; j<stringArrayList.size();j++) {
                s = new StringBuilder(stringArrayList.get(j));
                if (stringArrayList.get(i).equals(s.reverse().toString())) {
                    Pair p = new Pair(stringArrayList.get(i), stringArrayList.get(j));
                    result.add(p);
                    stringArrayList.remove(i);
                    stringArrayList.remove(j-1);
                    i--;
                    break;
                }

            }
        }
        for (Pair pair : result) {
            System.out.println(pair);
        }

    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

}
