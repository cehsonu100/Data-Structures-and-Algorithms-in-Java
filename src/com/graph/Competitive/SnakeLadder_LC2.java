package com.graph.Competitive;
import java.util.*;

public class SnakeLadder_LC2 {
    public static int snakesAndLadders(int[][] board) {

		//corner case 
		if(board == null || board.length == 0)return 0;

		int n = board.length;
		int k = 0;

		boolean[] visited = new boolean[n*n + 1];
        int[] board1d = new int [n*n + 1];
        
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

		Queue<Integer> queue = new LinkedList<>();

		queue.offer(1);
		int steps = 0;

		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i++){
				int cur = queue.poll();

                if(cur == n*n) return steps;
                
				for(int j = 1; j <= 6 && cur + j <= n*n; j++){
					int num1 = board1d[cur + j];
					int loca = (num1 == -1) ? cur+j: num1;

					if(!visited[loca]){
						visited[loca] = true;
						queue.offer(loca);
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
