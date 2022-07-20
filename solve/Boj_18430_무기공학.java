package algo;

import java.util.*;
import java.io.*;

public class Boj_18430_무기공학 {
	
	static int H,W;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1, 0, 1, 0}; //상우하좌
	static int[] dx = {0, 1, 0, -1};
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/algo/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W];
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		run(0, 0, 0);
		
		System.out.println(max);
	}
	
	static void run(int y, int x, int sum) {
		for(int i=y; i<H; i++) {
			for(int j=x; j<W; j++) {
				if(visited[i][j]) continue;
				for(int d=0; d<4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
					if(visited[ny][nx]) continue;
					
					int d2 = (d+1) % 4;
					int ny2 = i + dy[d2];
					int nx2 = j + dx[d2];
					if(ny2<0 || nx2<0 || ny2>=H || nx2>=W) {
						d++;
						continue;
					}
					if(visited[ny2][nx2]) {
						d++;
						continue;
					}
					
					visited[ny][nx] = true;
					visited[ny2][nx2] = true;
					visited[i][j] = true;
					run(i, j, sum + map[ny][nx]+map[ny2][nx2]+map[i][j]*2);
					
					visited[ny][nx] = false;
					visited[ny2][nx2] = false;
					visited[i][j] = false;
				}
			}
			x = 0;
		}
		
		if(sum > max) max = sum;
	}
}
