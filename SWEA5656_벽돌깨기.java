package Algorithm.solve;
//가지치기 안함. 1067ms
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5656_벽돌깨기 {

	static int N, W, H;
	static int min;
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int[][] map = new int[H][W];
			int[] number = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
				}
			}
			// 실행
			perm(0, number, map);

			// 출력
			sb.append('#').append(tc).append(" ").append(min).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	// 중복순열
	static void perm(int cnt, int[] number, int[][] map) {
		if (cnt == N) {
			// map 깊은 복사
			int[][] temp = new int[H][W];
			for (int i = 0; i < H; i++) {
				temp[i] = map[i].clone();
			}
			int num = drop(number, temp);
			if (num < min)
				min = num;
			return;
		}

		for (int i = 0; i < W; i++) {
			number[cnt] = i;
			perm(cnt + 1, number, map);
		}
	}

	// 구슬떨어뜨리기 실행
	static int drop(int[] number, int[][] temp) {
		for (int n = 0; n < N; n++) {
			int j = number[n];
			for (int i = 0; i < H; i++) {
				if (temp[i][j] == 0) continue;
				crush(i, j, temp[i][j], temp);
				arrange(temp);
				break;
			}
		}
		return count(number, temp);
	}

	// 벽돌깨기
	static void crush(int y, int x, int num, int[][] temp) {
		temp[y][x] = 0;
		for(int d = 0; d < 4; d++) {
			for(int power=0; power<num; power++) {
				int ny = y + (dy[d])*power;
				int nx = x + (dx[d])*power;
				if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
				if(temp[ny][nx] != 0) crush(ny, nx, temp[ny][nx], temp); 
			}
		}
	}

	// 맵 재정렬
	static void arrange(int[][] temp) {
		for(int j=0; j<W; j++) {
			Queue<Integer> que = new LinkedList<Integer>();
			for(int i=H-1; i>=0; i--) {
				if(temp[i][j] != 0) {
					que.offer(temp[i][j]);
					temp[i][j] = 0;
				}
			}
			int i=H-1;
			while(!que.isEmpty()) {
				temp[i][j] = que.poll();
				i--;
			}
		}
	}

	// 남은벽돌카운트
	static int count(int[] number, int[][] temp) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (temp[i][j] != 0)
					cnt++;
			}
		}
		return cnt;
	}
}
