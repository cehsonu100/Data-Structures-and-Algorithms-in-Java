package com.graph;

import java.util.*;
import java.util.Map.Entry;

public class MinKnightMove {
    public int minStepToReachTarget(int knightpos[], int targetpos[], int N) {
        int[] vertexI = {1, 1, 2, 2, -1, -1, -2, -2};
        int[] vertexJ = {2, -2, 1, -1, 2, -2, 1, -1}; 
        Queue<Map<Integer, Integer>> q = new LinkedList<>();
        boolean[][] isVisisted = new boolean[N+1][N+1];
        q.add(Map.of(knightpos[0], knightpos[1]));
        isVisisted[knightpos[0]][knightpos[1]] = true;
        int[][] distance = new int[N+1][N+1];
        distance[knightpos[0]][knightpos[1]] = 0;
        int minMove = 0;
        while(q.size() != 0) {
            Map<Integer, Integer> current = q.remove();
            Map.Entry<Integer, Integer> entry = current.entrySet().iterator().next();
            int currentI = entry.getKey();
            int currentJ = entry.getValue();
            if(currentI  == targetpos[0] && currentJ == targetpos[1]) {
                return distance[currentI][currentJ];
            }
            for(int k = 0; k < 8; k++) {

                if(isPath(currentI + vertexI[k], currentJ + vertexJ[k], N, isVisisted)) {
                    distance[currentI + vertexI[k]][currentJ + vertexJ[k]] = distance[currentI][currentJ] + 1;
                    q.add(Map.of(currentI + vertexI[k], currentJ + vertexJ[k]));
                    isVisisted[currentI + vertexI[k]][currentJ + vertexJ[k]] = true;
                    
                }
            }

        }
        return -1;
        
    }
    
    public static boolean isPath(int i, int j, int N, boolean[][] isVisisted) {
        if(i < 1 || i > N || j < 1 || j > N || isVisisted[i][j])
            return false;
        return true;
    }
    
    public static void main(String[] args) {
        MinKnightMove minKnightMove = new MinKnightMove();
        int[] target = {1,1};
        int[] source = {4,5};
        int minMove = minKnightMove.minStepToReachTarget(source, target, 6);
        System.out.println(minMove); 
    }
}
