package cote;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_12851_숨바꼭질2_bfs {
	
	static int N, K;
	static int ans_time;
	static int ans_cnt;
	static boolean[] visited = new boolean[100001];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//실행
		bfs();
		
		//출력
		if(ans_time == 0) ans_cnt = 1;
		sb.append(ans_time).append('\n').append(ans_cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void bfs() {
		Queue<Integer> que = new LinkedList<Integer>();
		Queue<Integer> temp = new LinkedList<Integer>();
		que.offer(N);
		visited[N] = true;
		
		while(!que.isEmpty()) {
			if(visited[K]) return;
			int size = que.size();
			ans_time++;
			
			while(size-->0) {
				int x = que.poll();
				
				if(x-1 >= 0) {
					if(!visited[x-1]) {
						que.offer(x-1);
						temp.offer(x-1);
					}
					if(x-1 == K) ans_cnt++;
				}
				
				if(x+1 <= K) {
					if(!visited[x+1]) {
						que.offer(x+1);
						temp.offer(x+1);
					}
					if(x+1 == K) ans_cnt++;
				}
				
				if(x*2 <= K+1) {
					if(!visited[x*2]) {
						que.offer(x*2);
						temp.offer(x*2);
					}
					if(x*2 == K) ans_cnt++;
				}
			}
			while(!temp.isEmpty()) {
				visited[temp.poll()] = true;
			}
			
		}
	}
	
}