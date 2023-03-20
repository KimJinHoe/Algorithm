package cote;

import java.util.*;
import java.io.*;

public class Boj_1507_궁금한민호_Floyd {
	
	static int INF = 100000;
	static int[][] map;
	static int[][] origin;
	static boolean[][] checked;
	static int N;
	static int ans;
	static PriorityQueue<int[]> pque = new PriorityQueue<int[]>((o1, o2) -> o1[0] - o2[0]);

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		
		N = read();
		map = new int[N+1][N+1];
		origin = new int[N+1][N+1];
		checked = new boolean[N+1][N+1];
		
		//map 초기화
		for(int i=1; i<N+1; i++) {
			map[i][i] = INF;
			origin[i][i] = INF;
		}
		
		//입력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				int num = read();
				if(num == 0) continue;
				origin[i][j] = map[i][j] = num;
			}
		}
		
		//잠깐 origin을 temp map으로 쓰고, origin을 floyd
		for(int k=1; k<=N; k++) {
			floyd(k);
		}
		
		//불가능한 경우 -1 출력 (주어진 입력 map을 Floyd했을 때, 더 최적의 길이 완성되는 경우)
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] == origin[i][j]) continue;
				System.out.println(-1);
				return;
			}
		}
		
		//origin 초기화
		for(int i=1; i<N+1; i++) {
			Arrays.fill(origin[i], INF);
		}
		
		//경로 구하기1 (특정 정점으로 가는 길 중 최소인 값은 무조건 도로)
		for(int i=1; i<=N; i++) {
			int min = Integer.MAX_VALUE;
			int v = 0;
			for(int j=1; j<=N; j++) {
				if(i == j) continue;
				if(min <= map[i][j]) continue;
				min = map[i][j];
				v = j;
			}
			if(checked[i][v]) continue;
			origin[i][v] = origin[v][i] = min;
			checked[i][v] = checked[v][i] = true;
			ans += min;
		}
	
		
		//origin을 Floyd
		for(int k=1; k<=N; k++) {
			floyd(k);
		}
		
		//pickRoadCandidate(): origin과 입력받은 map을 비교해서 값이 다른 것 중 최소를 도로 후보에 넣고 pque에 넣어서 오름차순 정렬
		pickRoadCandidate();
		
		//pque에서 최솟값부터 도로 후보에서 조건 일치하면 도로로 결정
		while(!pque.isEmpty()) {
			int[] cur = pque.poll();
			if(cur[0] >= origin[cur[1]][cur[2]]) continue;
			origin[cur[1]][cur[2]] = origin[cur[2]][cur[1]] = cur[0];
			floyd(cur[1]);
			floyd(cur[2]);
			ans += cur[0];
			
			//도로 후보 뽑기 다시 실행
			pickRoadCandidate();
		}
		
		System.out.println(ans);
	}
	
	static void pickRoadCandidate() {
		for(int i=1; i<=N; i++) {
			int min = Integer.MAX_VALUE;
			int v = 0;
			for(int j=1; j<=N; j++) {
				if(map[i][j] == origin[i][j]) continue;
				if(min <= map[i][j]) continue;
				min = map[i][j];
				v = j;
			}
			if(min == Integer.MAX_VALUE) continue;
			if(checked[i][v]) continue;
			checked[i][v] = checked[v][i] = true;
			pque.add(new int[] {min, i , v});
		}
	}
	
	static void floyd(int k) {
		for(int i=1; i<=N; i++) {
			if(i == k) continue;
			for(int j=1; j<=N; j++) {
				if(i==j || j==k) continue;
				if(origin[i][j] > origin[i][k] + origin[k][j]) origin[i][j] = origin[i][k] + origin[k][j];
			}
		}
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
}
