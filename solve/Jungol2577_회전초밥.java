package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Jungol2577_회전초밥 {
	
	static int N, d, k, c;
	static 	int[] foods;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//입력
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		foods = new int[N+k-1];
		for (int i = 0; i < N; i++) {
			foods[i] = Integer.parseInt(br.readLine());
		}
		for(int i=0; i<k-1; i++) {
			foods[N+i] = foods[i];
		}
		
		//실행
		run();
		
		//출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static void run() {
		label:for(int i=0; i<N; i++) {
			int[] checked = new int[d];
			int sum = 0;
			for(int j=0; j<k; j++) {
				//가지치기: 현재먹은음식종류수와 남은음식수를 더해도 sum보다 작으면 그만
				if(sum+k-j<sum && sum!=Integer.MAX_VALUE) continue label;
				//안먹었던 음식이면
				if(checked[foods[i+j]-1] == 0) {
					checked[foods[i+j]-1] = 1;
					sum++;
				}
			}
			//쿠폰확인
			if(checked[c-1] == 0) sum++;
			if(sum>max) max = sum;
			//음식먹기
			if(max==k+1) return;
		}
	}
	
}
