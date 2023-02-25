package cote;

import java.util.*;
import java.io.*;

public class Boj_12100_2048_dfs {
	
	static int N;
	static int max = Integer.MIN_VALUE;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = read();
				max = Math.max(max, map[i][j]);
			}
		}
		
		dfs(0, map);
		
		System.out.println(max);
	}
	
	static void dfs(int cnt, int[][] map) {
		//기저조건
		if(cnt == 5) return;
		
		for(int d=0; d<4; d++) {
			dfs(cnt+1, act(d, map));
		}
		
	}
		static int[][] act(int d, int[][] map) {
			int[][] tmp = new int[N][N];
			//상
			if(d == 0) {
				for(int j=0; j<N; j++) {
					Deque<Integer> dque = new ArrayDeque<Integer>();
					for(int i=0; i<N; i++) {
						if(map[i][j] == 0) continue;
						if(dque.isEmpty()) dque.add(map[i][j]);
						else if(dque.peekLast() == map[i][j]) {
							dque.pollLast();
							dque.add(-2*map[i][j]);
							max = Math.max(max, 2*map[i][j]);
						}
						else dque.add(map[i][j]);
					}
					
					int i = 0;
					while(!dque.isEmpty()) {
						tmp[i++][j] = Math.abs(dque.poll());
					}
				}
			}
			//하
			else if(d == 1) {
				for(int j=0; j<N; j++) {
					Deque<Integer> dque = new ArrayDeque<Integer>();
					for(int i=N-1; i>=0; i--) {
						if(map[i][j] == 0) continue;
						if(dque.isEmpty()) dque.add(map[i][j]);
						else if(dque.peekLast() == map[i][j]) {
							dque.pollLast();
							dque.add(-2*map[i][j]);
							max = Math.max(max, 2*map[i][j]);
						}
						else dque.add(map[i][j]);
					}
					
					int i = N-1;
					while(!dque.isEmpty()) {
						tmp[i--][j] = Math.abs(dque.poll());
					}
				}
			}
			//좌
			else if(d == 2) {
				for(int i=0; i<N; i++) {
					Deque<Integer> dque = new ArrayDeque<Integer>();
					for(int j=0; j<N; j++) {
						if(map[i][j] == 0) continue;
						if(dque.isEmpty()) dque.add(map[i][j]);
						else if(dque.peekLast() == map[i][j]) {
							dque.pollLast();
							dque.add(-2*map[i][j]);
							max = Math.max(max, 2*map[i][j]);
						}
						else dque.add(map[i][j]);
					}
					
					int j = 0;
					while(!dque.isEmpty()) {
						tmp[i][j++] = Math.abs(dque.poll());
					}
				}
			}
			//우
			else if(d == 3) {
				for(int i=0; i<N; i++) {
					Deque<Integer> dque = new ArrayDeque<Integer>();
					for(int j=N-1; j>=0; j--) {
						if(map[i][j] == 0) continue;
						if(dque.isEmpty()) dque.add(map[i][j]);
						else if(dque.peekLast() == map[i][j]) {
							dque.pollLast();
							dque.add(-2*map[i][j]);
							max = Math.max(max, 2*map[i][j]);
						}
						else dque.add(map[i][j]);
					}
					
					int j = N-1;
					while(!dque.isEmpty()) {
						tmp[i][j--] = Math.abs(dque.poll());
					}
				}
			}
			
			return tmp;
		}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
}
