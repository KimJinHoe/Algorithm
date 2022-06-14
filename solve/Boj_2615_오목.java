package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_2615_오목 {
	static int[][] map = new int[21][21];
	static int[][][] memo = new int[21][21][4];
	static int[] dx = { 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1 };
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 1; i <= 19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		findFive();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void findFive() {
		for (int j = 1; j <= 19; j++) {
			for (int i = 1; i <= 19; i++) {
				if (map[i][j] != 0) {
					for (int d = 0; d < 4; d++) {
						if (memo[i][j][d] == 0 && calc(i, j, d, map[i][j]) == 5) {
							sb.append(map[i][j]).append('\n').append(i).append(' ').append(j);
							return;
						}
					}
				}
			}
		}
		sb.append('0');
		return;
	}

	private static int calc(int x, int y, int dir, int color) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if (map[nx][ny] == color) {
			return memo[nx][ny][dir] = calc(nx, ny, dir, color) + 1;
		}
		return 1;
	}

}