package Algorithm.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

==> 8

4 
0 94 53 16 
79 0 24 18 
91 80 0 98 
26 51 92 0
==> 16


7
0   2   8   5   9  15  20
2   0   5   4   7  10  16
8   5   0   7   6   4  10
5   4   7   0  15   8   9
9   7   6  15   0  11  13
15 10   4   8  11   0   6
20 16  10   9  13   6   0

==> 14
 */

public class ex8_다익스트라PQ {

	private static class Vertex implements Comparable<Vertex>{
		int no;
		int totaldist;
		
		public Vertex(int no, int totaldist) {
			this.no = no;
			this.totaldist = totaldist;
		}
		@Override
		public int compareTo(Vertex o) {
			return this.totaldist-o.totaldist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); //정점 갯수
		int start = 0; //시작점 인덱스
		int end =  N-1; //도착점 인덱스
		
		int[][] arr = new int[N][N];
		int[] dist = new int[N];
		boolean[] visited = new boolean[N];
		
		for(int i=0; i<N; ++i){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j){
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Vertex> pque = new PriorityQueue<Vertex>();
		dist[start] = 0;
		pque.offer(new Vertex(start,dist[start]));
		
		Vertex cur = null;
        while(!pque.isEmpty()){
			
			//a단계 : 방문하지 않은 정점들 중 최소가중치의 정점 선택
        	cur = pque.poll();
        	if(visited[cur.no])continue;
        	
			visited[cur.no] = true; // 선택 정점 방문 처리
			if(cur.no == end) break; // 선택 정점이 도착정점이면 탈출.
			
			//b단계: current정점을 경유지로 하여 갈수 있는 다른 방문하지 않은 정점들에 대한 처리
			for(int j=0; j<N; ++j){
				if(!visited[j] && arr[cur.no][j] != 0
						&&  dist[j] >cur.totaldist+arr[cur.no][j]){
					dist[j] = cur.totaldist+arr[cur.no][j];
					pque.offer(new Vertex(j,dist[j]));
				}
			}
		}
		System.out.println(dist[end]);
	}

}