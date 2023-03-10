package cote;
//Floyd로 풀었지만 모범 답안은 벨만-포드 반복으로 음수 사이클 찾기
//벨만포드 설명 잘되어있음https://developer-davii.tistory.com/89
import java.io.*;
import java.util.*;

public class Boj_1865_웜홀_Floyd {
	
	static int N, M, W;
	static int[][] map;
	static int INF = 5000001;
	
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = read();
		tc:while(TC-->0) {
			N = read(); //지점
			M = read(); //도로
			W = read(); //웜홀
			map = new int[N+1][N+1];
			for(int i=0; i<=N; i++) {
				Arrays.fill(map[i], INF);
			}
			
			while(M-->0) {
				int S = read();
				int E = read();
				int T = read();
				map[S][E] = map[E][S] = Math.min(map[S][E], T);
			}
			
			while(W-->0) {
				int S = read();
				int E = read();
				int T = -read();
				map[S][E] = Math.min(map[S][E], T);
			}
			
			//경출목
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(map[i][j] > map[i][k] + map[k][j]) map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
			
			for(int i=1; i<=N; i++) {
				if(map[i][i] >= 0) continue;
				sb.append("YES").append('\n');;
				continue tc;
			}
			sb.append("NO").append('\n');
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