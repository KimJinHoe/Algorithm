package cote;
//2023.03.19 228ms 1등
import java.io.*;
import java.util.*;

public class Boj_1719_택배_Floyd {
	
	static int INF = 100000;
	
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = read();
		int M = read();
		int[][] map = new int[N+1][N+1];
		int[][] result = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(map[i], INF);
		}
		
		while(M-->0) {
			int s = read();
			int e = read();
			map[s][e] = map[e][s] = read();
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(k == i) continue;
				for(int j=1; j<=N; j++) {
					if(k==j || i==j) continue;
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
						result[i][j] = result[i][k]==0 ? k : result[i][k];
					}
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) {
					sb.append('-').append(' ');
					continue;
				}
				sb.append(result[i][j]==0 ? j : result[i][j]).append(' ');
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
    }
    
	static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    boolean isNegative = n == 13;
	    if (isNegative) n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if(c == 13) System.in.read();
	    return isNegative ? ~n + 1 : n;
	}

}