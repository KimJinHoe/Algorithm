package Algorithm.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//최단경로 알고리즘. 음의 가중치가 없어야 함. Prim과 거의 비슷.
public class ex8_다익스트라알고리즘 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] adjMatrix = new int[N][N];
		int start = 0;
		//int end = N-1;
		//배열입력
		StringTokenizer st = null;
		for (int c = 0; c < N; c++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) {
				adjMatrix[c][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] visited = new boolean[N]; // 최소비용 확정여부
		int[] dist = new int[N]; // 출발지에서 자신으로 오는 최소비용
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0; // 시작점 0으로
		
		for (int c = 0; c < N; c++) {
			// 단계1 : 최소비용이 확정되지 않은 정점중 최소비용의 정점 선택
			int min=Integer.MAX_VALUE;
			int cur=0;
			
			for (int i = 0; i < N; i++) {
				if(!visited[i] && dist[i]<min) {
					min = dist[i];
					cur = i;
				}
			}
			
			visited[cur] = true;
			//if(cur==end) break;
			
			// 단계2 :  선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른정점의 최소비용을 고려
			for (int i = 0; i < N; i++) {
				//방문하지 않았고, 경유지에서 목적지까지 간선이 있으며, 경유해서 가는게 더 작은 비용이 들 때
				if (!visited[i] && adjMatrix[cur][i] != 0 && dist[i] > dist[cur] + adjMatrix[cur][i]) {
					dist[i] =  dist[cur] + adjMatrix[cur][i];
//					System.out.println(Arrays.toString(dist));
//					System.out.println("-----------------------");
				}
			}
		}
		//System.out.println(dist[end]);
		System.out.println("start지점으로 부터 다른 정점까지 " + Arrays.toString(dist)); 
	}
}

/*
============= 인접행렬 테스트케이스 

5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

output==> 8
[0, 2, 2, 5, 8]

6
0 3 5 0 0 0
0 0 2 6 0 0
0 1 0 4 6 0
0 0 0 0 2 3
3 0 0 0 0 6
0 0 0 0 0 0

output==> 12


10
0 4 6 0 0 0 0 0 0 0
0 0 0 9 8 0 0 0 0 0
0 3 0 0 2 3 0 0 0 0
0 0 0 0 0 0 6 0 0 0
0 0 0 2 0 1 3 7 0 0 
0 0 0 0 0 0 0 4 8 0
0 0 0 0 0 0 0 0 0 13
0 0 0 0 0 0 0 0 0 9
0 0 0 0 0 0 0 0 0 4
0 0 0 0 0 0 0 0 0 0


output ==> 21

============= 인접리스트 테스트케이스 
10 17
0 1 4
0 2 6
1 3 9
1 4 8
2 1 3
2 4 2
2 5 3
3 6 6
4 3 2
4 5 1
4 6 3
4 7 7
5 7 4
5 8 8
6 9 13
7 9 9
8 9 4

output ==> 21
*/