package Algorithm.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//최소신장트리 (KRUSKAL 알고리즘)
//1.최초 모든 간선을 가중치에 따라 오름차순으로 정렬
//2. 가중치가 가장 낮은 간선부터 선택하면서 트리를 증가시킴(Union)
//* 사이클이 존재하면 다음으로 가중치가 낮은 간선 선택
//n-1개의 간선이 선택될 때까지 2를 반복
//간선이 적으면 KRUSKAL 간선이 많으면 PRIM 알고리즘 쓰기 (KRUSCAL은 정렬에서 많은 시간 소모)
//Arrays.sort()를 사용하지 않고 PriorityQueue를 쓰면 훨씬 효율적임
/*
5 10
0 1 5
0 2 10
0 3 8
0 4 7
1 2 5
1 3 3
1 4 6
2 3 1
2 4 3
3 4 1

output==>10

7 11
0 1 32
0 2 31
0 5 60
0 6 51
1 2 21
2 4 46
2 6 25
3 4 34
3 5 18
4 5 40
4 6 51

output==>175
 */
public class ex7_Kruskal알고리즘_최소신장트리 {
	//from,to,weight를 저장할 클래스 생성 (배열로 해도 상관없음)
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
	static int[] parents;
	static Edge[] edgeList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[E];
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList); //간선비용의 오름차순 정렬. 중요!!! Arrays.sort()를 사용하지 않고 PriorityQueue를 쓰면 훨씬 효율적임
		makeSet();
		
		int result = 0;
		int cnt = 0;
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				if(++cnt == N-1) break;
			}
		}
		System.out.println(result);
	}
	
	//단위 집합 생성
	static void makeSet() {
		parents = new int[N];
		//자신의 부모노드를 자신의 값으로 세팅
		for(int i=0; i<N; i++) {
			parents[i] = i;
		}
	}
	//a의 집합 찾기 : a의 대표자 찾기
	static int findSet(int a) {
		if(a==parents[a]) return a;
		return parents[a] = findSet(parents[a]); //path compression
	}
	//a,b 두 집합 합치기
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
}
