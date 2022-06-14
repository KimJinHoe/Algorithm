package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17471_게리맨더링_sol_bfs_subset {
	
	static int N;
	static int[] people;

	static int[][] adj;
	static boolean[] selected;

	static int diff = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		//입력
		N = Integer.parseInt(br.readLine());
		selected = new boolean[N+1];
		adj = new int[N+1][N+1];
		people = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		for (int i = 1; i <= N; ++i) {
            people[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; ++j) {
				int num = Integer.parseInt(st.nextToken());
				adj[i][num] = 1;
			}
		}
		
		//실행
		subset(1,0,0);
		
		//출력
		diff = (diff==Integer.MAX_VALUE)?-1:diff;
		sb.append(diff);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	//부분집합 구하기
	private static void subset(int cnt , int sumA ,int sumB) { 
		if(cnt == N+1) {
			//공집합 제거
			if(sumA==0 || sumB==0) return;
			//선거구 연결확인 
			if(!check()) return;
			//최소인구차이 구하기
			diff = Math.min(diff, Math.abs(sumA-sumB));
			return;
		}
		
		selected[cnt] = true;   //A선거구
		subset(cnt+1, sumA+people[cnt],sumB );    
		selected[cnt] = false; //B선거구
		subset(cnt+1,sumA,sumB+people[cnt]);   
	}
	
	//각 선거구끼리 연결 되었는지 확인. bfs
	static boolean check() {  
		boolean[] visited = new boolean[N+1];
		
		//A팀 출발지를 고르자.
		int A = -1;
		for(int i = 1; i <= N; i++) {
			if(selected[i]) {
				A = i;
				break;
			}
		}
		
		//B팀 출발지를 고르자.
		int B = -1;
		for(int i = 1; i <= N; i++) {
			if(!selected[i]) {
				B = i;
				break;
			}
		}
		
		//각 팀의 선거구를 A와 B를 출발지 삼아서 탐색을 시작 .  bfs.
		Queue<Integer> queue = new LinkedList<>();
		
		//A탐색.
		//A => selected[i] 가  true 인 선거구
		queue.add(A);
		visited[A] = true;
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			//모든 선거구들 중에서 나와 같은 팀이면서. 나와 연결이 있으면서. 방문하지 않았다면 큐에 삽입
			for(int i = 1; i <= N; i++) {
				if(!selected[i]) continue; //같은 팀이 아니면 패스
				if(visited[i])	continue; //방문했으면 패스
				if(adj[node][i] == 0)continue; //인접하지 않았으면 패스
				visited[i] = true;
				queue.add(i);
			}
		}
		
		//B => selected[i] 가 false인 선거구
		//B의 출발주자를 큐에 넣고, visited에 트루 체크하고 while돌자
		queue.add(B);
		visited[B] = true;
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			//모든 선거구들 중에서 나와 같은 팀이면서. 나와 연결이 있으면서. 방문하지 않았다면 큐에 삽입
			for(int i = 1; i <= N; i++) {
				if(selected[i]) continue; //같은 팀이 아니면 패스
				if(visited[i]) continue; //방문했으면 패스
				if(adj[node][i] == 0)continue; //인접하지 않았으면 패스
				visited[i] = true;
				queue.add(i);
			}
		}
		
		//visited의 1부터 N까지 중에 false가 발견되면  return false
		for(int i = 1; i <= N; i++) {
			if(!visited[i])
				return false;
		}
		//조건 다 충족되면 true
		return true;
	}
}