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

public class Boj_2636_치즈_bfs {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int ans, temp;
	static int depth;
	static Queue<int[]> que2 = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// bfs 실행
		bfs1(0, 0);
		bfs2();

		// 출력
		sb.append(depth).append('\n').append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	// 겉 공기 구별 후, 겉 치즈 탐색
	static void bfs1(int start_y, int start_x) {
		Queue<int[]> que = new LinkedList<>();

		que.offer(new int[] { start_y, start_x });
		visited[start_y][start_x] = true;

		while (!que.isEmpty()) {
			int size = que.size();

			while (size-- > 0) {
				int[] xy = que.poll();
				int y = xy[0];
				int x = xy[1];

				// 사방탐색
				for (int dir = 0; dir < 4; dir++) {
					int ny = y + dy[dir];
					int nx = x + dx[dir];
					// 범위 밖이면 패스
					if (ny >= N || nx >= M || ny < 0 || nx < 0)
						continue;

					// 방문 안 한 겉 공기면
					if (map[ny][nx] == 0 && !visited[ny][nx]) {
						visited[ny][nx] = true;
						que.offer(new int[] { ny, nx });
						//겉 치즈를 만나면
					} else if (map[ny][nx] == 1 && !visited[ny][nx]) {
						visited[ny][nx] = true;
						que2.offer(new int[] { ny, nx });
						temp++;
					}
				}
			}
		}
		if (start_y == 0)
			ans = temp;
	}

	// 치즈 녹는 것 탐색
	static void bfs2() {
		while (!que2.isEmpty()) {
			temp = 0;
			int size = que2.size();

			while (size-- > 0) {
				int[] xy = que2.poll();
				int y = xy[0];
				int x = xy[1];

				// 사방탐색
				for (int dir = 0; dir < 4; dir++) {
					int ny = y + dy[dir];
					int nx = x + dx[dir];

					// 방문 안 한 치즈면
					if (map[ny][nx] == 1 && !visited[ny][nx]) {
						visited[ny][nx] = true;
						que2.offer(new int[] { ny, nx });
						temp++;
						//안에 구멍이 있으면
					} else if (map[ny][nx] == 0 && !visited[ny][nx]) {
						bfs1(ny, nx);
					}
				}
			}
			depth++;
			if (temp != 0)
				ans = temp;
		}
	}
}
