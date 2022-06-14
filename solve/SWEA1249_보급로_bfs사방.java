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

public class SWEA1249_보급로_bfs사방 {
	
	static int N;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//입력
			N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = line.charAt(j)-'0';
				}
			}
			
			//실행
			int ans = bfs(map);
			
			//출력
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int bfs(int[][] map) {
		int[][] dist = new int[N][N];
		for(int i=0; i<N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {0, 0});
		dist[0][0] = 0;
		
		while(!que.isEmpty()) {
			int size = que.size();
			while(size -->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				//사방탐색
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					//범위확인
					if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
					//최단거리 갱신 가능 시, 변경하고 que에 값 넣음
					if(dist[ny][nx] > map[ny][nx]+dist[y][x]) {
						dist[ny][nx] = map[ny][nx]+dist[y][x];
						que.offer(new int[] {ny, nx});
					}
				}
				
			}
		}
		return dist[N-1][N-1];
	}
}
