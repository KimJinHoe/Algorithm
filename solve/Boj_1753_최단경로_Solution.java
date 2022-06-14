package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1753_최단경로_Solution {

    static class Node implements Comparable<Node>{
    	int to, weight;
    	
    	public Node(int to, int weight){
    		this.to = to;
    		this.weight = weight;
    	}
    	
    	@Override
    	public int compareTo(Node o) {
    		return weight - o.weight;
    	}
    }
	
    static int N;
    static int E;
    static int start;
    static ArrayList<ArrayList<Node>> list;
    static int[] dist;


    public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine())-1;
        
        list = new ArrayList<ArrayList<Node>>();
        for(int i=0; i<N; i++) {
        	list.add(new ArrayList<Node>());
        }
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 리스트에 그래프 정보를 초기화
        for(int i = 0 ; i < E; i++){
        	st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            // start에서 end로 가는 weight 가중치
            list.get(start).add(new Node(to, weight));
        }

        // 다익스트라 알고리즘
        dijkstra(start);
        
        // 출력 부분
        for(int i = 0; i < N; i++){
            if(dist[i] == Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(dist[i]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dijkstra(int start){
       PriorityQueue<Node> pque = new PriorityQueue<>();
       boolean[] visited = new boolean[N];
       pque.add(new Node(start, 0));  //start 까지의 비용은 0 => to=1,w=0인 노드 
       dist[start] = 0;//start 까지의 비용은 0

       while(!pque.isEmpty()){
           Node curNode = pque.poll();
           int cur = curNode.to;

           if(visited[cur]) continue; //이전에 방문 확정된 노드는 돌아 보지 않는다.
           visited[cur] = true;

           for(Node node : list.get(cur)){ //cur 와 연결된 노드를 모두 확인한다.
               if(dist[node.to] > dist[cur] + node.weight){
                   dist[node.to] = dist[cur] + node.weight; //연결된 정점으로 가는 비용 D에 작은비용으로 업데이트 
                   pque.add(new Node(node.to, dist[node.to]));
               }
           }
       }
    }

}