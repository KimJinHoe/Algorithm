package cote;
//2023.01.31 720ms 2등
import java.io.*;
import java.util.*;
//Kruskal 알고리즘 + Pque
//Pque를 쓰면 훨씬 효율적임
public class Boj_1647_도시분할계획_KruskalPque {
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;
		
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static Edge[] edges;
	static int[] parents;
	
	public static void main(String[] args) throws Exception{
		//입력
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		int N = read();
		int M = read();
		
		edges = new Edge[M];
		parents = new int[N+1];
		
		for(int i=1; i<N+1; i++) {
			parents[i] = i;
		}
		
		PriorityQueue<Edge> pque = new PriorityQueue<Edge>();
		
		for(int i=0; i<M; i++) {
			pque.add(new Edge(read(), read(), read()));
		}
		
		//실행
		int cnt = 0;
		int weight = 0;
		while(!pque.isEmpty()) {
			Edge edge = pque.poll();
			if(union(edge.start, edge.end)) {
				weight += edge.weight; 
				if(++cnt == N-2) break;
			}
		}
		
		System.out.println(weight);
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aparent = find(a);
		int bparent = find(b);
		
		if(parents[a] == parents[b]) return false;
		
		parents[aparent] = bparent;
		return true;
	}
	
	static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    boolean isNegative = n == 13;
	    if (isNegative) n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if (c == 13) System.in.read();
	    return isNegative ? ~n + 1 : n;
	}
}
