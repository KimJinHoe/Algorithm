package cote;
//2023.01.30 172ms 1등
import java.io.*;
import java.util.*;

public class Boj_1916_최소비용구하기_dijkstra {

	static int N, M;
	static int[][] map;
	static boolean[] visited;
	static int[] dist;
	static int start, end;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		//입력
		N = read();
		M = read();
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		dist = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}
		
		while(M-->0) {
			int v1 = read();
			int v2 = read();
			int num = read();
			if(map[v1][v2] > num) map[v1][v2] = num;
		}
		start = read();
		end = read();
		
		//실행
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		for(int c=1; c<=N; c++) {
			int min = Integer.MAX_VALUE;
			int cur = 0;
			for(int i=1; i<=N; i++) {
				if(min > dist[i] && !visited[i]) {
					min = dist[i];
					cur = i;
				}
			}
			if(cur == end) break;
			visited[cur] = true;
			
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				if(map[cur][i] == Integer.MAX_VALUE) continue;
				if(map[cur][i] + dist[cur] < dist[i]) dist[i] = map[cur][i] + dist[cur];
			}
		}
		System.out.println(dist[end]);
	}

	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13)
			System.in.read();
		return n;
	}
}
