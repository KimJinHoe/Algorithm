package cote;

import java.io.*;
import java.util.*;

public class Boj_12865_평범한배낭_dp {

	static int[][] items;
	static int N, K;
	static int[] dp;
	static int[] sum = new int[2];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> que = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			};
		});
		items = new int[N + 1][2];
		dp = new int[K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			que.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}

		int size = que.size();
		for (int i = 0; i < size; i++) {
			items[i] = que.poll();
			sum[0] += items[i][0];
			sum[1] += items[i][1];
		}

		if (K >= sum[0]) {
			System.out.println(sum[1]);
			return;
		}

		for (int i = 0; i <= N; i++) {
			for (int j = K; j - items[i][0] >= 0; j--) {
				dp[j] = Math.max(dp[j], dp[j - items[i][0]] + items[i][1]);
			}
		}
		System.out.println(dp[K]);
	}
}
