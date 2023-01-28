package cote;
//2023.01.28 124ms 9등
import java.io.*;
import java.util.*;

public class Boj_10830_행렬제곱_dp {
	
	static int A;
	static long B;
	static Stack<Integer> stack;
	static long[] dp = new long[37];
	static int[][][] mdp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		A = read();
		B = readL();
		stack = new Stack<Integer>();
		mdp = new int[37][A][A];
		
		//입력
		for(int i=0; i<A; i++) {
			for(int j=0; j<A; j++) {
				mdp[0][i][j] = read();
			}
		}
		
		//계산
		makedp();
		getnums();
		makemdp();
		
		int[][] ans = mdp[stack.pop()];
		while(!stack.isEmpty()) {
			ans = calc(ans, mdp[stack.pop()]);
		}

		//출력
		for(int i=0; i<A;i++) {
			for(int j=0; j<A; j++) {
				sb.append(ans[i][j]%1000);
				if(j != A-1) sb.append(' ');
			}
			sb.append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int[][] calc(int[][] m1, int[][] m2) {
		int[][] nm = new int[A][A];
		for(int i=0; i<A; i++) {
			for(int j=0; j<A; j++) {
				for(int k=0; k<A; k++) {
					nm[i][j] += m1[i][k]*m2[k][j];
				}
				nm[i][j] %= 1000;
			}
		}
		return nm;
	}
	
	static void makemdp() {
		mdp[1] = calc(mdp[0], mdp[0]);
		for(int i=2; i<37; i++) {
			mdp[i] = calc(mdp[i-1], mdp[i-1]);
		}
	}
	
	static void makedp() {
		dp[0] = 1;
		for(int i=1; i<37; i++) {
			dp[i] = dp[i-1]*2;
		}
	}
	
	static void getnums() {
		for(int i=36; i>=0; i--) {
			if(B >= dp[i]) {
				B -= dp[i];
				stack.add(i);
			}
		}
	}
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
    
    static long readL() throws Exception {
        long c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}