package cote;
//2023.03.03 228ms 1등
import java.util.*;
import java.io.*;

public class Boj_14500_테트로미노 {
	
	static int N, M;
	static int[][] map;
	static int ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		M = read();
		map = new int[N+2][M+2];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				map[i][j] = read();
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				type1(i, j);
				type2(i, j);
				type3(i, j);
			}
		}
		
		System.out.println(ans);
	}
	
	// ㅁㅁㅁ을 기준으로 하는 7가지 도형
	static void type1(int i, int j) {
		if(j+2 > M) return;
		int max = map[i-1][j];
		if(max < map[i-1][j+1]) max = map[i-1][j+1];
		if(max < map[i-1][j+2]) max = map[i-1][j+2];
		if(max < map[i][j+3]) max = map[i][j+3];
		if(max < map[i+1][j]) max = map[i+1][j];
		if(max < map[i+1][j+1]) max = map[i+1][j+1];
		if(max < map[i+1][j+2]) max = map[i+1][j+2];
		max += map[i][j] + map[i][j+1] + map[i][j+2];
		
		if(ans < max) ans = max;
	}
	
	//ㅁ
	//ㅁ
	//ㅁ 을 기준으로 하는 7가지 도형
	static void type2(int i, int j) {
		if(i+2 > N) return;
		int max = map[i][j-1];
		if(max < map[i+1][j-1]) max = map[i+1][j-1];
		if(max < map[i+2][j-1]) max = map[i+2][j-1];
		if(max < map[i+3][j]) max = map[i+3][j];
		if(max < map[i][j+1]) max = map[i][j+1];
		if(max < map[i+1][j+1]) max = map[i+1][j+1];
		if(max < map[i+2][j+1]) max = map[i+2][j+1];
		max += map[i][j] + map[i+1][j] + map[i+2][j];
		
		if(ans < max) ans = max;
	}
	
	//ㅁㅁ을 기준으로 하는 5가지 도형
	static void type3(int i, int j) {
		if(j+1 > M) return;
		int max = map[i+1][j]+map[i+1][j+1];
		if(max < map[i+1][j+1]+map[i+1][j+2]) max = map[i+1][j+1]+map[i+1][j+2];
		if(max < map[i-1][j+1]+map[i-1][j+2]) max = map[i-1][j+1]+map[i-1][j+2];
		if(max < map[i-1][j]+map[i+1][j+1]) max = map[i-1][j]+map[i+1][j+1];
		if(max < map[i+1][j]+map[i-1][j+1]) max = map[i+1][j]+map[i-1][j+1];
		max += map[i][j] + map[i][j+1];
		
		if(ans < max) ans = max;
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c=System.in.read())>32) n = (n<<3) + (n<<1) + (c&15);
		if(c==13) System.in.read();
		return negative ? ~n + 1 : n;
	}
}
