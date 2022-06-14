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

public class SWEA1953_탈주범검거_bfs_사방 {

	static int N, M, R, C, L;
	static int[][] dy = { {}, { -1, 1, 0, 0 }, { -1, 1 }, { 0, 0 }, { -1, 0 }, { 0, 1 }, { 0, 1 }, { -1, 0 } };
	static int[][] dx = { {}, { 0, 0, -1, 1 }, { 0, 0 }, { -1, 1 }, { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 실행
			int ans = bfs(map);
			// 출력
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int bfs(int[][] map) {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] { R, C });
		visited[R][C] = true;

		while (--L > 0) {
			int size = que.size();
			
			while (size-- > 0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];

				int pipe = map[y][x];
				int[] ddy = dy[pipe];
				int[] ddx = dx[pipe];
				int dl = ddy.length;
				//파이프의 종류에 따른 사방탐색
				for (int d = 0; d < dl; d++) {
					int ny = y + ddy[d];
					int nx = x + ddx[d];
					//범위확인
					if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
					//방문 및 다음이 파이프인지 확인
					if (visited[ny][nx] || map[ny][nx] == 0) continue;
					//현재 파이프에서 다음 파이프가 연결되어 있는지
					if(!avail(ddy[d], ddx[d], map[ny][nx])) continue;
					que.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
				}
			}
		}
		//범인이 존재 가능한 공간 카운트
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == true) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	static boolean avail(int dy, int dx, int np) { // np:next pipe
		// 상하좌우 순
		if (dy == -1) {
			if (np == 3 || np == 4 || np == 7)
				return false;
		} else if (dy == 1) {
			if (np == 3 || np == 5 || np == 6)
				return false;
		} else if (dx == -1) {
			if (np == 2 || np == 6 || np == 7)
				return false;
		} else if (dx == 1) {
			if (np == 2 || np == 4 || np == 5)
				return false;
		}
		return true;
	}
}
