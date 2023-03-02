package cote;
//2023.03.03 2등~
import java.util.*;
import java.io.*;

public class Boj_20040_사이클게임_union {
	
	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		M = read();
		
		parents = new int[N];
		for(int i=0; i<N; i++) {
			parents[i] = i;
		}
		
		for(int i=1; i<=M; i++) {
			if(union(read(), read())) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return true;
		parents[aRoot] = bRoot;
		return false;
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c=System.in.read())>32) n = (n<<3) + (n<<1) + (c&15);
		if(c==13) System.in.read();
		return negative ? ~n + 1 : n;
	}
}
