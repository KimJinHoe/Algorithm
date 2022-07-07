package cote;
//2등. 208ms.
import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_12851_숨바꼭질4_bfs {
	
	static int N, K;
	static int ans_time;
	static int[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if(N >= K) {
			sb.append(N-K).append('\n');
			for(int i=N; i>=K; i--) {
				sb.append(i).append(' ');
			}
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			br.close();
			return;
		}
		if(N >= K) visited = new int[N+2];
		else visited = new int[K+2];
		Arrays.fill(visited, -1);
		
		//실행
		bfs();
		
		//출력
		sb.append(ans_time).append('\n');
		
		Deque<Integer> que = new ArrayDeque<Integer>();
		int idx = K;
		while(idx != N) {
			que.addFirst(visited[idx]);
			idx = visited[idx];
		}
		while(!que.isEmpty()) {
			sb.append(que.poll()).append(' ');
		}
		sb.append(K);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		que.offer(N);
		visited[N] = -2;
		
		while(!que.isEmpty()) {
			int size = que.size();
			ans_time++;
			
			while(size-->0) {
				int x = que.poll();
				
				if(x-1 >= 0 && visited[x-1] == -1) {
					visited[x-1] = x;
					if(x-1 == K) return;
					que.offer(x-1);
				}
				
				if(x+1 <= K+1 && visited[x+1] == -1) {
					visited[x+1] = x;
					if(x+1 == K) return;
					que.offer(x+1);
				}
				
				if(x*2 <= K+1 && visited[x*2] == -1) {
					visited[x*2] = x;
					if(x*2 == K) return;
					que.offer(x*2);
				}
			}
			
		}
	}
	
}