package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution sol1 = new Solution();
        sol1.innerClasses[0] = new Solution().new InnerClass();
        sol1.innerClasses[1] = new Solution().new InnerClass();
        Solution sol2 = new Solution();
        sol2.innerClasses[0] = new Solution().new InnerClass();
        sol2.innerClasses[1] = new Solution().new InnerClass();

        Solution[] solutions = {sol1, sol2};
        return solutions;
    }

    public static void main(String[] args) {

    }
}
