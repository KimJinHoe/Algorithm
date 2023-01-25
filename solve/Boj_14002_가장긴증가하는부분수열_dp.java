package cote;

import java.io.*;
import java.util.*;

public class Boj_14002_가장긴증가하는부분수열_dp {
	
	static int N;
	static int[] arr;
	static int[] dp;
	static int[] order;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		order = new int[N];
		Arrays.fill(order, -1);
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//dp 실행
		for(int i=0; i<N; i++) {
			int num = arr[i];
			for(int k=0; k<i; k++) {
				if(num <= arr[k]) continue;
				if(dp[i] >= dp[k]+1) continue;
				dp[i] = dp[k]+1;
				order[i] = k;
			}
		}
		//결과값 찾기
		int max = Integer.MIN_VALUE;
		int idx = 0;
		for(int i=0; i<N; i++) {
			if(max < dp[i]) {
				max = dp[i];
				idx = i;
			}
		}
		//출력
		sb.append(max+1).append('\n');
		int tmp = idx;
		while(tmp != -1) {
			stack.add(order[tmp]);
			tmp = order[tmp];
		}
		
		stack.pop();
		while(!stack.isEmpty()) {
			sb.append(arr[stack.pop()]).append(' ');
		}
		sb.append(arr[idx]).append(' ');
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
}