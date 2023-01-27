package cote;
//다익스트라가 더 효율적임
import java.io.*;
import java.util.*;

public class Boj_1504_특정한최단경로_Floyd {
	
	static int N, E;
	static int[][] map;
	static int v1, v2;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		E = read();
		map = new int[N+1][N+1];
		int MAXVALUE = 1000000; //N이 최대값(800)이고 c가 모두 1000일 때, 거리 최대는 80만
		for(int i=1; i<=N; i++) {
			Arrays.fill(map[i], MAXVALUE); 
			map[i][i] = 0;
		}
		
		for(int i=0; i<E; i++) {
			int a = read();
			int b = read();
			int c = read();
			map[a][b] = c;
			map[b][a] = c;
		}
		v1 = read();
		v2 = read();
		
		//Floyd 경출목
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(k==i) continue;
				for(int j=1; j<=N; j++) {
					if(k==j || i==j) continue;
					//경유하는 것이 더 빠르면 값 교체
					if(map[i][j] <= map[i][k] + map[k][j]) continue;
					map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		//v1~v2 갈 수 없으면 답이 없음
		if(map[v1][v2] == MAXVALUE) {
			System.out.println(-1);
			return;
		}
		//출-v1-v2-목 없고, 출-v2-v1-목 도 없으면 답 없음 
		if((map[1][v1]==MAXVALUE || map[v2][N]==MAXVALUE) 
				&& (map[1][v2]==MAXVALUE || map[v1][N]==MAXVALUE)) {
			System.out.println(-1);
			return;
		}
		int ans = (map[1][v1]+map[v2][N] < map[1][v2]+map[v1][N]) ?  map[1][v1]+map[v2][N] : map[1][v2]+map[v1][N];
		System.out.println(ans + map[v1][v2]);
	}
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
	
}
