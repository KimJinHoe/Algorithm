package Algorithm.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_15686_아기상어solution {
	
	
	static int[] dy = {-1, 1, 0, 0}; //상하좌우
	static int[] dx = {0, 0, -1, 1};
	
	static int[][] map; //물고기 정보들이 담길 배열
	static int N; // 배열의 크기
	static int size = 2; // 아기상어의 크기
	static int shark_y, shark_x; // 아기상어의 위치
	
	static class Fish {	// 잡아먹을 물고기 객체
		int y, x, dis; // 물고기의 위치 좌표, 아기상어로부터의 거리

		public Fish(int y, int x, int dis) {
			super();
			this.y = y;
			this.x = x;
			this.dis = dis;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) { // 값이 9면 아기상어의 위치
					shark_y = i;
					shark_x = j;
					map[i][j] = 0; // 배열 탐색 시 방해되지 않도록 빈칸으로 만들기
				}				
			}
		}
		int ans = 0;
		int full = 0;
		while(true) {
			Fish fish = find(bfs()); // 먹을 수 있는 가장 가까운 물고기 찾기
			if(fish == null) break; // 더이상 먹을 물고기가 없으면 끝
			map[fish.y][fish.x] = 0; // 먹었으니 0으로 없애주기
			shark_y = fish.y; // 현재 위치 정보 갱신
			shark_x = fish.x;
			ans += fish.dis; // 시간에 거리 더해서 갱신
			// 먹은 수를 늘려주고 먹은 수가 현재 사이즈보다 크거나 같아지면 사이즈를 키우고 먹은 수 초기화
			full++;
			if(full == size) {
				size++;
				full = 0;
			}
		}
		System.out.println(ans);
		br.close();
		
	}
	
	static int[][] bfs() { // 아기상어의 위치로부터의 거리 정보 배열을 반환
		int[][] dis = new int[N][N]; // 아기상어와 물고기들간 거리 정보가 담길 배열
		// 거리를 전부 -1로 초기화
		for(int i = 0; i < N; i++) {
			Arrays.fill(dis[i], -1);
		}
		dis[shark_y][shark_x] = 0; // 현재 위치 거리를 0으로 초기화
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {shark_y, shark_x}); // 큐에 현재 위치 좌표 넣기
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int y = pos[0];
			int x = pos[1];
			for(int d = 0; d < 4; d++) {
				int new_y = y + dy[d];
				int new_x = x + dx[d];
				//범위 밖이면 제외
				if(new_y < 0 || new_y >= N || new_x < 0 || new_x >= N) continue;
				//지나갈 수 있는 곳이고 방문안한곳이면 동작
				if(map[new_y][new_x] <= size && dis[new_y][new_x] == -1) { // 더 큰 물고기는 못 지나감, 방문했던 곳은 다시방문 x
					dis[new_y][new_x] = dis[y][x] + 1; // 방문처리해서 0으로 만듦
					q.offer(new int[] {new_y, new_x});
				}
			}
		}
		return dis;
	}
	
	static Fish find(int[][] dis) { // 위쪽->아래, 왼쪽->오른쪽으로 거리 배열을 찾으면서 먹을 수 있는 물고기 찾아서 반환
		int min = Integer.MAX_VALUE;
		Fish fish = null;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				//방문했었고, 길이 아닌 먹이이고, 먹을 수 있는 사이즈이고, dis가 min보다 작으면 min 갱신
				if(dis[i][j] != -1 && 1 <= map[i][j] && map[i][j] < size && dis[i][j] < min) {
					// dis[i][j] <= min 이 아닌 < 인 이유는 위쪽, 왼쪽 우선순위 때문
					min = dis[i][j];
					fish = new Fish(i, j, min);
				}
			}
		}
		return fish;
	}
}

/*
6
6 0 6 0 6 1
0 0 0 0 0 2
2 3 4 5 6 6
0 0 0 0 0 2
0 2 0 0 0 0
3 9 3 0 0 1
*/