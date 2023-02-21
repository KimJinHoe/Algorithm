package cote;
//2023.02.21 276ms 1등 
import java.io.*;
import java.util.*;

public class Boj_2342_DanceDanceRevolution_dp {
	
	static int[][][] dp = new int[100001][5][5];
	int ans = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		
		//dp 초기화. 40만(10만*4점) 이상인 값으로 설정
		for(int i=0; i<100001; i++) {
			for(int j=0; j<5; j++) {
				Arrays.fill(dp[i][j], 1000000);
			}
		}
		
		int num = read();
		//수열이 없는 경우
		if(num == 0) {
			System.out.println(0);
			return;
		}
		
		dp[0][num][0] = 2;
		dp[0][0][num] = 2;
		int cnt = 0;
		while((num = read()) != 0) {
			for(int left=0; left<5; left++) {
				for(int right=0; right<5; right++) {
					if(num != right) {
                        int lchange = dp[cnt][left][right] + calc(left, num);
					    if(dp[cnt+1][num][right] > lchange) dp[cnt+1][num][right] = lchange;
                    }
                    if(num != left) {
                        int rchange = dp[cnt][left][right] + calc(right, num);
					    if(dp[cnt+1][left][num] > rchange) dp[cnt+1][left][num] = rchange;
                    }
				}
			}
			cnt++;
		}


		int min = Integer.MAX_VALUE;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(min > dp[cnt][i][j]) min = dp[cnt][i][j];
			}
		}
		
		System.out.println(min);
	}
	
	static int calc(int post, int next) {
		if(post == next) return 1;
		if(post == 0) return 2;
		if((post-next) % 2 == 0) return 4;
		return 3;
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
}
