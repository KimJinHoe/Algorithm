package cote;

import java.io.*;

public class Boj_9251_LCS_dp {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		int len1 = str1.length;
		int len2 = str2.length;
		int[][] dp = new int[len1+1][len2+1];
		
		for(int i=1; i<=len1; i++) {
			for(int j=1; j<=len2; j++) {
				dp[i][j] = Math.max(Math.max(dp[i-1][j-1] + (str1[i-1]==str2[j-1]?1:0), dp[i][j-1]), dp[i-1][j]);
			}
		}
		
		System.out.println(dp[len1][len2]);
	}
}