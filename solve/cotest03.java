package codetest;

import java.io.*;
import java.util.*;

public class cotest03 {
	
	static int[][] map = new int[6][6];
	static int[] height = new int[6];
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) {
		int[][] macaron = {{1, 1}, {2, 1}, {1, 2}, {3, 3}, {6, 4}, {3, 1}, {3, 3}, {3, 3}, {3, 4}, {2, 1}};
		
		Arrays.fill(height, 5);
		
		for(int[] maca : macaron) {
			int w = maca[0] - 1;
			int h = height[w];
			int color = maca[1];
			
			//마카롱 쌓음. 맨아래는 map[5][]이다.
			map[h][w] = color;
			height[w]--;
			
			//체크
			boolean[][] checked = new boolean[6][6];
			check(h, w, color, checked);
		}
		
		String[] answer = new String[6];
		for(int i=0; i<6; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<6; j++) {
				sb.append(map[i][j]);
			}
			answer[i] = sb.toString();
		}
		
		System.out.println(Arrays.toString(answer));
	}
	
	static void check(int y, int x, int color, boolean[][] checked) {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean visited[][] = new boolean[6][6]; //que에서 방문했는지 확인하기 위한 배열
		que.add(new int[] {y, x});
		checked[y][x] = true; //3개 이상일 때 터지고 해당 위치 재확인했는지 확인하기 위한 배열
		int cnt = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			while(size-->0) {
				int[] cur = que.poll();
				
				for(int d=0; d<4; d++) {
					int ny = cur[0] + dy[d];
					int nx = cur[1] + dx[d];
					if(ny<0 || nx<0 || ny>=6 || nx>= 6) continue;
					if(map[ny][nx] != color) continue;
					if(visited[ny][nx]) continue;
					visited[ny][nx] = true;
					checked[ny][nx] = true;
					que.add(new int[] {ny, nx});
					cnt++;
				}
			}
		}
		
		//제거하기
		if(cnt >= 3) remove(visited, checked);

		
	}
	
	static void remove(boolean[][] visited, boolean[][] checked) {
		//제거하기
		for(int i=5; i>=0; i--) {
			for(int j=0; j<6; j++) {
				if(!visited[i][j]) continue;
				visited[i][j] = false;
				checked[i][j] = false;
				
				int bombcnt = 1;
				int ti = i-1;
				//위로 몇 개 터지는지
				while(ti>=0 && visited[ti][j]) {
					visited[ti][j] = false;
					checked[ti][j] = false;
					bombcnt++;
					ti--;
				}
				
				//옮기기
				for(int h=i; h>=0; h--) {
					if(h-bombcnt < 0) map[h][j] = 0; 
					else map[h][j] = map[h-bombcnt][j];
				}
			}
		}
		
		//다시 체크
		for(int i=5; i>=0; i--) {
			for(int j=0; j<6; j++) {
				if(map[i][j] != 0 && !checked[i][j]) check(i, j, map[i][j], checked);
			}
		}
	}
	
}
