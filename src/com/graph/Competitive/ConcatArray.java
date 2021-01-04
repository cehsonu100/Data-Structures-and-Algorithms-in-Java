package com.graph.Competitive;
import java.util.*;

public class ConcatArray {
    public static boolean canFormArray(int[] arr, int[][] pieces) {
        HashMap<Integer, int[]> pHash = new HashMap<>();
        for(int[] p : pieces) {
            pHash.put(p[0], p);
        }
        int x = 0;
        while(x < arr.length) {
            int curr = arr[x];
            if(!pHash.containsKey(curr))
                return false;
            int[] tmp = pHash.get(curr);
            if(tmp.length == 1) {
                x++;
                continue;
            }
            for(int y : tmp) {
                if(y != arr[x])
                    return false;
                x++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {91,4,64,78};
        int[][] pieces = {{78},{64,4},{91}};
        System.out.println(canFormArray(arr, pieces));
    }

}
