package com.graph.Competitive.LeetCodeJan2021;

public class MinOperationToZero {
    int [][] temp;
    
    public int minOperations(int[] nums, int x) {
        temp = new int[nums.length][nums.length];
        return helper(nums, 0, nums.length - 1, x);
    }
    
    public int helper(int[] nums, int left, int right, int remain) {
        if (left > right || remain < 0) {
            return -1;
        }
        if (temp[left][right] != 0) return temp[left][right];
        if (remain - nums[left] == 0 || remain - nums[right] == 0) {
            temp[left][right] = 1;
            return 1;
        }
        int minusLeft = helper(nums, left + 1, right, remain - nums[left]);
        int minusRight = helper(nums, left, right - 1, remain - nums[right]);
        if (minusLeft == -1 && minusRight == -1) {
            temp[left][right] = -1;
            return -1;
        }
        if (minusLeft == -1 || minusRight == -1) {
            temp[left][right] = minusLeft == -1 ? 1 + minusRight : 1 + minusLeft;
            return temp[left][right];
        }
        temp[left][right] = 1 + Math.min(minusLeft, minusRight);
        return temp[left][right];
    }

    public static void main(String[] args) {
        int[] arr = {1,1,4,2,3};
        MinOperationToZero minOperationToZero = new MinOperationToZero();
        int res = minOperationToZero.minOperations(arr, arr.length);
        System.out.println(res);
    }
}
