package cote;
//2023.03.03 412ms 1등
//3차원 배열을 이용하는 방법으로 풀었지만 Boj14442 풀면서 2차원 효율적인 배열로 바꿈
import java.util.*;
import java.io.*;

public class Boj_2206_벽부수고이동하기_bfs {
	
	static int N, M, K;
	static int[][] check; //해당 좌표를 최초로 지나갈 때의 벽 뚫은 횟수
	static boolean[][] map; //벽과 길이 표시된 맵
	static boolean[][][] visited; //뚫은 횟수에 따른 방문 배열
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		M = read();
		K = 1;
		
		if(N==1 && M==1) {
			System.out.println(1);
			return;
		}
		
		map = new boolean[N+2][M+2]; //외각패딩으로 +2
		visited = new boolean[K+1][N+2][M+2];
		check = new int[N+2][M+2];
		
		Arrays.fill(check[0], -1);
		Arrays.fill(check[N+1], -1);
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(check[i], Integer.MAX_VALUE);
			check[i][0] = -1;
			check[i][M+1] = -1;
			
			for(int j=1; j<=M; j++) {
				map[i][j] = System.in.read()=='1';
			}
			System.in.read();
			System.in.read();
		}
		
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {1,1,0});
		visited[0][1][1] = true;
		check[1][1] = 0;
		
		int depth = 1;
		while(!que.isEmpty()) {
			int size = que.size();
			depth++;
			
			while(size-->0) {
				int[] cur = que.poll();
				int v = cur[2];
				
				for(int d=0; d<4; d++) {
					int ny = cur[0] + dy[d];
					int nx = cur[1] + dx[d];
					if(check[ny][nx] > v) check[ny][nx] = v;
					else continue;
					if(map[ny][nx]) { //벽이면
						if(v == K) continue; //뚫을 수 있는 횟수 다 사용하면 패스
						visited[v+1][ny][nx] = true;
						if(ny!=N || nx!=M) que.add(new int[] {ny, nx, v+1}); //목적지가 아니면 계속 진행
						else {
							System.out.println(depth);
							return;
						}
						continue;
					}
					//길이면
					visited[v][ny][nx] = true;
					if(ny!=N || nx!=M) que.add(new int[] {ny, nx, v}); //목적지가 아니면 계속 진행
					else {
						System.out.println(depth);
						return;
					}
				}
			}
		}
		
		System.out.println(-1);
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
