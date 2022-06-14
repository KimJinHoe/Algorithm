package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5643_키순서_bfs_이중리스트 {
	
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
			List<List<Integer>> fromList = new ArrayList<List<Integer>>(); //인접한 작은 애들
			List<List<Integer>> toList = new ArrayList<List<Integer>>(); //인접한 큰 애들
			//N개 만큼 할당
			for(int i=0; i<N; i++) {
				fromList.add(new ArrayList<Integer>());
				toList.add(new ArrayList<Integer>());
			}
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num1= Integer.parseInt(st.nextToken())-1;
				int num2= Integer.parseInt(st.nextToken())-1;
				fromList.get(num2).add(num1);
				toList.get(num1).add(num2);
			}
			ans=0;
			run(fromList, toList);
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run(List<List<Integer>> fromList, List<List<Integer>> toList) {
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
					int listSize = toList.get(num).size();
					for(int j=0; j<listSize; j++) {
						int to = toList.get(num).get(j);
						//방문했으면 패스
						if(visited[to]) continue;
						//아니면 방문처리하고 큐 삽입
						visited[to] = true;
						que.offer(to);
					}
				}
			}
			
			//인접한 작은 아이만 찾기
			que.offer(i);
			while(!que.isEmpty()) {
				int size = que.size();
				while(size-->0) {
					int num = que.poll();
					int listSize = fromList.get(num).size();
					for(int j=0; j<listSize; j++) {
						int from = fromList.get(num).get(j);
						//방문했으면 패스
						if(visited[from]) continue;
						//아니면 방문처리하고 큐 삽입
						visited[from] = true;
						que.offer(from);
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
