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

public class Boj_9205_맥주마시면서_bfs {
	
	static int[][] place;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테스트케이스 반복
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			//입력
			N = Integer.parseInt(br.readLine());
			place = new int[N+2][2]; //0:출발점, 중간:마트, N+1:목적지
			for(int i=0; i<N+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				place[i][0] = Integer.parseInt(st.nextToken());
				place[i][1] = Integer.parseInt(st.nextToken());				
			}

			//출력
			sb.append(bfs()).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static String bfs() {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[] visited = new boolean[N+2];
		que.offer(place[0]);
		visited[0] = true;
		
		while(!que.isEmpty()) {
			int size = que.size();
			
			while(size-->0) {
				int[] cur = que.poll();
				int x = cur[0];
				int y = cur[1];
				
				for(int i=1; i<N+2; i++) {
					if(visited[i]) continue;
					if(Math.abs(x-place[i][0])+Math.abs(y-place[i][1])>1000) continue;
					if(i==N+1) return "happy";
					que.offer(place[i]);
					visited[i] = true;
				}
			}
		}
		return "sad";
	}
}
