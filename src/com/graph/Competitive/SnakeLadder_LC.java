package com.graph.Competitive;
import java.util.*;

public class SnakeLadder_LC {
    public static int snakesAndLadders(int[][] board) {

        int n = board.length;
        int k = 0;

        int[] board1d = new int[n*n + 1];
        boolean[] visited = new boolean[n*n + 1];
        
        //convert board 2d array into 1d for easy traversal 
        int sign = -1;
        for(int i = n-1; i >= 0; i--) {
            sign++;
            for(int j = 0; j < n; j++) {
                int x = j;
                if(sign % 2 != 0) {
                    x = n-1-j;
                }
                    
                
                board1d[++k] = board[i][x];
            }
        }
        
        Queue<Integer> q = new LinkedList<>();

        q.offer(1);
        int steps = 0;
        
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int curr = q.poll();

                if(curr == n*n) return steps;

                for(int x = 1; (x <= 6) && (x+curr <= n*n); x++) {
                    int temp = board1d[curr + x];
                    int adj = (temp == -1) ? (curr + x) : temp;
                    // int adj = curr + x;
                    // adj = board1d[adj] != -1 ? board1d[adj] : adj;
                    
                    if(!visited[adj]) {
                        visited[adj] = true;
                        q.offer(adj);
                    }
                }
                
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] board = {
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}};
        System.out.println(snakesAndLadders(board));
    }
}
