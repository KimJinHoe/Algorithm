package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_14502_연구소_bfs {
	
	static int N, M;
	static List<int[]> virus;
	static int max = Integer.MIN_VALUE;
	static int[][] map;
	static int[] selected;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1}; //상하좌우
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		List<int[]> list = new ArrayList<int[]>();
		virus = new ArrayList<int[]>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num==2) virus.add(new int[] {i, j});
				else if(num==0) list.add(new int[] {i, j});
			}
		}
		selected = new int[3];
		
		//실행
		comb(0, 0, list.size(), list);
		
		//출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void comb(int cnt, int start, int size, List<int[]> list) {
		if(cnt == 3) {
			//벽으로 만듦
			for(int i=0; i<3; i++) {
				map[list.get(selected[i])[0]][list.get(selected[i])[1]] = 1;
			}
			//bfs 실행
			if(max < bfs()) max = bfs();
			
			//빈 공간으로 만듦
			for(int i=0; i<3; i++) {
				map[list.get(selected[i])[0]][list.get(selected[i])[1]] = 0;
			}
			return;
		}
		
		for(int i=start; i<size; i++) {
			selected[cnt] = i;
			comb(cnt+1, i+1, size, list);
		}
	}
	
	static int bfs() {
		Queue<int[]> que = new LinkedList<int[]>();
		int[][] temp = new int[N][M];
		for(int i=0; i<N; i++) {
			temp[i] = map[i].clone();
		}
		int vsize = virus.size();
		for(int i=0; i<vsize; i++) {
			que.offer(new int[] {virus.get(i)[0], virus.get(i)[1]});
		}
		
		while(!que.isEmpty()) {
			int size = que.size();
			
			while(size-->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					
					if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
					if(temp[ny][nx] != 0) continue;
					que.offer(new int[] {ny, nx});
					temp[ny][nx] = 2;
				}
			}
		}
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(temp[i][j] == 0) cnt++; 
			}
		}
		return cnt;
	}
}
