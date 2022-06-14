package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5643_키순서_bfs_인접행렬배열 {
	
	static int N, M;
	static int[][] adj;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adj = new int[N][N];
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num1= Integer.parseInt(st.nextToken())-1;
				int num2= Integer.parseInt(st.nextToken())-1;
				adj[num1][num2] = 1;
			}
			ans=0;
			run();
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run() {
		label:for(int i=0; i<N; i++) {
			boolean[] visited = new boolean[N];
			Queue<Integer> que = new LinkedList<Integer>();
			que.offer(i);
			visited[i] = true;
			//인접한 큰 아이만 찾기
			while(!que.isEmpty()) {
				int size = que.size();
				while(size-->0) {
					int num = que.poll();
					
					for(int j=0; j<N; j++) {
						//방문했으면 패스
						if(visited[j]) continue;
						//인접한 키 높은 아이 있으면
						if(adj[num][j]==1) {
							que.offer(j);
							visited[j] = true;
						}
					}
				}
			}
			
			//인접한 작은 아이만 찾기
			que.offer(i);
			while(!que.isEmpty()) {
				int size = que.size();
				while(size-->0) {
					int num = que.poll();
					
					for(int j=0; j<N; j++) {
						//방문했으면 패스
						if(visited[j]) continue;
						//인접한 키 작은 아이 있으면
						if(adj[j][num]==1) {
							que.offer(j);
							visited[j] = true;
						}
					}
				}
			}
			for(int j=0; j<N; j++) {
				//방문 안 한 아이있으면
				if(!visited[j]) continue label;
			}
			ans++;
		}
	}
}
