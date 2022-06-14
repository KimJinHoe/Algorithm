package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA5215_햄버거다이어트_dp {

	static int N, L;
	static int[][] result;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input6.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			dp = new int[L + 1];
			result = new int[N + 1][L + 1];
			
			// 데이터 입력 및 Knapsack알고리즘 실행
			for (int i = 1; i < N + 1; i++) {
				st = new StringTokenizer(br.readLine());
				int point = Integer.parseInt(st.nextToken());
				int calory = Integer.parseInt(st.nextToken());
				run(i, point, calory);
			}
			
			int ans = result[N][L];
			// 출력
			sb.append('#').append(tc).append(" ").append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void run(int n, int point, int calory) {
			for(int w=0; w<L+1; w++) {
				if(w>=calory) {
					result[n][w] = Math.max(result[n-1][w], point+result[n-1][w-calory]);
				} else {
					result[n][w] = result[n-1][w];
				}
			}
	}
}
