package cote;

import java.util.*;
import java.io.*;
//2023.01.20 172ms 5등
public class Boj_17212_미팅_dp {
	
	static int N, M, C;
	static int[][] map;
	static int[] arr;
	static int[] brr;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		brr = new int[M+1];
		map = new int[C+1][C+1];
		dp = new int[N+1][M+1];
		
		for(int i=1; i<C+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<(C+1/2)+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map[j][i] = map[i][j];
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) {
			brr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				dp[i][j] = Math.max(Math.max(dp[i-1][j-1] + map[arr[i]][brr[j]], dp[i][j-1]), dp[i-1][j]);
			}
		}
		
		System.out.println(dp[N][M]);
	}
}
