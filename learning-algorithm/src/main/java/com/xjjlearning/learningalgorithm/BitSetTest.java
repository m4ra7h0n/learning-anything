package com.xjjlearning.learningalgorithm;

import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        System.out.println((char)(1 + '0'));
//        learnBitSet();
    }

    /**
     *      practice
     */
    public static void learnBitSet() {
        BitSet bitSet = new BitSet(9);
        bitSet.set(1);
        bitSet.set(3);
//        System.out.println(bitSet.toString());
//        bitSet.clear();
//        System.out.println(bitSet.toString());
//        bitSet.flip(3);
//        System.out.println(bitSet.toString());
//        System.out.println(bitSet.length());
        int i = 0;
        while (i++ < bitSet.length()) {
        }
        System.out.println(bitSet.cardinality());
    }

    /**
     * leetcode 37
     */
    static BitSet[] col;
    static BitSet[] row;
    static BitSet[][] grid;
    static int count = 0;
    static char[][] res;
    public static char[][] solveSudoku(char[][] board) {
        int n = board.length;
        res = new char[n][n];
        col = new BitSet[n];
        row = new BitSet[n];
        grid = new BitSet[n / 3][n / 3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    count++;
                    continue;
                }
                col[i].set(board[i][j] - '1');
                row[j].set(board[i][j] - '1');
                grid[i / 3][j / 3].set(board[i][j] - '1');
            }
        }
        solveSudokuDfs(board);
        return res;
    }
    private static void solveSudokuDfs(char[][] board) {
        if (count == 0) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    res[i][j] = board[i][j];
                }
            }
            return;
        }
        //找到能填的最少的位置
        int[] next = getNext(board);
        // 找到改点可以填的数字(BitSet)
        BitSet allCanFills = getAllCanFills(next[0], next[1]);
        for (int k = 0; k < 9; k++) {
            if (!allCanFills.get(k)) {
                board[next[0]][next[1]] = (char)(k + '0');
                col[next[0]].set(k);
                row[next[1]].set(k);
                grid[next[0]][next[1]].set(k);
                count--;

                solveSudokuDfs(board);

                board[next[0]][next[1]] = '.';
                col[next[0]].clear(k);
                row[next[1]].clear(k);
                grid[next[0]][next[1]].clear(k);
                count++;
            }
        }
    }
    private static BitSet getAllCanFills(int i, int j) {
        BitSet bitSet = new BitSet(9);
        bitSet.or(col[i]);
        bitSet.or(row[j]);
        bitSet.or(grid[i][j]);
        return bitSet;
    }
    private static int[] getNext(char[][] board) {
        int count = 9;
        int resI = 0, resJ = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (9 - getAllCanFills(i, j).cardinality() < count) {
                    resI = i;
                    resJ = j;
                    count = 9 - getAllCanFills(i, j).cardinality();
                }
            }
        }
        return new int[]{resI, resJ};
    }
}
