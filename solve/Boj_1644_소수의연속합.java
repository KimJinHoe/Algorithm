package cote;
//에라토스테네스의 체 + 투포인터
//2023.02.16 180ms 7등
import java.io.*;
import java.util.*;

public class Boj_1644_소수의연속합 {
	
	static int N;
	static boolean[] arr;
	static int[] prime;
//	static List<Integer> list = new ArrayList<Integer>();
	static int ans;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		arr = new boolean[N+1];
		prime = new int[N];
		
		//소수 표시 (false가 소수)
		arr[0] = true;
		arr[1] = true;
		int pcnt = 0;
		for(int i=2; i<=N; i++) {
			if(arr[i]) continue;
			prime[pcnt++] = i;
			for(int j=2*i; j<=N; j+=i) {
				arr[j] = true;
			}
		}
		
		int left = 0;
		int right = 0;
		if(N == 1) {
			System.out.println(0);
			return;
		}
		int val = prime[0];
		while(left<=right) {
			if(val < N) {
				right++;
				if(right >= pcnt) break;
				val += prime[right];
			}
			else if(val > N) {
				if(left < 0) break;
				val -= prime[left];
				left++;
			}
			else {
				ans++;
				right++;
				if(right >= pcnt) break;
				val += prime[right];
			}
		}
		
		System.out.println(ans);
	}
	
	static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if (c == 13) System.in.read();
	    return n;
	}
}
