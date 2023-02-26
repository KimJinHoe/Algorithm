package cote;

import java.util.*;
import java.io.*;

public class Boj_7579_앱_dp {
	
	static int N, M;
	static int[][] arr;
	static long[][] dp;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = read();
		M = read();
		
		arr = new int[N+1][2];
        int MM = N*100+1;
		dp = new long[N+1][MM];
		
		
		for(int i=1; i<N+1; i++) {
			arr[i][0] = read();
		}
		for(int i=1; i<N+1; i++) {
			arr[i][1] = read();
		}
		
		for(int i=1; i<N+1; i++) {
			int m = arr[i][0];
			int c = arr[i][1];
            
			if(dp[i-1][0] == 0 && dp[i-1][c] < m) dp[i][c] = m;
			
			for(int j=0; j<MM; j++) {
				if(dp[i-1][j] == 0) continue;
				if(dp[i-1][j] > dp[i][j]) dp[i][j] = dp[i-1][j];
				dp[i][j+c] = Math.max(dp[i-1][j+c], dp[i-1][j]+m);
			}
		}
		
		for(int i=0; i<MM; i++) {
			if(dp[N][i] >= M) {
				System.out.println(i);
				return;
			}
		}
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
