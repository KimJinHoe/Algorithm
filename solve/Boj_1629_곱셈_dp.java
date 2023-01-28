package cote;
//2023.01.28 116ms 2등
import java.io.*;
import java.util.*;

public class Boj_1629_곱셈_dp {

	static int A, B, C;
	static long[] dp = new long[31];
	static long[] adp = new long[31];
	static Stack<Integer> stack = new Stack<Integer>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		A = read();
		B = read();
		C = read();

		makedp();
		makeadp();
		getnums();
		long ans = adp[stack.pop()];
		while(!stack.isEmpty()) {
			ans = (ans * adp[stack.pop()]) % C;
		}
		
		System.out.println(ans);
	}

	static void makeadp() {
		adp[0] = A % C;
		adp[1] = (adp[0] * adp[0]) % C;
		for (int i = 2; i < 31; i++) {
			adp[i] = (adp[i - 1] * adp[i - 1]) % C;
		}
	}
	
	static void makedp() {
		dp[0] = 1;
		for(int i=1; i<31; i++) {
			dp[i] = dp[i-1]<<1;
		}
	}

	static void getnums() {
		for (int i = 30; i >= 0; i--) {
			if (B >= dp[i]) {
				B -= dp[i];
				stack.add(i);
			}
		}
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13)
			System.in.read();
		return n;
	}
}
