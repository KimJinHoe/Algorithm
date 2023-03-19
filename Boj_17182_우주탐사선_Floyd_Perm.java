package cote;
//2023.03.20 132ms 2등 
import java.util.*;
import java.io.*;

public class Boj_17182_우주탐사선_Floyd_Perm {
	
	static int N;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int S;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		S = read();
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = read();
			}
		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				if(k == i) continue;
				for(int j=0; j<N; j++) {
					if(k==j || i==j) continue;
					if(map[i][j] > map[i][k] + map[k][j]) map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		
		perm(1, 1<<S, S, 0);
		
		System.out.println(min);
	}
	
	static void perm(int cnt, int flag, int from, int sum) {
		if(sum >= min) return;
		
		if(cnt == N) {
			if(min > sum) min = sum;
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((flag&1<<i) != 0) continue;
			perm(cnt+1, flag|1<<i, i, sum+map[from][i]);
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
