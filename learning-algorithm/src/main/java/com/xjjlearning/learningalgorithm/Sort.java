package com.xjjlearning.learningalgorithm;

public final class Sort {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 2, 6, 7, 5, 1, 2, 3, 4, 5};
        MergeSort.sort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    //归并和二叉树的后序遍历异曲同工
    final static class MergeSort {
        private static int[] temp;
        public static void sort(int[] nums) {
            temp = new int[nums.length];
            sort(nums, 0, nums.length);
        }
        private static void sort(int[] nums, int lo, int hi) {
            //取左不取右
            if (lo == hi - 1) return;
            int mid = lo + (hi - lo) / 2;
            sort(nums, lo, mid);
            sort(nums, mid, hi);
            merge(nums, lo, mid, hi);
        }
        private static void merge(int[] nums, int lo, int mid, int hi) {
            for (int i = lo; i < hi; i++) {
                temp[i] = nums[i];
            }
            int i = lo, j = mid;
            //取左不取右
            for (int p = lo; p < hi; p++) {
                if (i == mid) {
                    nums[p] = temp[j++];
                } else if (j == hi) {
                    nums[p] = temp[i++];
                } else if (temp[i] > temp[j]) {
                    nums[p] = temp[j++];
                } else{
                    nums[p] = temp[i++];
                }
            }
        }
    }
}
