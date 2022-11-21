package com.xjjlearning.learningalgorithm;

import java.util.Arrays;

public class Np {
    public static void main(String[] args) {
        int n = 12;
        int[][] relations = new int[][]{{1, 2}, {1, 3}, {7, 5}, {7, 6}, {4, 8}, {8, 9}, {9, 10}, {10, 11}, {11, 12}} ;
        int k = 2;
        int i = minNumberOfSemestersResolve1(n, relations, k);
        System.out.println(i);
    }

    // leetcode 1494
    /**
     * 给你一个整数n表示某所大学里课程的数目，编号为1到n，数组relations中，relations[i] = [xi, yi] 表示一个先修课的关系，也就是课程xi必须在课程yi之前上。同时你还有一个整数k。
     * 在一个学期中，你 最多可以同时上 k门课，前提是这些课的先修课在之前的学期里已经上过了。
     * 请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/parallel-courses-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @param relations
     * @param k
     * @return
     */
    /**
     * np-complete, 暴力 + 状态压缩 + dp
     */
    public static int minNumberOfSemestersResolve1(int n, int[][] relations, int k) {
        // 1. 初始化prerequiste
        // 2. 统计i中的1
        // 3. 遍历状态
        // 4. 遍历课程。找出有效课程
        // 5. 遍历有效课程的子集

        int[] prerequiste = new int[n];
        for(int[] rel : relations){
            // xi 必须在课程 yi 之前上
            int pre = rel[0] - 1;
            int after = rel[1] - 1;
            // 举例：prerequisite[1] = 0110 表示1的先修课为2和3
            prerequiste[after] |= 1 << pre;
        }

        int total = 1 << n;
        int[] cnt = new int[total];  // 缓存

        // 小技巧，计算每个数字的二进制数中，1的个数
        for(int i = 0; i < total; i++){
            cnt[i] = cnt[i >> 1] + (i & 1);
            // cnt[0] = 0
            // cnt[1] = 0 + 1 = 1
            // cnt[2] = 1 + 0 = 1
            // cnt[3] = 1 + 1 = 2
            // cnt[5] = cnt[2] + 1 = 2
            // cnt[7] = cnt[3] + 1 = 3
        }

        int[] dp = new int[total]; // 上完所有课最小学期数
        Arrays.fill(dp, 16); // 初始化最大值 + 1
        // 0为不需要上任何课的状态，因此不需要学期
        dp[0] = 0;

        for(int state = 0; state < total; state++){ //state表示已经上过了的课
            if(dp[state] > n) continue;
            int next = 0; // 上过state, 接下来还可以上next
            for (int j = 0; j < n; j++) {
                // 在上过state的基础上，还有哪些课可以上，要满足两个条件
                // CASE1: ((state & (1 << j)) == 0) 表示这个课在state中没上过
                // CASE2: ((prerequisite[j] & state) == prerequisite[j]) 表示这个课的先修课已经上完了
                if (((state & (1 << j)) == 0) && (prerequiste[j] & state) == prerequiste[j]) {
                    next |= (1 << j);
                }
            }

            // 枚举next的所有子集，比如next = 111，它的子集合就是{111, 110, 101 011, 100, 010, 001}
            for (int subMask = next; subMask != 0; subMask = (subMask - 1) & next) {
                // 这学期上的课的数量不能超过k
                if (cnt[subMask] <= k) {
                    // 之前上完state, 这学期再上subMask, 看看会不会更好
                    dp[state | subMask] = Math.min(dp[state] + 1, dp[state | subMask]);
                }
            }
        }
        return dp[total - 1];
    }

    private int bitCount(int n) {
        int c = 0 ; // 计数器
        for(c = 0; n != 0 ; n >>=1) // 循环移位
            c += n & 1 ; // 如果当前位是1，则计数器加1
        return c ;
    }
}
