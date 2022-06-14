package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/*
1cm 짜리 파란 막대와 1cm 짜리 노란 막대 그리고 2cm 짜리 빨간 막대가 있다. 
이 막대들을 연결하여 길이가 ncm 인 막대를 만드는 방법의 수를 f(n)이라 하자.
풀이: f(n) = 2f(n-1) + f(n-2)
 */
public class Solution_0331_막대색칠하기_dp {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		System.out.println(dp(6));
		
	}
	
	static int dp(int n) {
		if(n==0) return 1;
		if(n==1) return 2;
		return 2*dp(n-1)+dp(n-2);
	}
}
