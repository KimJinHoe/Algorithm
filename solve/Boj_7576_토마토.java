package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7576_토마토 {

	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		Queue<int[]> que = new LinkedList<>();
		// 배열입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num == 1)
					que.add(new int[] { i, j });
			}
		}
		// 실행
		System.out.println(bfs(map, N, M, que));
	}

	static int bfs(int[][] map, int N, int M, Queue<int[]> que) {
		int ans = -1;
		while (!que.isEmpty()) {
			int size = que.size();
			while (size-- > 0) {
				int[] cur = que.poll();
				for (int d = 0; d < 4; d++) {
					int ny = cur[0] + dy[d];
					int nx = cur[1] + dx[d];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M)
						continue;
					else if (map[ny][nx] == 0) {
						que.add(new int[] { ny, nx });
						map[ny][nx] = 1;
					}
				}
			}
			ans++;
		}
		// 최종적으로 안 익는 토마토가 있으면 depth는 -1
		label: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					ans = -1;
					break label;
				}
			}
		}
		return ans;
	}

}