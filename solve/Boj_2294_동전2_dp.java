package Algorithm.solve;

import java.util.Arrays;
import java.util.Scanner;

public class Boj_2294_동전2_dp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] coin = new int[n];
		for (int i = 0; i < n; i++) {
			coin[i] = sc.nextInt();
		}
		int[] dp = new int[k + 1];
		Arrays.fill(dp, 100001);
		dp[0] = 0;

		for (int i = 0; i < n; i++) {
			for (int j = coin[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
			}
		}

		if (dp[k] == 100001) dp[k] = -1;
		System.out.println(dp[k]);
	}

}