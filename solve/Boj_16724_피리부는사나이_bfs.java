package cote;
//2023.02.15 284ms 1등
import java.io.*;
import java.util.*;

public class Boj_16724_피리부는사나이_bfs {
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		int N = read();
		int M = read();
		char[][] map = new char[N][M];
		int[][] visited = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				char c = (char) System.in.read();
				if(c == 'U') map[i][j] = 0;
				else if(c == 'D') map[i][j] = 1;
				else if(c == 'L') map[i][j] = 2;
				else if(c == 'R') map[i][j] = 3;
			}
			System.in.read();
			System.in.read(); //윈도우에서만 한 줄 더 추가(백준환경은 x)
		}
		
		//bfs
		Queue<int[]> que = new LinkedList<int[]>();
		int cnt = 0;
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j] != 0) continue;
				cnt++;
				que.offer(new int[] {i, j});
				visited[i][j] = cnt;
				
				while(!que.isEmpty()) {
					int[] cur = que.poll();
					int type = map[cur[0]][cur[1]];
					int ny = cur[0] + dy[type];
					int nx = cur[1] + dx[type];
					
					if(visited[ny][nx] == 0) {
						visited[ny][nx] = cnt;
						que.offer(new int[] {ny, nx});
					}
					else if(visited[ny][nx] == cnt) {
						ans++;
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return n;
	}
	
}
