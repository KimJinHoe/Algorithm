package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2638_치즈_bfs {
	
	static int N, M;
	static int ans;
	static int[][] map;
	static int[] dy = {-1,1,0,0}; //상하좌우
	static int[] dx = {0,0,-1,1};
	
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
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//실행
		run();
		
		//출력
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run() {
		Queue<int[]> que_air = new LinkedList<int[]>(); //공기큐
		Queue<int[]> que = new LinkedList<int[]>(); //치즈큐
		//map[y][x] 정의
		//[공기] 0:방문안함(내부공기), -1:방문함(외부공기)
		//[치즈] 1~5: 외부공기 접촉면(0개~4개)
		
		//왼쪽, 오른쪽 가장자리부터 외부공기 탐색
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j+=M-1) { 
				if(map[i][j] != 0) continue; //방문안한 공기가 아니면 패스
				bfs_air(i, j, que_air, que); //공기bfs 실행
			}
		}
		
		//위쪽, 아래쪽 가장자리부터 외부공기 탐색
		for(int i=0; i<N; i+=N-1) {
			for(int j=0; j<M; j++) { 
				if(map[i][j] != 0) continue; //방문안한 공기가 아니면 패스
				bfs_air(i, j, que_air, que); //공기bfs 실행
			}
		}
		
		//치즈 녹기 bfs 시작
		while(!que.isEmpty()) {
			int size = que.size();
			ans++;
			while(size-->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				map[y][x] = -1; //치즈가 녹아서 외부 공기가 됨
				
				for(int d=0; d<4; d++) {
					int ny = cur[0] + dy[d];
					int nx = cur[1] + dx[d];
					if(ny<0 || nx<0 || ny>=N || nx>=M) continue; //범위조건
					if(map[ny][nx] == 0) { //내부공기면 공기bfs를 시작해서 전부 외부공기로 바꿈
						bfs_air(ny, nx, que_air, que);
						continue;
					}
					if(map[ny][nx] == -1) continue; //외부공기면 패스
					map[ny][nx]++; //인접면 증가
					if(map[ny][nx] == 3) que.offer(new int[] {ny, nx}); //인접면이 2면 치즈큐에 추가
				}
			}
		}
		
	}
	
	static void bfs_air(int i, int j, Queue<int[]> que_air, Queue<int[]> que) {
		que_air.offer(new int[] {i, j});
		map[i][j] = -1; //외부공기 방문처리
		
		while(!que_air.isEmpty()) {
			int[] cur = que_air.poll();
			
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dy[d];
				int nx = cur[1] + dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=M) continue; //범위조건
				if(map[ny][nx]>=1) { //치즈면 인접면 수+1
					map[ny][nx]++;
					if(map[ny][nx] == 3) que.offer(new int[] {ny, nx}); //인접면이 2면 치즈큐에 추가
					continue;
				}
				if(map[ny][nx] != 0) continue; //방문안한 공기가 아니면 패스
				que_air.offer(new int[] {ny, nx});
				map[ny][nx] = -1; //방문한 공기(외부공기) 처리
			}
		}
	}
}
