package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//Boj16935 배열 돌리기3
public class Boj_16935_배열돌리기3 {

	static int[][] arr;
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input21.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		// 배열 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		// R만큼 반복
		for (int r = 0; r < R; r++) {
			int mode = Integer.parseInt(st.nextToken());
			switch (mode) {
			case 1: run1(); break;
			case 2: run2(); break;
			case 3: run3(); break;
			case 4: run4(); break;
			case 5: run5(); break;
			case 6: run6(); break;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	// 상하반전
	static void run1() {
		// temp(arr의 깊은 복사)
		int[][] temp = new int[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i].clone();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = temp[N - 1 - i][j];
			}
		}
	}

	// 좌우반전
	static void run2() {
		int[][] temp = new int[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i].clone();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = temp[i][M - 1 - j];
			}
		}
	}

	// 오른쪽 90도
	static void run3() {
		int[][] temp = new int[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i].clone();
		}

		arr = new int[M][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[j][N - 1 - i] = temp[i][j];
			}
		}
		int temp_N = N;
		N = M;
		M = temp_N;
	}

	// 왼쪽 90도
	static void run4() {
		int[][] temp = new int[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i].clone();
		}
		arr = new int[M][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[M - 1 - j][i] = temp[i][j];
			}
		}
		int temp_N = N;
		N = M;
		M = temp_N;
	}
	//부분배열 오른쪽
	static void run5() {
		int[][] temp = new int[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i].clone();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int y = i / (N/2);
				int x = j / (M/2);
				if(y==0 && x==0) arr[i][j + M/2] = temp[i][j];
				else if(y==0 && x==1) arr[i + N/2][j] = temp[i][j];
				else if(y==1 && x==1) arr[i][j - M/2] = temp[i][j];
				else if(y==1 && x==0) arr[i - N/2][j] = temp[i][j];
			}
		}
	}
	//부분배열 왼쪽
	static void run6() {
		int[][] temp = new int[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i].clone();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int y = i / (N/2);
				int x = j / (M/2);
				if(y==0 && x==0) arr[i][j] = temp[i][j + M/2];
				else if(y==0 && x==1) arr[i][j] = temp[i + N/2][j];
				else if(y==1 && x==1) arr[i][j] = temp[i][j - M/2];
				else if(y==1 && x==0) arr[i][j] = temp[i - N/2][j];
			}
		}
	}

}
