package cote;
//2023.03.20 156ms 1등
import java.util.*;
import java.io.*;

public class Boj_2610_회의준비_Floyd {
	
	static int INF = 10000;
	static int N, M;
	static int[][] map;
	static int[] group;
	static int[][] represent;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = read();
		M = read();
		map = new int[N+1][N+1];
		group = new int[N+1];
		represent = new int[N+1][2];
		
		//map & represent 초기화
		for(int i=1; i<N+1; i++) {
			Arrays.fill(map[i], INF);
			represent[i][0] = INF;
		}
		
		//입력
		while(M-->0) {
			int a = read();
			int b = read();
			map[a][b] = map[b][a] = 1;
		}
		
		//경출목(Floyd)
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(i == k) continue;
				for(int j=1; j<=N; j++) {
					if(i==j || j==k) continue;
					if(map[i][j] > map[i][k] + map[k][j]) map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if(group[i] == 0) {
				group[i] = ++cnt;
				represent[cnt][0] = INF;
				represent[cnt][1] = i;
			}
			
			int groupNum = group[i];
			int max = Integer.MIN_VALUE;
			for(int j=1; j<=N; j++) {
				if(map[i][j] == INF) continue;
				if(group[j] == 0) group[j] = groupNum;
				if(max < map[i][j]) max = map[i][j];
			}
			
			if(represent[groupNum][0] > max) {
				represent[groupNum][0] = max;
				represent[groupNum][1] = i;
			}
		}
		
		//대표자 pque로 정렬
		PriorityQueue<Integer> pque = new PriorityQueue<Integer>();
		for(int i=1; i<=cnt; i++) {
			pque.add(represent[i][1]);
		}
		
		//출력
		sb.append(cnt).append('\n');
		while(!pque.isEmpty()) {
			sb.append(pque.poll()).append('\n');
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
