package com.xjjlearning.learningalgorithm.str;

public class Regex {
    public static void main(String[] args) {
        isMatch("aa", "a");
        isMatch("ab", ".*");
        isMatch("aa", "a*");
    }

    static boolean[][] memo;

    // leetcode 10 正则表达式匹配
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        memo = new boolean[m][n];
        return dp(s, p, 0, 0);
    }

    private static boolean dp(String s, String p, int i, int j) {
        int m = s.length(), n = p.length();
        if (j == n)
            return i == m;
        if (i == m) {
            if (((n - j) & 1) == 1) return false;
            for (; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') return false;
            }
            return true;
        }
        if (memo[i][j] != false) return true;
        boolean res = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                // 有* 可以匹配0
                // 次或者多次
                res = dp(s, p, i, j + 2) || dp(s, p, i + 1, j);
            } else {
                res = dp(s, p, i + 1, j + 1);
            }
        } else {
            // 不匹配
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                // 有* 匹配0次
                res = dp(s, p, i, j + 2);
            } else {
                res = false;
            }
        }
        memo[i][j] = res;
        return res;
    }



    // 纯dp版 不理解
    public static boolean isMatch(String s, String p, boolean dp) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
