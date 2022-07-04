package cote;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_18405_경쟁적전염_bfs {
	
	static int N, S, ans_y, ans_x;
	static int[][] map;
	static int[] dy = {-1,1,0,0}; //상하좌우
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st.nextToken(); //K는 필요하지 않으니 버림
		map = new int[N][N];
		for(int i=0; i<N;i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		ans_y = Integer.parseInt(st.nextToken())-1;
		ans_x = Integer.parseInt(st.nextToken())-1;

		//실행
		if(S != 0) bfs();
		
		//출력
		sb.append(map[ans_y][ans_x]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void bfs() {
		Queue<int[]> que = new LinkedList<int[]>();
		int[][] depths = new int[N][N];
		int depth = 0;
		for(int i=0; i<N;i ++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0) continue;
				que.offer(new int[] {i, j, map[i][j]});
			}
		}

		while(!que.isEmpty()) {
			int size = que.size();
			depth++;
			
			while(size-->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				int type = cur[2];
				
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
					if(map[ny][nx] == 0) {
						map[ny][nx] = type;
						depths[ny][nx] = depth;
						que.offer(new int[] {ny, nx, type});
					}
					else if(depths[ny][nx] < depth) continue; //depth가 낮은 바이러스가 있으면 갈 수 없음
					else if(map[ny][nx] <= type) continue; // 존재하던 바이러스 값이 더 낮으면 우선순위가 밀려 갈 수 없음
					map[ny][nx] = type;
					que.offer(new int[] {ny, nx, type});
				}
			}
			if(depth == S) return;
			if(map[ans_y][ans_x] != 0) return;
		}
	}
}