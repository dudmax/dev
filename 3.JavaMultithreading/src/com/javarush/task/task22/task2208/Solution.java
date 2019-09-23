package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        HashMap<String, String> params = new HashMap<>();
        params.put("name","Ivanov");
        params.put("country","Ukraine");
        params.put("city","Kiev");
        params.put("age",null);
        System.out.println("SELECT * WHERE " + getQuery(params));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                if (!result.toString().equals("")) {
                    result.append(" and ");
                }
                result.append(entry.getKey()).append(" = \'").append(entry.getValue()).append("\'");
            }
        }
        return result.toString();
    }
}
