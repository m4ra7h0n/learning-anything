package com.xjjlearning.learningalgorithm;

public class TireTest {
    // leetcode 820
    public static void main(String[] args) {
        minimumLengthEncoding(new String[]{"time", "me", "bell"});
    }
    static class Tire {
        Tire[] tree = new Tire[26];
        boolean flag;
    }
    static int res = 0;
    public static int minimumLengthEncoding(String[] words) {
        int n = words.length;
        Tire root = new Tire();
        for (int i = 0; i < n; i++) {
            Tire tree = root;
            for (int j = words[i].length() - 1; j >= 0; j--) {
                if (tree.tree[words[i].charAt(j) - 'a'] == null) {
                    tree.tree[words[i].charAt(j) - 'a'] = new Tire();
                    tree = tree.tree[words[i].charAt(j) - 'a'];
                }
            }
            tree.flag = true;
        }
        dfs(root, 0);
        return res;
    }
    private static void dfs(Tire root, int depth) {
        //没有子节点并且标记为true的root
        boolean flag = true;
        for (int i = 0; i < 26; i++) {
            if (root.tree[i] != null) {
                flag = false;
                dfs(root.tree[i], depth + 1);
            }
        }
        if (flag == true && root.flag == true) {
            res += depth + 1;
        }
    }
}
