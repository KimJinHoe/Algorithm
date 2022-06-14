package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5607_조합_페르마소정리 {
	
	static int MOD = 1234567891;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			long[] fac = new long[n+1];
			fac[0] = 1;
			fac[1] = 1;
			for (int i = 2; i <= n; i++) {
				fac[i] = (fac[i-1]*i) % MOD;
			}
			
			long bottom = (fac[r] * fac[n-r]) % MOD;
			bottom = pow(bottom, MOD-2); //페르마소정리
			System.out.println("#" + tc + " " + (fac[n] * bottom) % MOD );
		}
	}

	//
	private static long pow(long a, int b) {//a의 b승
		if(b == 0)
			return 1;
		
		else if(b == 1)
			return a;
		
		if(b%2 == 0) {//b가 짝수인 경우
			long tmp = pow(a, b/2);
			return (tmp * tmp) % MOD;
		}
		
		long tmp = pow(a, b-1) % MOD;//pow(2,5) ==> pow(2,4)
		return (tmp * a) % MOD;
	}
}