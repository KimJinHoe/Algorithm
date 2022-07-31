package algo;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Boj_4811_¾Ë¾à {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/algo/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		BigInteger[] dp = new BigInteger[31];
		Arrays.fill(dp, BigInteger.valueOf(0));
		dp[0] = BigInteger.valueOf(1);
		dp[1] = BigInteger.valueOf(1);
		dp[2] = BigInteger.valueOf(2);
		
		for(int i=3; i<=30; i++) {
			for(int j=0; j<i; j++) {
				dp[i] = dp[j].multiply(dp[i-j-1]).add(dp[i]); 
			}
		}
		while(true) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) break;
			sb.append(dp[num]).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
