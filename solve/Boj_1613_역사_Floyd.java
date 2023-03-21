package cote;
//2023.03.21 364ms 1등
import java.util.*;
import java.io.*;

public class Boj_1613_역사_Floyd {
	
	static int N, M, S;
	static int[][] map;
	static int INF = 1000;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = read();
		M = read();
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(map[i], INF);
		}
		
		while(M-->0) {
			map[read()][read()] = 1;
		}
		
		//플로이드
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(k == i) continue;
				for(int j=1; j<=N; j++) {
					if(k==j || i==j) continue;
					if(map[i][j] > map[i][k]+map[k][j]) map[i][j] = map[i][k]+map[k][j];
				}
			}
		}
		
		S = read();
		
		while(S-->0) {
			int i = read();
			int j = read();
			int num = 0;
			if(map[i][j] != INF) num = -1;
			else if(map[j][i] != INF) num = 1;
			
			sb.append(num).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
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