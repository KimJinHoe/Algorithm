package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11051_이항계수2_dp {

	static int[][] memo;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		if(k==0) k=n;
		memo = new int[n+1][k+1];
		for(int i=0; i<n+1; i++) {
			memo[i][0] = 1;
			memo[i][1] = i;
		}
		Arrays.fill(memo[1], 1);
		//실행
		sb.append(perm(n, k));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int perm(int n, int k) {
		k = ((k<(n-k))?k:(n-k));
		if(memo[n][k]!=0) return memo[n][k];
		memo[n-1][k-1] = perm(n-1, k-1);
		memo[n-1][k] = perm(n-1, k);
		return (memo[n-1][k-1] + memo[n-1][k])%10007;
	}
}
