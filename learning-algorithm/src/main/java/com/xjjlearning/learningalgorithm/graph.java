package com.xjjlearning.learningalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class graph {
    public static void main(String[] args) {
        int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(leetcode743(times, 4, 2));
    }
    public static int leetcode743(int[][] times, int n, int k) {
        List<List<int[]>> edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] time : times) {
            edges.get(time[0]).add(new int[]{time[1], time[2]});
        }
        int res = 0;




        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2)->(o1[1] - o2[1]));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        q.offer(new int[]{k, 0});
        while (!q.isEmpty()) {
            int[] edge = q.poll(); //0是编号 1该节点是距离start的距离
            if (dist[edge[0]] < edge[1]) continue; // 有一条路径比当前更短
            for (int[] nei : edges.get(edge[0])) {
                // nei 0是编号 1是编号nei[0]到edge[0]的直接距离
                // start -> nei 和 start -> edge[0] -> nei[0]比较
                if (dist[nei[0]] > dist[edge[0]] + nei[1]) {
                    dist[nei[0]] = dist[edge[0]] + nei[1];
                    q.offer(new int[]{nei[0], dist[nei[0]]});
                }
            }
        }





        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, dist[i]);
        }
        return res;
    }
}
