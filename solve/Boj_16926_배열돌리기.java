package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_16926_배열돌리기 {

	static int[] dy = { 0, 1, 0, -1 }; // 우, 상,좌,하 (반시계)
	static int[] dx = { 1, 0, -1, 0 };
	

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input12.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		// 배열 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//돌려야 하는 그룹 개수
		int depth = Math.min(N, M) / 2;
		//회전수 반복
		for (int i = 0; i < R; i++) {
			//그룹수 반복
			for (int j = 0; j < depth; j++) {
				int y = j;
				int x = j;
				int dir = 0;
				//가장 첫째 값
				int temp = arr[y][x];
				
				while (dir < 4) {
					int ny = y + dy[dir];
					int nx = x + dx[dir];
					//범위 내에 있으면 돌리기
					if (nx >= j && ny >= j && ny < N - j && nx < M - j) {
						arr[y][x] = arr[ny][nx];
						y = ny;
						x = nx;
					//범위 벗어나면 방향 전환
					} else dir++;
				}
				arr[j + 1][j] = temp;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
