package cote;

import java.io.*;
import java.util.*;

public class Boj_12852_1로만들기2_dp {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = read();
		int[] dp = new int[1000002];
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		dp[1] = 0;
		int[] order = new int[1000002];
		
		for(int i=1; i<=1000000; i++) {
			//1.
			if(3*i <= 1000000) {
				if(dp[3*i] >= dp[i]+1) {
					dp[3*i] = dp[i]+1;
					order[3*i] = i;
				}
			}
			//2.
			if(2*i <= 1000000) {
				if(dp[2*i] >= dp[i]+1) {
					dp[2*i] = dp[i]+1;
					order[2*i] = i;
				}
			}
			//3.
			if(dp[i+1] >= dp[i]+1) {
				dp[i+1] = dp[i]+1;
				order[i+1] = i;
			}
		}
		
		sb.append(dp[N]).append('\n')
		.append(N).append(' ');
		int cur = order[N];
		while(cur != 0) {
			sb.append(cur).append(' ');
			cur = order[cur];
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    boolean isNegative = n == 13;
	    if (isNegative) n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if (c == 13) System.in.read();
	    return isNegative ? ~n + 1 : n;
	}
}
