package com.javarush.task.task34.task3404;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {

    public static void main(String[] args) {

        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); System.out.println("0.5 6 - expected output");
        solution.recurse("tan(45)", 0);  System.out.println("1 1 - expected output");
        solution.recurse("tan(-45)", 0);  System.out.println("-1 2 - expected output");
        solution.recurse("0.305", 0);  System.out.println("0.3 0 - expected output");
        solution.recurse("0.3051", 0);  System.out.println("0.31 - expected output");
        solution.recurse("(0.3051)", 0);  System.out.println("0.31 - expected output");
        solution.recurse("1+(1+(1+1)*(1+1))*(1+1)+1", 0);  System.out.println("12 8 - expected output");
          solution.recurse("tan(44+sin(89-cos(180)^2))", 0); System.out.println("1 6 - expected output");
        solution.recurse("-2+(-2+(-2)-2*(2+2))", 0);  System.out.println("-14 8 - expected output");
        solution.recurse("sin(80+(2+(1+1))*(1+1)+2)", 0);  System.out.println("1 7 - expected output");
        solution.recurse("1+4/2/2+2^2+2*2-2^(2-1+1)", 0);  System.out.println("6 11 - expected output");
        solution.recurse("10-2^(2-1+1)", 0);  System.out.println("6 4 - expected output");
        solution.recurse("2^10+2^(5+5)", 0);  System.out.println("2048 4 - expected output");
        solution.recurse("1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1", 0);  System.out.println("72.96 8 - expected output");
        solution.recurse("0.000025+0.000012", 0);  System.out.println("0 1 - expected output");
        solution.recurse("-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)", 0);  System.out.println("-3 16 - expected output");
        solution.recurse("cos(3 + 19*3)", 0);  System.out.println("0.5 3 - expected output");

        solution.recurse("2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)", 0);  System.out.println("8302231.36 14 - expected output");
        solution.recurse("(-1 + (-2))", 0);  System.out.println("-3 3 - expected output");
        solution.recurse("-sin(2*(-5+1.5*4)+28)", 0);  System.out.println("-0.5 7 - expected output");
        solution.recurse("sin(100)-sin(100)", 0);  System.out.println("0 3 - expected output");
        solution.recurse("-(-22+22*2)", 0);  System.out.println("-22 4 - expected output");
        solution.recurse("-2^(-2)", 0);  System.out.println("-0.25 3 - expected output");
        solution.recurse("-(-2^(-2))+2+(-(-2^(-2)))", 0);  System.out.println("2.5 10 - expected output");
        solution.recurse("(-2)*(-2)", 0);  System.out.println("4 3 - expected output");
        solution.recurse("(-2)/(-2)", 0);  System.out.println("1 3 - expected output");
        solution.recurse("sin(-30)", 0);  System.out.println("-0.5 2 - expected output");
        solution.recurse("cos(-30)", 0);  System.out.println("0.87 2 - expected output");
        solution.recurse("tan(-30)", 0);  System.out.println("-0.58 2 - expected output");
        solution.recurse("2+8*(9/4-1.5)^(1+1)", 0);  System.out.println("6.5 6 - expected output");
        solution.recurse("0.005", 0);  System.out.println("0.01 0 - expected output");
        solution.recurse("0.0049", 0);  System.out.println("0 0 - expected output");
        solution.recurse("0+0.304", 0);  System.out.println("0.3 1 - expected output");
        solution.recurse("sin(45) - cos(45)", 0);  System.out.println("0 3 - expected output");
        solution.recurse("0/(-3)", 0);  System.out.println("0 2 - expected output");

    }


    public void recurse(final String expression, int countOperation) {
        //implement
        String workExp = expression.replaceAll(" ", "");
        if (countOperation == 0) {
            String[] signs = {"sin", "cos", "tan", "\\^", "\\/", "\\*", "\\-", "\\+"};
            for (String s : signs) {
                countOperation += workExp.split(s).length-1;
            }
        }

        if (!workExp.contains("(") && !workExp.contains(")")) {
            workExp = calculate(workExp);
            String result = (new DecimalFormat("#.##").format(Double.valueOf(workExp))).replace(',','.');
            if (result.equals("-0")) {
                result = "0";
            }
            System.out.println(result + " " + countOperation);
            return;
        }
        else {
            ArrayList<Integer> openedIndex = new ArrayList<Integer>();
            for (int i = 0; i < workExp.length(); i++) {
                char c = workExp.charAt(i);
                if (c == '(') {
                    openedIndex.add(i);
                }
                if (c == ')') {
                    int startIndex = openedIndex.remove(openedIndex.size() - 1) + 1;
                    String firstExp = workExp.substring(startIndex, i);
                    String calculateResult = calculate(firstExp);
                    if (startIndex > 3 && workExp.substring(startIndex - 4, startIndex - 1).equals("sin")) {
                        workExp = workExp.replace(workExp.substring(startIndex - 4, startIndex + firstExp.length() + 1), String.valueOf(Math.sin(Double.valueOf(calculateResult) * Math.PI / 180)));
                    } else if (startIndex > 3 && workExp.substring(startIndex - 4, startIndex - 1).equals("cos")) {
                        workExp = workExp.replace(workExp.substring(startIndex - 4, startIndex + firstExp.length() + 1), String.valueOf(Math.cos(Double.valueOf(calculateResult) * Math.PI / 180)));
                    } else if (startIndex > 3 && workExp.substring(startIndex - 4, startIndex - 1).equals("tan")) {
                        workExp = workExp.replace(workExp.substring(startIndex - 4, startIndex + firstExp.length() + 1), String.valueOf(Math.tan(Double.valueOf(calculateResult) * Math.PI / 180)));
                    } else {
                        workExp = workExp.replace("(" + firstExp + ")", calculateResult);
                    }
                    workExp = workExp.replace("--", "+");
                    workExp = workExp.replace("+-", "-");
                    workExp = workExp.replace("++", "+");
                    recurse(workExp, countOperation);
                    return;
                }
            }
        }
    }

    public String calculate(String expression) {
        char[] signs = {'^', '/', '*', '-', '+'};
        for (char c : signs) {
            String pattern = String.format("-?\\d+\\.?\\d*\\%s-?\\d+\\.?\\d*", c);
            //System.out.println(pattern);
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(expression);
            while (m.find()) {
                String exp = m.group();
                double num1, num2;
                if (exp.indexOf(c)!=0) {
                    if (exp.charAt(0)=='-' && c == '^') {
                        exp = exp.substring(1);
                    }
                    num1 = Double.valueOf(exp.substring(0, exp.indexOf(c)));
                    num2 = Double.valueOf(exp.substring(exp.indexOf(c) + 1));
                }
                else {
                    num1 = Double.valueOf(exp.substring(0, exp.indexOf(c,1)));
                    num2 = Double.valueOf(exp.substring(exp.indexOf(c,1) + 1));
                }
                switch (c) {
                    case '^':
                        num1=Math.pow(num1, num2);
                        break;
                    case '/':
                        num1/=num2;
                        break;
                    case '*':
                        num1*=num2;
                        break;
                    case '-':
                        num1-=num2;
                        break;
                    case '+':
                        num1+=num2;
                        break;
                }
                expression = expression.replace(exp, String.valueOf(num1));
                if (!isNumeric(expression)) {
                    expression = calculate(expression);
                }
                double d = Double.valueOf(expression);
                if (d == (int)d) {
                    expression = String.valueOf((int)d);
                }
            }
        }

        return expression;
    }

    public boolean isNumeric(String s) {
        try {
            double n = Double.valueOf(s);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public Solution() {
        //don't delete
    }
}

