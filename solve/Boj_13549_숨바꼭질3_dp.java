package cote;
//dp. 6등. 144ms.
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_13549_숨바꼭질3_dp {
	
	static int N, K;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] dp = new int[100002];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[N] = 0;
		dp[0] = N;
		
		//실행
		if(N >= K) {
			ans = N-K;
		}
		else {
			for(int i=1; i<=K; i++) {
				int val = Math.min(Math.abs(N-i), dp[i]);
				int idx = i;
				while(idx <= 100000) {
					if(dp[idx-1] > val+1) dp[idx-1] = val+1;
					if(dp[idx] > val) dp[idx] = val;		
					if(dp[idx+1] > val+1) dp[idx+1] = val+1;
					idx *= 2;
				}
			}
			
			for(int i=N+1; i<=K; i++) {
				int val = Math.min(dp[i-1], dp[i+1])+1;
				if(i%2 == 0) val = Math.min(val, dp[i/2]);
				if(dp[i] > val) dp[i] = val;
			}
			ans = dp[K];
		}
		
		//출력
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
	
