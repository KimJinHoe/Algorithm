package cote;
//120ms
import java.io.*;

public class Boj_9095_123더하기_dp {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[N+1];
			dp[1] = 1;
			if(N >= 2) dp[2] = 2;
			if(N >= 3) dp[3] = 4;
			for(int j=4; j<=N; j++) {
				dp[j] = dp[j-1] + dp[j-2] + dp[j-3];
			}
			sb.append(dp[N]).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}