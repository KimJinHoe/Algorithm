package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Boj17144미세먼지안녕
public class Boj_17144_미세먼지안녕 {
	
	static class Info{
		int y;
		int x;
		int val;
		
		public Info(int y, int x, int val) {
			super();
			this.y = y;
			this.x = x;
			this.val = val;
		}
		
	}
	
	static int R, C, T;
	static int[] machine = new int[2];
	static int[] dy = {-1,1,0,0}; //상하좌우
	static int[] dx = {0,0,-1,1};
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		Queue<Info> que = new LinkedList<>();
		
		//배열입력받기
		int[][] map = new int[R][C];
		int cnt = 0;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				int num = Integer.parseInt(st.nextToken());
				//map[i][j] = num;
				if(num == -1) {
					machine[cnt++] = i;
					map[i][j] = num;
				}
				else if(num > 0) que.add(new Info(i,j,num));
				
			}
		}
		
		//실행
		bfs(map, que);

		//출력
		System.out.println(ans);
	}
	
	static void bfs(int[][] map, Queue<Info> que) {
		int time = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			while(size -- > 0) {
				Info cur = que.poll();
				int val = cur.val/5;
				int cnt = 0;
				
				//사방탐색해서 먼지 이동
				for(int d=0; d<4; d++) {
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];
					//범위 밖 못 감
					if(ny<0 || nx<0 || ny>=R || nx>=C) continue;
					//에어컨 있는 곳으로 못 감
					if(map[ny][nx] == -1) continue;
					cnt++;
					map[ny][nx] += val;
				}
				//기존 자리에 남은 먼지
				map[cur.y][cur.x] += cur.val - val*cnt;
			}
			
			//공기청정기 작동
			refresh(map);
			
			//맵의 남은 먼지 다시 전부 que에 넣고 bfs 돌림
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j] > 0) {
						que.add(new Info(i,j,map[i][j]));
						map[i][j] = 0;
					}
				}
			}
			time++;
			if(time == T) break;
		}
		while(!que.isEmpty()) ans += que.poll().val;
	}
	
	static void refresh(int[][] map) {
		//위쪽
		int m1 = machine[0];
		for(int i=m1-1; i>0; i--) map[i][0] = map[i-1][0];
		for(int i=0; i<C-1; i++) map[0][i] = map[0][i+1];
		for(int i=0; i<m1; i++) map[i][C-1] = map[i+1][C-1];
		for(int i=C-1; i>1; i--) map[m1][i] = map[m1][i-1];
		map[m1][1] = 0;
		
		//아래쪽
		int m2 = machine[1];
		for(int i=m2+1; i<R-1; i++) map[i][0] = map[i+1][0];
		for(int i=0; i<C-1; i++) map[R-1][i] = map[R-1][i+1];
		for(int i=R-1; i>m2; i--) map[i][C-1] = map[i-1][C-1];
		for(int i=C-1; i>1; i--) map[m2][i] = map[m2][i-1];
		map[m2][1] = 0;
	}
}
