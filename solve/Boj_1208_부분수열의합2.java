package cote;

import java.util.*;
import java.io.*;

public class Boj_1208_부분수열의합2 {
	
	static int[] arr, brr;
	static Set<Integer> set1, set2;
	static int[] cntArr = new int[4000001];
	static int[] cntBrr = new int[4000001];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		int N = read();
		int S = read();
		PriorityQueue<Integer> pque = new PriorityQueue<Integer>();
		arr = new int[N/2];
		brr = new int[N-N/2];
		set1 = new HashSet<Integer>();
		set2 = new HashSet<Integer>();
		
		for(int i=0; i<N; i++) {
			pque.add(read());
		}
		for(int i=0; i<N/2; i++) {
			arr[i] = pque.poll();
		}
		for(int i=0; i<N-N/2; i++) {
			brr[i] = pque.poll();
		}
		
		dfs(0, N/2, 0, 0, 1); //arr
		dfs(0, N-N/2, 0, 0, 2); //brr
		
		long ans = 0;
		for(Integer num: set1) {
			if(num==S) ans += cntArr[num+2000000];
			if(set2.contains(S-num)) ans += (long)cntArr[num+2000000]*cntBrr[S-num+2000000];
		}
		for(Integer num: set2) {
			if(num==S) ans += cntBrr[num+2000000];
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int cnt, int R, int sum, int pick, int funcType) {
		if(cnt==R) {
			if(pick == 0) return;
			if(funcType == 1) {
				set1.add(sum);
				cntArr[sum+2000000]++;
			}
			else {
				set2.add(sum);
				cntBrr[sum+2000000]++;
			}
			return;
		}
		//arr
		if(funcType == 1) {
			dfs(cnt+1, R, sum+arr[cnt], pick+1, funcType);
			dfs(cnt+1, R, sum, pick, funcType);
		}
		//brr
		else {
			dfs(cnt+1, R, sum+brr[cnt], pick+1, funcType);
			dfs(cnt+1, R, sum, pick, funcType);
		}
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
}
