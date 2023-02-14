package cote;
//2023.02.14 684ms 2등
//아이디어 정리
//3개의 좌표를 3개의 pque로 정렬 (3*nlogn)
//3개의 pque에서 인접한 행성끼리의 터널 N-1개 씩, 총 3(N-1)개의 터널을 Edges에 담아 정렬 (3n*log3n)
//3n개의 간선을 기준으로 크루스칼 알고리즘 실행
import java.io.*;
import java.util.*;

public class Boj_2887_행성터널_크루스칼 {
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int N;
	static int[][] planets;
	static int[] parents;
	static PriorityQueue<int[]>[] pques = new PriorityQueue[3]; 
	static PriorityQueue<Edge> Edges = new PriorityQueue<Edge>();
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		planets = new int[N][3];
		parents = new int[N];
		
		//pque 정렬기준 정의 (람다 표현식 사용하면 깔끔하지만 성능은 조금 낮아지는 듯(?))
		for(int i=0; i<3; i++) {
//			pques[i] = new PriorityQueue<int[]>((int[] o1, int[] o2) -> o1[1] - o2[1]);
			pques[i] = new PriorityQueue<int[]>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
			});
		}
		
		//입력 및 좌표별 pque 정렬 + parents 초기화
		for(int i=0; i<N; i++) {
			parents[i] = i;
			for(int j=0; j<3; j++) {
				planets[i][j] = read(); 
				pques[j].add(new int[] {i, planets[i][j]});
			}
		}
		
		//3N개 간선 정렬
		for(int i=0; i<3; i++) {
			PriorityQueue<int[]> pque = pques[i];
			int size = N-1;
			while(size-->0) {
				int[] cur = pque.poll();
				int[] next = pque.peek();
				Edges.add(new Edge(cur[0], next[0], Math.abs(cur[1]-next[1])));
			}
		}
		
		//실행
		long ans = 0;
		int cnt = 0;
		int size = Edges.size();
		while(size-->0) {
			Edge edge = Edges.poll();
			if(union(edge.from, edge.to)) {
				ans += edge.weight;
				if(++cnt == N-1) break;
			}
		}
		
		System.out.println(ans);
	}
	
	static int findSet(int a) {
		if(a==parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
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
