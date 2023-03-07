package cote;

import java.io.*;
import java.util.*;

public class Boj_7579_토마토_bfs {
	
	static int H, N, M;
	static int[] dh = {-1,1,0,0,0,0}; //위아래상하좌우
    static int[] dy = {0,0,-1,1,0,0};
    static int[] dx = {0,0,0,0,-1,1};
    static boolean[][][] map;
    static Queue<int[]> que= new LinkedList<>();;

    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
        M = read();
        N = read();
        H = read();
        map = new boolean[H+2][N+2][M+2];
        
        //배열입력
        for(int h=1; h<=H; h++) {
	        for(int i=1; i<=N; i++) {
	            for(int j=1; j<=M; j++) {
	                int num = read() + 1; //0: 없음. 1: 안익음. 2:익음
	                if(num == 2) que.add(new int[] {h, i, j});
	                else if(num == 1) map[h][i][j] = true; //true:안익음. false:없거나익음
	            }
	        }
        }
        
        //실행
        System.out.println(dfs());
    }
    
    static int dfs() {
        int ans = -1;
        
        while(!que.isEmpty()) {
            int size = que.size();
            
            while(size-->0) {
                int[] cur = que.poll();
                
                for(int d=0; d<6; d++) {
                	int nh = cur[0] + dh[d];
                    int ny = cur[1] + dy[d];
                    int nx = cur[2] + dx[d];
                    if(!map[nh][ny][nx]) continue;
                	que.add(new int[] {nh, ny, nx});
                	map[nh][ny][nx] = false;
                }
            }
            ans++;
        }
        
        //최종적으로 안 익는 토마토가 있으면 depth는 -1 
        for(int h=1; h<=H; h++) {
	        for(int i=1; i<=N; i++) {
		        for(int j=1; j<=M; j++) {
		            if(!map[h][i][j]) continue;
		            return -1;
		        }
	        }
        }
        return ans;
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