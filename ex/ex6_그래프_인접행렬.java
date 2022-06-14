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
//그래프를 그리고 보면 답 이해하기 쉬움
public class ex6_그래프_인접행렬 {
	
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //정점 수
		int C = sc.nextInt(); //간선 수
		
		int[][] adjMatrix = new int[N][N]; //인접행렬
		for(int i=0; i<C; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[from][to] = adjMatrix[to][from] = 1;
		}
		
		for(int[] num : adjMatrix) {
			System.out.println(Arrays.toString(num));
		} 
		bfs(adjMatrix,0);
		dfs(adjMatrix,new boolean[N],0);
	}
	
	static void bfs(int[][] adjMatrix, int start) {
		
		Queue<Integer> que = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];
		
		que.offer(start);
		visited[start] = true;
		while(!que.isEmpty()) {
			int size = que.size();
			System.out.print("/");
			while(size --> 0) {
				int cur = que.poll();
				System.out.print(cur+" ");
				//cur 정점의 인접정점 처리(단, 방문하지 않은 인접정점만)
				for(int j=0; j<N; j++) {
					//j에 아직 방문을 안했고, cur에서 j로 갈 수 있는 간선이 있다면
					if(!visited[j] && adjMatrix[cur][j]!=0) {
						que.offer(j);
						visited[j] = true;
					}
				}
			}
		}
		System.out.println();
	}
	
	static void dfs(int[][] adjMatrix, boolean[] visited, int cur) {
		visited[cur] = true;
		System.out.print(cur+" ");
		
		//cur 정점의 인접정점 처리(단, 방문하지 않은 인접정점만)
		for(int j=0; j<N; j++) {
			//j에 아직 방문을 안했고, cur에서 j로 갈 수 있는 간선이 있다면
			if(!visited[j] && adjMatrix[cur][j]!=0) {
				dfs(adjMatrix, visited, j);
			}
		}
	}
}
