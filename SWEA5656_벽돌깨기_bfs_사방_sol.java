package Algorithm.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS + 자료구조 활용하여 빈공간 처리하기
public class SWEA5656_벽돌깨기_bfs_사방_sol {

	static class Point{
		int y,x,cnt;

		public Point(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int N,W,H,min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			min = Integer.MAX_VALUE;
			go(0,map);
			System.out.println("#"+tc+" "+min);
		}
	}

	static boolean go(int count,int[][] map) {// 중복순열 이용하여 구슬을 던지기, 벽돌이 다 부서졌다면 true, 아니면 false 리턴
		
		int result = getRemain(map);
		
		if(result == 0) {// 모든 벽돌이 다 부서졌다면
			min = 0;
			return true;
		}
		
		if(count == N) { // 모든 구슬을 다 던졌다면
			min = Math.min(min, result);
			return false;
		}
		int[][] newMap = new int[H][W];
		// 0열부터 W-1열까지 구슬 던져보기
		for (int x = 0; x < W; x++) {
			
			// 구슬에 맞는 벽돌 찾기
			int y = 0;
			while(y<H && map[y][x]==0) ++y; // 빈공간이면 계속해서 아래로
			
			// 해당 열은 벽돌이 없음
			if(y==H) continue;
			
			// 배열의 상태를 백업
			copy(map,newMap);
			boom(newMap,y,x); // 현재 벽돌 기준으로 주변의 가능한 모든 벽돌 함께 연쇄 처리
			down(newMap); // 부서진 벽돌 정리
			
			if(go(count+1, newMap)) return true; // 다음 구슬 던지러 go
		}
		return false;
	}
	
	static void boom(int[][] map, int y, int x) { // y,x 위치에서 주변의 가능한 모든 벽돌도 함께 부수는 처리 
		Queue<Point> queue = new LinkedList<Point>();
		if(map[y][x]>1) { // 벽돌크기가 2이상이면
			queue.offer(new Point(y,x,map[y][x]));
		}
		map[y][x] = 0; // 자신은 제거처리(빈 공간으로 ...) : visit 처리 역할
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = p.y, nx = p.x;
			
				for (int k = 1; k < p.cnt; k++) { // 벽돌의 크기-1만큼 반복
					ny += dy[d];
					nx += dx[d];
					if(ny>=0 && ny<H && nx>=0 && nx<W && map[ny][nx]>0) {
						if(map[ny][nx]>1) { // 주변 벽돌에 영향을 주는 벽돌이면
							queue.offer(new Point(ny, nx, map[ny][nx]));
						}
						map[ny][nx] = 0; //  제거 처리
					}
				}
			}
		}
	}
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static void down(int[][] map) {// 부서진 벽돌 정리,남은 벽돌 정리!!(공중에 떠있는 벽돌도 아래로 내리기)
		for (int x = 0; x < W; x++) {
			int y = H-1;// 아래행 시작
			while(y>=0) {
				if(map[y][x]>0) { // 벽돌 찾기
					list.add(map[y][x]);
					map[y][x] = 0;
				}
				y--;
			} // 부서지지 않고 남아있는 벽돌 리스트에 다 담기, 벽돌이 있던 자리는 빈공간으로 처리가 됨.
			
			y = H-1;
			for (int a : list) { // 벽돌리스트
				map[y--][x] = a;
			}
			list.clear();
		}
	}
	static int getRemain(int[][] map) { // 남은 벽돌 수 리턴
		int count = 0;
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				if(map[y][x]>0) ++count;
			}
		}
		return count;
	}
	static void copy(int[][] map, int[][] newMap) {
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				newMap[y][x] = map[y][x];
			}
		}
	}
}