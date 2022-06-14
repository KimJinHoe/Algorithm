package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_4485_녹색옷입은애가젤다지_bfs_dp {
	
	static int N;
	static int[][] map;
	static int[][] memo;
	static int[] dy = {1,0,-1,0};
	static int[] dx = {0,1,0,-1}; //하우상좌
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = 0;
		while((N = Integer.parseInt(br.readLine())) != 0) {
			T++;
			//입력
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			memo = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(memo[i], Integer.MAX_VALUE);
			}
			
			//실행
			bfs();
			
			//출력
			sb.append("Problem ").append(T).append(": ").append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void bfs() {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {0,0});
		memo[0][0] = map[0][0];
		
		while(!que.isEmpty()) {
			int size = que.size();
			
			while(size-->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
					if(memo[ny][nx]<=memo[y][x]+map[ny][nx]) continue;
					que.offer(new int[] {ny, nx});
					memo[ny][nx] = memo[y][x]+map[ny][nx];
				}
			}
		}
		ans = memo[N-1][N-1];
	}
}
