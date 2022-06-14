package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1600_말이되고픈원숭이 {
	
	static int K;
	static int[][] map;
	static int W, H;
	static int ans = -1;
	static int[] dy = {-1,1,0,0}; //원숭이 움직임
	static int[] dx = {0,0,-1,1};
	static int[] ddy = {-2,-2,-1,1,2,2,1,-1}; //말 움직임
	static int[] ddx = {-1,1,2,2,1,-1,-2,-2};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//실행
		run();
		
		//결과
		System.out.println(ans);
	}
	
	static void run() {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {0,0,K,0}); //y, x, 남은 점프 횟수, 움직인 횟수
		boolean[][][] visited = new boolean[H][W][K+1];
		Arrays.fill(visited[0][0], true);
		
		while(!que.isEmpty()) {
			int size = que.size();
			
			while(size-->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				int left = cur[2];
				int cnt = cur[3];
				
				//목적지에 도달하면 끝
				if(y==H-1 && x==W-1) {
					ans = cnt;
					return;
				}
				
				//원숭이 움직임
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny<0 || nx<0 || ny>=H || nx>=W) continue; //배열이 범위 밖이면 continue
					if(map[ny][nx] == 1) continue;// 장애물 있으면 continue
					if(visited[ny][nx][left]) continue; // 이 방법보다 더 짧은 이동횟수로 도달 가능하면 continue
					visited[ny][nx][left] = true;
					que.offer(new int[] {ny, nx, left, cnt+1});
				}
				
				//말 움직임
				if(left == 0) continue; //더 이상 남은 점프 횟수 없으면 하지 않음
				for(int d=0; d<8; d++) {
					int ny = y + ddy[d];
					int nx = x + ddx[d];
					if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
					if(map[ny][nx] == 1) continue;
					if(visited[ny][nx][left-1]) continue;
					visited[ny][nx][left-1] = true;
					que.offer(new int[] {ny, nx, left-1, cnt+1});
				}
				
			}
		}
	}

}
