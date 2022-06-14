package Algorithm.ex;
/*
5
0 5 10 8 7 
5 0 5 3 6 
10 5 0 1 3 
8 3 1 0 1 
7 6 3 1 0

output==>10

7
0 32 31 0 0 60 51
32 0 21 0 0 0 0
31 21 0 0 46 0 25
0 0 0 0 34 18 0
0 0 46 34 0 40 51
60 0 0 18 40 0 0
51 0 25 0 51 0 0

output==>175
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//인접행렬 데이터가 들어옴. 그리고 간선이 많으면 KRUSKAL알고리즘은 정렬때문에 비효율적이니 PRIM을 쓰자.
//물론 간선 데이터가 들어와도 인접행렬 데이터로 변환해서 사용가능함.
public class ex7_Prim알고리즘_최소신장트리 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] adjMatrix = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		boolean[] visited = new boolean[N]; //신장트리에 선택된 여부
		int[] minEdge = new int[N]; //타정점에서 자신으로의 간선비용 중 최소비용
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;
		
		for(int c=0; c<N; c++) { //N개의 정점을 모두 연결
			// 신장트리에 연결되지 않은 정점 중 가장 유리한 비용의 정점을 선택
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			
			for(int i=0; i<N; i++) {
				if(!visited[i] && minEdge[i]<min) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			//선택된 정점을 신장트리에 포함시킴
			visited[minVertex] = true;
			result += min;
			
			// 선택된 정점 기준으로 신장트리에 포함되지 않은 다른 정점으로의 간선비용을 따져보고 최소값 갱신
			for (int i=0; i<N; i++) {
				//방문하지 않았고, 인접해서 간선이 있으며, minEdge보다 작으면
				if(!visited[i] && adjMatrix[minVertex][i]!=0 && adjMatrix[minVertex][i]<minEdge[i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}
		System.out.println(result);
	}
}
