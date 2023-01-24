package cote;

import java.io.*;
import java.util.*;

public class Boj_11054_바이토닉부분수열_dp {
	
	static int N;
	static int[] arr;
	static int[] dp;
	static int[] rdp;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		rdp = new int[N];
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//dp 실행
		for(int i=0; i<N; i++) {
			//정방향(증가)
			int num = arr[i];
			for(int k=0; k<i; k++) {
				if(num <= arr[k]) continue;
				if(dp[i] >= dp[k]+1) continue;
				dp[i] = dp[k]+1;
			}
			
			//역방향(감소)
			int j = N-i-1;
			int rnum = arr[j];
			for(int k=N-1; k>j; k--) {
				if(rnum <= arr[k]) continue;
				if(rdp[j] >= rdp[k]+1) continue;
				rdp[j] = rdp[k]+1;
			}
		}
		
		//결과값 찾기
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i] + rdp[i] + 1);
		}
		
		//출력
		System.out.println(max);
	}
}