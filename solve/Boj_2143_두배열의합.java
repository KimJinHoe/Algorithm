package cote;

import java.io.*;
import java.util.*;

public class Boj_2143_두배열의합 {
	
	static int T;
	static int N, M;
	static int[] arr;
	static int[] brr;
	static Map<Integer, Integer> amap = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		
		//입력
		T = read();
		N = read();
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = read();
		}
		M = read();
		brr = new int[M];
		for(int i=0; i<M; i++) {
			brr[i] = read();
		}
		
		//amap 생성
		for(int i=0; i<N; i++) {
			int num = 0;
			for(int j=i; j<N; j++) {
				num += arr[j];
				Integer cnt = amap.get(num);
				if(cnt == null) amap.put(num, 1);
				else amap.replace(num, cnt+1);
			}
		}
		
		//실행
		long ans = 0;
		for(int i=0; i<M; i++) {
			int num = T;
			for(int j=i; j<M; j++) {
				num -= brr[j];
				Integer cnt = amap.get(num);
				if(cnt != null) ans += cnt;
			}
		}
		
		System.out.println(ans);
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
