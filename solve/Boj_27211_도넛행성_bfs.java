package cote;
//2023.01.17 640ms 통과. 4등
import java.util.*;
import java.io.*;

public class Boj_27211_도넛행성_bfs {
	
	static boolean[][] map;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int N, M;
	static int ans;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				if(st.nextToken().charAt(0) == '1') {
					map[i][j] = true;
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0; j<M;j ++) {
				bfs(i,j);
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int i, int j) {
		if(map[i][j]) return;
		ans++; //가능한 구역 찾으면 ans+1해주고, 이어진 자리 전부 1처리
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {i, j});
		map[i][j] = true;
		
		while(!que.isEmpty()) {
			int size = que.size();
			
			while(size-->0) {
				int[] cur = que.poll();
				
				for(int d=0; d<4; d++) {
					int ny = (cur[0] + dy[d] + N)%N; // 0 ~ N-1
					int nx = (cur[1] + dx[d] + M)%M; // 0 ~ M-1
					if(map[ny][nx]) continue;
					map[ny][nx] = true;
					que.offer(new int[] {ny, nx});
				}
			}
		}
	}
}
