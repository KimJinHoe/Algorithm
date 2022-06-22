package Algorithm.solve;
//기존풀이 Prim의 adjList -> 1728ms. 효율성 안좋음
//neobird님의 코드 참고 및 재풀이. 다익스트라PQ
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Boj_10282_해킹_pq_Solution {
	
	static class Node implements Comparable<Node> {
		int to;
		int val;

		public Node(int to, int val) {
			this.to = to;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}

	}
	
	static int N;
	static List<Node>[] map;
	static int ans;
	static int time;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테케 반복
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
        	//전역변수 초기화 및 입력 받기
        	ans = 0;
        	time = Integer.MIN_VALUE;
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	int start = Integer.parseInt(st.nextToken());
        	map = new ArrayList[N+1];
        	for(int i=1; i<N+1; i++) {
        		map[i] = new ArrayList<Node>();
        	}
        	
        	//map 입력받기
        	for(int i=0; i<M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int to = Integer.parseInt(st.nextToken());
        		int from = Integer.parseInt(st.nextToken());
        		int val = Integer.parseInt(st.nextToken());
        		map[from].add(new Node(to, val));
        	}
        	
        	//실행
    		run(start);
    		
    		//출력
            sb.append(ans).append(' ').append(time).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void run(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] minEdge = new int[N+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[start] = 0;
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (minEdge[cur.to] < cur.val) continue; //기존의 값이 더 작으면 패스

			for (Node next : map[cur.to]) {
				if (minEdge[next.to] > cur.val + next.val) {
					minEdge[next.to] = cur.val + next.val;
					pq.add(new Node(next.to, cur.val + next.val));
				}
			}
		}
		
		for(int i=1; i<N+1; i++) {
			if(minEdge[i] != Integer.MAX_VALUE) {
				ans++; //감염된 컴퓨터 카운트.
				time = Math.max(time, minEdge[i]);
			}
		}
	}
}