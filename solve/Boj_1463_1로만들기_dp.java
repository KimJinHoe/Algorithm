package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1463_1로만들기_dp {
	
	static int[] memo;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		memo = new int[N+1];
		dp(N);
		System.out.println(memo[N]);
	}
	
	static void dp(int N) {
		int[] temp = new int[3];
		for(int i=2; i<=N; i++) {
			temp[0] = memo[i-1]+1;
			if(i%2==0) temp[1] = memo[i/2] + 1;
			else temp[1] = Integer.MAX_VALUE;
			if(i%3==0) temp[2] = memo[i/3] + 1;
			else temp[2] = Integer.MAX_VALUE;
			memo[i] = Math.min(Math.min(temp[0], temp[1]), temp[2]);
		}
	}
}
