package com.xjjlearning.learningalgorithm;

import java.util.ArrayDeque;
import java.util.Deque;

public class Str {
    public static void main(String[] args) {
//        String s = "1-12/4 +3/3 *6";
//        String s= "3*(4-5/2)-6";
//        String s = " 2-1+2";
        String s = "-1-(-2)";
//        String s = "(1+(4+5+2)-3)+(6+8)";
        int calculate = Str.calculate(s);
        System.out.println(calculate);
    }

    public static int calculate(String s) {
        s = s.replaceAll(" ", "");
        int n = s.length();
        Deque<Character> chars = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            chars.offer(c);
        }
        return calculate(chars);
    }

    private static int calculate(Deque<Character> chars) {
        // "1-12+3" -> "+1-12+3"
        // "2-3*4+5" -> "+2-3*4+5"
        // "3 * (4 - 5/2) - 6"
        // "(1+(4+5+2)-3)+(6+8)"
        Deque<Integer> stack = new ArrayDeque<>();
        char sign = '+';
        int num = 0;
        int pre;
        while (!chars.isEmpty()) {
            char c = chars.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                num = calculate(chars);
            }
            if (!Character.isDigit(c) || chars.isEmpty()) {
                switch (sign) {
                    case '+' :
                        stack.push(num);
                        break;
                    case '-' :
                        stack.push(-num);
                        break;
                    case '*':
                        pre = stack.pop();
                        pre *= num;
                        stack.push(pre);
                        break;
                    case '/':
                        pre = stack.pop();
                        pre /= num;
                        stack.push(pre);
                        break;
                }
                sign = c;
                num = 0;
            }
            if (c == ')') break;
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    private int StrToInt(String s) {
        // "123" -> 123
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            n = n * 10 + (c - '0');
        }
        return n;
    }
}
