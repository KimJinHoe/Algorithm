package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_0204_2_Answer {

	static int height;
	static int width;
	static int y; // 현재 전차 위치
	static int x;
	static int[] dy = { 0, 0, 1, -1 }; // 동서남북
	static int[] dx = { 1, -1, 0, 0 };
	static int dir; // 동서남북 0,1,2,3

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		// T만큼 반복
		for (int tc = 0; tc < T; tc++) {
			String[] str = br.readLine().split(" ");
			// H, W입력받기
			height = Integer.parseInt(str[0]);
			width = Integer.parseInt(str[1]);
			char[][] arr = new char[height][width];
			// 배열 입력
			for (int i = 0; i < height; i++) {
				String line = br.readLine();
				for (int j = 0; j < width; j++) {
					arr[i][j] = line.charAt(j);
					if (arr[i][j] == '<' || arr[i][j] == '>' || arr[i][j] == '^' || arr[i][j] == 'v') {
						if (arr[i][j] == '^')
							dir = 3;
						else if (arr[i][j] == 'v')
							dir = 2;
						else if (arr[i][j] == '<')
							dir = 1;
						else if (arr[i][j] == '>')
							dir = 0;
						y = i;
						x = j;
					}
				}
			}
			// N 입력받기
			int n = Integer.parseInt(br.readLine());
			String actions = br.readLine();
			run(arr, actions, n);
			sb.append("#").append(tc + 1).append(" ");
			for (int i = 0; i < height; i++) {
				sb.append(arr[i]).append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void run(char[][] arr, String actions, int n) {
		for (int i = 0; i < n; i++) {
			char c = actions.charAt(i);
			int temp_y = y;
			int temp_x = x;
			if (c == 'U' || c == 'D' || c == 'L' || c == 'R') {
				if (c == 'U') {
					dir = 3;
					arr[y][x] = '^';
				} else if (c == 'D') {
					dir = 2;
					arr[y][x] = 'v';
				} else if (c == 'L') {
					dir = 1;
					arr[y][x] = '<';
				} else if (c == 'R') {
					dir = 0;
					arr[y][x] = '>';
				}
				temp_y = y + dy[dir];
				temp_x = x + dx[dir];

				if (temp_y >= 0 && temp_y < height && temp_x >= 0 && temp_x < width) {
					if (arr[temp_y][temp_x] == '.') {
						arr[temp_y][temp_x] = arr[y][x];
						arr[y][x] = '.';
						y = temp_y;
						x = temp_x;
					}
				}
			} else if (c == 'S') {
				// 현재 전차 방향에 따라서 발사 방향 다르게
				while (true) {
					temp_y += dy[dir];
					temp_x += dx[dir];
					if (!(temp_y >= 0 && temp_y < height && temp_x >= 0 && temp_x < width) || arr[temp_y][temp_x] == '#') break;
					else if (arr[temp_y][temp_x] == '*') {
						arr[temp_y][temp_x] = '.';
						break;
					}
				}

			}
		}
	}

}