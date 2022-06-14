package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1753_최단경로 {
	
	static class Node {
		int vertex;
		int weight;
		Node link;
		
		public Node(int vertex, int weight, Node link) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
		
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine())-1;
		Node[] nodes = new Node[E];
		
		//간선 정보 입력
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			nodes[from] = new Node(to, weight, nodes[from]);
		}
		
		int[] dist = new int[V];
		boolean[] result = new boolean[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		int cur = 0;
		//출발지에서 정점들로의 최솟값 구하기
		for(int i=0; i<V; i++) {
			int min = Integer.MAX_VALUE;
			//최소비용이 확정되지 않은 정점에서 최소비용의 정점을 선택
			for(int j=0; j<V; j++) {
				if(!result[j] && dist[j] < min) {
					min = dist[j];
					cur = j;
				}
			}
			
			result[cur] = true;
			
			for(Node temp = nodes[cur]; temp!=null; temp=temp.link) {
				if(!result[temp.vertex] && dist[temp.vertex] > min + temp.weight) {
					dist[temp.vertex] = min + temp.weight;
				}
			}
			//출력
			if(dist[i] == Integer.MAX_VALUE) sb.append("INF").append('\n');
			else sb.append(dist[i]).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
