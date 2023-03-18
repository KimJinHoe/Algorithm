package cote;
//2023.03.18 120ms 4등
import java.io.*;
import java.util.*;

public class Boj_3055_탈출 {

	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static char[][] map;
	static int N, M;
	
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		Queue<int[]> wque = new LinkedList<int[]>();
		Queue<int[]> que = new LinkedList<int[]>();
		
		N = read();
		M = read();
		map = new char[N+2][M+2];
		Arrays.fill(map[0], 'X');
		Arrays.fill(map[N+1], 'X');
		
		for(int i=1; i<=N; i++) {
			map[i][0] = 'X';
			map[i][M+1] = 'X';
			for(int j=1; j<=M; j++) {
				char c = (char)System.in.read();
				if(c == '*') wque.add(new int[] {i, j});
				else if(c == 'S') {
					que.add(new int[] {i, j});
					map[i][j] = ',';
					continue;
				}
				map[i][j] = c;
			}
			System.in.read();
			System.in.read();
		}
		
		int time = 0;
		while(!que.isEmpty()) {
			time++;
			
			int size = wque.size();
			while(size-->0) {
				int[] wcur = wque.poll();
				
				for(int d=0; d<4; d++) {
					int ny = wcur[0] + dy[d];
					int nx = wcur[1] + dx[d];
					if(map[ny][nx] != '.' && map[ny][nx] != ',') continue;
					map[ny][nx] = '*';
					wque.add(new int[] {ny, nx});
				}
			}
			
			size = que.size();
			while(size-->0) {
				int[] cur = que.poll();
				
				for(int d=0; d<4; d++) {
					int ny = cur[0] + dy[d];
					int nx = cur[1] + dx[d];
					
					if(map[ny][nx] == 'D') {
						System.out.println(time);
						return;
					}
					if(map[ny][nx] != '.') continue;
					map[ny][nx] = ',';
					que.add(new int[] {ny, nx});
				}
			}
		}
		
		System.out.println("KAKTUS");
    }
    
	static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    boolean isNegative = n == 13;
	    if (isNegative) n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if(c == 13) System.in.read();
	    return isNegative ? ~n + 1 : n;
	}

}