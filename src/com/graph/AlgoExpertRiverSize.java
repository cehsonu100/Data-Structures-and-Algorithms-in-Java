package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class AlgoExpertRiverSize {

    public static List<Integer> riverSizes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        ArrayList<Integer> tracker = new ArrayList<Integer>();
        ArrayList<Integer> riversSize = new ArrayList<Integer>();
        boolean[][] isVisited = new boolean[n][m];
        int sourceCount = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!isVisited[i][j]) {
                    tracker.add(0);
                    DFS(matrix, i, j, riversSize, isVisited, 0, tracker);
                }
            }
        }
        return riversSize;
      }
        
    public static void DFS(int[][] matrix, int i, int j, ArrayList<Integer> riversSize, boolean[][] isVisited, int sum, ArrayList<Integer> tracker){
        if(isVisited[i][j])
            return;
        isVisited[i][j] = true;
        if(matrix[i][j] == 1)
            sum = sum + 1;
        if(matrix[i][j] == 0)
            return;
        if(isPath(i-1, j, matrix.length, matrix[0].length)) 
            DFS(matrix, i-1, j, riversSize, isVisited, sum, tracker);
        if(isPath(i+1, j, matrix.length, matrix[0].length)) 
            DFS(matrix, i+1, j, riversSize, isVisited, sum, tracker);
        if(isPath(i, j-1, matrix.length, matrix[0].length)) 
            DFS(matrix, i, j-1, riversSize, isVisited, sum, tracker);
        if(isPath(i, j+1, matrix.length, matrix[0].length)) 
            DFS(matrix, i, j+1, riversSize, isVisited, sum, tracker);
        if(tracker.get(tracker.size()-1) == 0) {
            riversSize.add(sum);
            tracker.set(tracker.size()-1, 1);
        }
        sum = 0;
    }
        
    public static boolean isPath(int i, int j, int n, int m) {
        if(i < 0 || i >= n || j < 0 || j >= m) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1, 0, 1}};
        System.out.println(riverSizes(matrix));
    }
}
