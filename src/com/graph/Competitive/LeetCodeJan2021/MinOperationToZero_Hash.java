package com.graph.Competitive.LeetCodeJan2021;

import java.util.*;

public class MinOperationToZero_Hash {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for(int i : nums)
            sum += i;

        int target = sum - x;
        if(target == 0) return nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0,-1);
        int maxLengthSubArray = 0;
        int prefixSum = 0;

        for(int i = 0 ; i< nums.length ; i++ ) {
            prefixSum += nums[i];
            map.put(prefixSum,i);
            if(map.containsKey(prefixSum - target)) {
                int currentLengthSubArray = i - map.get(prefixSum - target);
                maxLengthSubArray = Math.max(maxLengthSubArray,currentLengthSubArray);
            }
        }
        return (maxLengthSubArray==0) ? -1 :nums.length- maxLengthSubArray;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,4,2,3};
        MinOperationToZero minOperationToZero = new MinOperationToZero();
        int res = minOperationToZero.minOperations(arr, arr.length);
        System.out.println(res);
    }
}
