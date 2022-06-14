package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3307_최장증가부분수열_dp {
	static int N;
	static int[] number;
	static int[] LIS;
	static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			
			//데이터 입력
			N = Integer.parseInt(br.readLine());
			number = new int[N];
			LIS = new int[N];
			Arrays.fill(LIS, 1);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			
			//실행
			run();
			
			//출력
			sb.append('#').append(tc).append(" ").append(max).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run() {
		max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<i; j++) {
				if(number[i]>number[j] && LIS[i] < LIS[j]+1) LIS[i] = LIS[j]+1;
			}
			if(max<LIS[i]) max = LIS[i];
		}
	}
}
