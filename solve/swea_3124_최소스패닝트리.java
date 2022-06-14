package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_3124_최소스패닝트리 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int[][] adjMatrix = new int[N][N];
			int[] minEdge = new int[N]; //타정점에서 자신으로의 간선비용 중 최소비용
			boolean[] visited = new boolean[N]; //신장트리에 선택된 여부
			for(int n=0; n<E; n++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken())-1;
				int j = Integer.parseInt(st.nextToken())-1;
				int w = Integer.parseInt(st.nextToken());
				adjMatrix[i][j] = w;
				adjMatrix[j][i] = w;
			}
			Arrays.fill(minEdge, Integer.MAX_VALUE);
			int result = 0;
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
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
}
