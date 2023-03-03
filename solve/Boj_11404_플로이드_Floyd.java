package cote;
//2023.03.03 200ms 4등 
import java.util.*;
import java.io.*;

public class Boj_11404_플로이드_Floyd {
	
	static int INF = 10000000;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		int M = read();
		
		int[][] map = new int[N+1][N+1];
		for(int i=0; i<=N; i++) {
			Arrays.fill(map[i], INF);
		}
		
		for(int i=0; i<M; i++) {
			int s = read();
			int e = read();
			if(map[s][e] == INF) map[s][e] = read();
			else map[s][e] = Math.min(map[s][e], read());
		}

		//경출목
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(k == i) continue;
				for(int j=1; j<=N; j++) {
					if(k==j || i==j) continue;
					if(map[i][j] > map[i][k]+map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
					}
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				sb.append(map[i][j]==INF?0:map[i][j]);
				if(j != N) sb.append(' ');
			}
			sb.append('\n');
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
