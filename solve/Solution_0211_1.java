package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//정사각형 방
public class Solution_0211_1 {

	static boolean[][] check;
	static int N;
	static int[] result = new int[2];
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input20.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		//테스트케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			// 배열 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//result 테스트케이스마다 초기화
			result[0] = N*N;
			result[1] = 0;
			check = new boolean[N][N];
			//방문체크하면서 재귀함수로 방을 돌아다니고 결과값을 찾음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!check[i][j]) move(arr, i, j, 1);
				}
			}
			//출력
			sb.append('#').append(tc).append(' ').append(result[0]).append(' ').append(result[1]).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void move(int[][] arr, int i, int j, int depth) {
		//방문체크
		check[i][j] = true;
		//조건에 따라 result 갱신 
		if(depth >= result[1]) {
			if(depth > result[1]) {
				result[1] = depth;
				result[0] = arr[i][j] - (depth -1);
			}
			else if(depth == result[1]) {
				if(arr[i][j] < result[0]) result[0] = arr[i][j] - (depth -1); 
			}
		}
		//사방탐색으로 자기보다 +1이 주변에 있는지 찾음
		for (int dir = 0; dir < 4; dir++) {
			int y = i + dy[dir];
			int x = j + dx[dir];
			if (x >= 0 && x < N && y >= 0 && y < N) {
				if (arr[y][x] == arr[i][j] + 1) move(arr,y,x, depth+1);
			}
		}
	}
	
}
