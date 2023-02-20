package cote;
//2023.02.20 632ms 1등 (59,872KB)
//2등 대비 시간 효율 1.5배, 메모리 5배 좋음. 2등: 940ms (301,264KB)
import java.io.*;
import java.util.*;

public class Boj_9466_텀프로젝트_유니온 {
	
	static int[] arr;
	static int[] parents;
	static boolean[] checked;
	static int ans;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = read();
		while(T-->0) {
			int N = read();
			arr = new int[N+1];
			parents = new int[N+1];
			checked = new boolean[N+1];
			ans = N;
			
			for(int i=1; i<=N; i++) {
				arr[i] = read();
				parents[i] = i;
			}
			
			for(int i=1; i<=N; i++) {
				union(i, arr[i]);
			}
			
			sb.append(ans).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static void check(int a, int cnt) {
		if(parents[a] == a) {
			ans -= cnt;
			return;
		}
		check(arr[a], cnt+1);
	}
	
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) {
			check(b, 1);
			return;
		}
		
		parents[a] = bRoot;
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
