package Algorithm.ex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
7
8
0 1
0 2
1 3
1 4
2 4
3 5
4 5
5 6
 */
public class ex6_그래프_인접리스트 {

	static class Node {
		int v;
		Node link;

		public Node(int v, Node link) {
			this.v = v;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", link=" + link + "]";
		}

	}

	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 정점 수
		int C = sc.nextInt(); // 간선 수

		Node[] adjList = new Node[N]; // 정점 수 크기로 생성
		for (int i = 0; i < C; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			// 무향이므로 간선 하나로 양방향 처리
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}

		for (Node head : adjList) {
			System.out.println(head);
		}

		bfs(adjList, 0);
		dfs(adjList, new boolean[N], 0);
	}

	static void bfs(Node[] adjList, int start) {

		Queue<Integer> que = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];

		que.offer(start);
		visited[start] = true;
		while (!que.isEmpty()) {
			int size = que.size();
			System.out.print("/");
			while(size-->0) {
				int cur = que.poll();
				System.out.print(cur + " ");
				// cur 정점의 인접정점 처리(단, 방문하지 않은 인접정점만)
				for (Node temp = adjList[cur]; temp != null; temp = temp.link) {
					// j에 아직 방문을 안했다면 동작
					if (!visited[temp.v]) {
						que.offer(temp.v);
						visited[temp.v] = true;
					}
				}
			}
		}
		System.out.println();
	}

	static void dfs(Node[] adjList, boolean[] visited, int cur) {
		visited[cur] = true;
		System.out.print(cur + " ");

		// cur 정점의 인접정점 처리(단, 방문하지 않은 인접정점만)
		for (Node temp = adjList[cur]; temp != null ; temp= temp.link) {
			// j에 아직 방문을 안했고, cur에서 j로 갈 수 있는 간선이 있다면
			if (!visited[temp.v]) {
				dfs(adjList, visited, temp.v);
			}
		}
	}

}
