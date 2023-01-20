package cote;
//160ms
import java.io.*;

public class Boj_9252_LCS2_dp {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		int len1 = str1.length;
		int len2 = str2.length;
		int[][] dp = new int[len1+1][len2+1];
		
		for(int i=1; i<=len1; i++) {
			for(int j=1; j<=len2; j++) {
				if(str1[i-1] == str2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		sb.append(dp[len1][len2]).append('\n');
		StringBuilder sb2 = new StringBuilder();
		int i = len1;
		int j = len2;
		while(i != 0 && j != 0) {
			if(str1[i-1] == str2[j-1]) {
				sb2.append(str1[i-1]);
				i--;
				j--;
			} else if(dp[i][j-1] >= dp[i-1][j]){
				j--;
			} else {
				i--;
			}
		}
		
		bw.write(sb.toString());
		bw.write(sb2.reverse().toString());
		bw.flush();
		bw.close();
		br.close();
	}
}