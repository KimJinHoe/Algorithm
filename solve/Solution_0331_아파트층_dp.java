package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
아파트를 각 층별로 파란색 또는 노란색 페인트로 칠하되 다음과 같은 규칙으로 칠하려고 한다.
 노란색은 인접한 두 층에 연속하여 사용할 수 있다.
 파란색은 인접한 두 층에 연속하여 사용할 수 없다.
이와 같은 규칙으로 층의 아파트를 칠할 수 있는 방법의 수를 f(n)이라 하면 다음 그림과 같이
f(1) = 2, f(2) = 3 이다. f(8)을 얼마인가?
풀이: f(n, blue) = f(n-1, yellow), f(n,yellow) = f(n-1, yellow) + f(n-1, blue)
	f(n) = f(n, blue) + f(n, yellow)
 */
public class Solution_0331_아파트층_dp {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		System.out.println(yellowdp(8) + bluedp(8));
	}

	static int yellowdp(int n) {
		if(n==1) return 1;
		return yellowdp(n-1)+bluedp(n-1);
	}

	static int bluedp(int n) {
		if(n==1) return 1;
		return yellowdp(n-1);
	}
}
