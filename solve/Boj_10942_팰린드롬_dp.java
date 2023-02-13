package cote;
//2023.02.13 408ms 2등
import java.io.*;
import java.util.*;

public class Boj_10942_팰린드롬_dp {
	
	static int N, M;
	static int[] arr;
	static boolean[][] dp;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//입력
		N = read();
		arr = new int[N];
		dp = new boolean[N][N];
		for(int i=0; i<N; i++) {
			arr[i] = read();
		}
		
		//실행(dp)
		//1. 홀수 개수 dp (S~E의 원소가 1,3,5...)
		//i는 수열에서 가운데 숫자가 될 인덱스
		for(int i=0; i<N; i++) {
			//[0 1 (2) 3 4 5] 에서 가운데 숫자가 2일 때, 왼쪽에는 2칸 오른쪽에는 3칸까지 확장 가능. len = 2
			int len = Math.min(i, N-1-i);
			for(int j=0; j<=len; j++) {
				int start = i-j;
				int end = i+j;
				if(arr[start] == arr[end]) {
                    dp[start][end] = true;
                    continue;
                }
                //다른 경우 뒤의 경우도 계속 false
                break;
			}
		}
		
		//2. 짝수 개수 dp (S~E의 원소가 2,4,6...)
		for(int i=0; i<N-1; i++) {
			//[0 1 (2 3) 4 5 6] 에서 가운데 숫자가 2와 3일 때, 왼쪽에는 2칸 오른쪽에는 3칸까지 확장 가능. len = 2
			int len = Math.min(i, N-2-i);
			for(int j=0; j<=len; j++) {
				int start = i-j;
				int end = i+1+j;
				if(arr[start] == arr[end]) {
                    dp[start][end] = true;
                    continue;
                }
                //다른 경우 뒤의 경우도 계속 false
                break;
			}
		}
		
		//출력
		M = read();
		while(M-->0) {
			sb.append(dp[read()-1][read()-1]?1:0).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
