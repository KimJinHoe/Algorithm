package cote;
//2023.02.16 120ms 1등
import java.io.*;
import java.util.*;

public class Boj_13460_구슬탈출2_bfs {
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		int N = read();
		int M = read();
		char[][] map = new char[N][M];
		boolean[][][][] visited = new boolean[N][M][N][M]; //빨간공, 파란공을 위한 4차원 방문처리 배열
		int[] ball = new int[4];
		int[] hall = new int[2];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				char c = (char) System.in.read();
				if(c == '.') map[i][j] = '.';
				else if(c == '#') map[i][j] = '#';
				else if(c == 'O') {
					map[i][j] = 'O';
					hall[0] = i;
					hall[1] = j;
				}
				else if(c == 'R') {
					map[i][j] = '.'; // .과 동일하게
					ball[0] = i;
					ball[1] = j;
				}
				else if(c == 'B') {
					map[i][j] = '.'; // .과 동일하게
					ball[2] = i;
					ball[3] = j;
				}
			}
			System.in.read();
			System.in.read(); //윈도우에서만 한 줄 더 추가(백준환경은 x)
		}
		
		//bfs
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(ball);
		visited[ball[0]][ball[1]][ball[2]][ball[3]] = true;
		int cnt = 0;
		while(!que.isEmpty()) {
			cnt++;
			int size = que.size();
			
			while(size-->0) {
				int[] cur = que.poll();
				
				label: for(int d=0; d<4; d++) {
					int ry = cur[0];
					int rx = cur[1];
					int by = cur[2];
					int bx = cur[3];
					boolean rCanGo = true;
					boolean bCanGo = true;
					boolean isEnd = false;
					while(rCanGo || bCanGo) {
						if(rCanGo) {
							ry += dy[d];
							rx += dx[d];
						}
						if(bCanGo) {
							by += dy[d];
							bx += dx[d];
						}
						if(map[ry][rx] == '#') {
							ry -= dy[d];
							rx -= dx[d];
							rCanGo = false;
						}
						else if(map[ry][rx] == 'O') {
							rCanGo = false;
							isEnd = true;
						}
						if(map[by][bx] == '#') {
							by -= dy[d];
							bx -= dx[d];
							bCanGo = false;
						}
						else if(map[by][bx] == 'O') {
							continue label;
						}
						//R공과 B공이 겹치면, 벽에 부딪혀 못 가는 공이 아닌 다른 공을 한 칸 제자리로
						if(ry==by && rx==bx) {
							if(!rCanGo) {
								by -= dy[d];
								bx -= dx[d];
								bCanGo = false;
							}
							else if(!bCanGo) {
								ry -= dy[d];
								rx -= dx[d];
								rCanGo = false;
							}
						}
					}
					if(isEnd) {
						System.out.println(cnt);
						return;
					}
					if(visited[ry][rx][by][bx]) continue;
					que.offer(new int[] {ry, rx, by, bx});
					visited[ry][rx][by][bx] = true;
				}
			}
			if(cnt==10) break;
		}
		System.out.println(-1);
		return;
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return n;
	}
	
}
