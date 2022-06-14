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

public class Boj_16236_아기상어_bfs {
	
	static int N;
	static int shark_y;
	static int shark_x;
	static int size_shark = 2;
	static int full = 0;
	static int[][] map;
	static int[] fish = new int[6];
	static int[] dy = {-1, 0, 1, 0}; //상좌하우
	static int[] dx = {0, -1, 0, 1};
	static int ans = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 9) { //상어면
					shark_y = i;
					shark_x = j;
				}
				else if(num != 0) { //물고기면
					fish[num-1]++;
					map[i][j] = num;
				}
			}
		}
		
		//함수 실행
		bfs();
		
		//출력
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void bfs() {
		Queue<int[]> que = new LinkedList<int[]>();
		Queue<int[]> food = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		que.offer(new int[] {shark_y, shark_x}); //y, x좌표
		visited[shark_y][shark_x] = true;
		int dist = 0; //상어와의 거리
		
		while(!que.isEmpty()) {
			int size = que.size();
			dist++;
			
			while(size-->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny<0 || nx<0 || ny>=N || nx>=N) continue; //범위 밖이면 패스
					if(visited[ny][nx]) continue; //방문했으면 패스
					if(map[ny][nx] > size_shark) continue; //큰 물고기면 패스
					if(map[ny][nx]!=0 && map[ny][nx]<size_shark) food.offer(new int[] {ny, nx}); //먹이면 먹이후보에 추가 (아직 먹지 않음)
					else { //먹이가 아닌 길이면 계속 bfs 진행
						que.offer(new int[] {ny, nx});
						visited[ny][nx] = true;
					}
				}
			}
			if(!food.isEmpty()) { //먹이후보가 있으면
				int food_size = food.size();
				shark_y = 999;
				shark_x = 999;
				//후보 중 먹이를 정함
				for(int i=0; i<food_size; i++) {
					int[] food_loc = food.poll();
					if(food_loc[0] < shark_y) {
						shark_y = food_loc[0];
						shark_x = food_loc[1];
					}
					else if(food_loc[0] == shark_y && food_loc[1] < shark_x) {
						shark_y = food_loc[0];
						shark_x = food_loc[1];
					}
				}
				//먹음
				//큐와 방문처리 초기화
				que = new LinkedList<int[]>();
				food = new LinkedList<int[]>();
				que.offer(new int[] {shark_y, shark_x});
				visited = new boolean[N][N];
				visited[shark_y][shark_x] = true;
				//답 갱신 및 dist 초기화
				ans += dist;
				dist = 0;
				//물고기 삭제
				fish[map[shark_y][shark_x]-1]--;
				map[shark_y][shark_x] = 0;
				//포만도 및 성장
				full++;
				if(full == size_shark) {
					full = 0;
					size_shark ++;
				}
				if(!check_fish()) return; //더 이상 먹을 수 있는 물고기가 맵에 없으면 종료
			}
		}
	}
	
	static boolean check_fish() {
		for(int i=0; i<6; i++) {
			if(i>=size_shark-1) continue;
			if(fish[i] != 0) return true; //맵에 먹을 수 있는 물고기가 존재한다.
		}
		return false; //더 이상 먹을 수 있는 물고기가 없다.
	}
}
