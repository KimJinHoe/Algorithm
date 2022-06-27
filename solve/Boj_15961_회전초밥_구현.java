package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_15961_회전초밥_구현 {
	
	static int N, d, k, c;
	static 	int[] foods;
	static int ans;
	
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
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static void run() {
		int[] checked = new int[d+1]; //음식의 번호는 1부터 
		checked[c]++; //쿠폰음식은 처음부터 먹을 수 있으니 +1
		int sum = 1;
		//0번~k번의 경우일 때 카운트
		for(int i=0; i<k; i++) {
			if(checked[foods[i]] == 0) sum++; //먹은 음식종류+
			checked[foods[i]]++;
		}
		int max = sum;
		
		//그 다음부터 맨 처음과 맨 끝만 바꿔가면서 카운트
		int start = 0;
		int end = 0;
		for(int i=0; i<N-1; i++) {
			start = foods[i];
			end = foods[i+k];
			if(end == start) continue; //추가와 뺀 음식이 같은 종류면 변화 X
			else {
				if(++checked[end] == 1) sum++; //추가된 음식이 1개면 sum++
				if(--checked[start] == 0) sum--; //뺀 음식이 0개면 sum--
				if(max < sum) max = sum;
			}
		}
		
		ans = max;
	}
	
}
