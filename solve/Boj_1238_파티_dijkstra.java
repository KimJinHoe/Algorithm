package cote;

import java.util.*;
import java.io.*;

public class Boj_1238_파티_dijkstra {
	
	static int N, M, X;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	static int[] result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		
		N = read();
		M = read();
		X = read();
		map = new int[N+1][N+1];
		result = new int[N+1];
		
		//입력
		while(M-->0) {
			map[read()][read()] = read();
		}
		
		//다익스트라 실행
		for(int i=1; i<=N; i++) {
			dijkstra(i, X);
		}
		
		//최대구하기
		int max = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			if(max < result[i]) max = result[i];
		}
		
		//출력
		System.out.println(max);
	}
	
	static void dijkstra(int start, int end) {
		int[] dist = new int[N+1];
		boolean[] visited = new boolean[N+1];
		PriorityQueue<int[]> pque = new PriorityQueue<int[]>((o1, o2) -> o1[1] - o2[1]);
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		pque.add(new int[] {start, dist[start]});
		
		while(!pque.isEmpty()) {
			int[] cur = pque.poll();
			
			//출발점이 X면 전체 dist 구함
			//아니면 도착지가 X면 종료
			if(start!=X && cur[0]==end) break;
			
			if(visited[cur[0]]) continue;
			visited[cur[0]] = true;
			
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				if(map[cur[0]][i] == 0) continue;
				if(dist[i] <= cur[1]+map[cur[0]][i]) continue;
				dist[i] = cur[1] + map[cur[0]][i];
				pque.offer(new int[] {i, dist[i]});
			}
		}
		
		//result에 결과값 추가
		if(start == X) {
			for(int i=1; i<=N; i++) {
				result[i] += dist[i];
			}
		} else {
			result[start] += dist[end];
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
