package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//도영이가 만든 맛있는 음식
public class Boj_2961_도영이 {
	//food[][0]: 신맛, food[][1]: 쓴맛
	static int[][] foods;
	static int N;;
	static int min = 999999;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		foods = new int[N][2];

		//foods 입력
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			foods[n][0] = Integer.parseInt(st.nextToken());
			foods[n][1] = Integer.parseInt(st.nextToken());
		}
		//실행
		subset(0, 1, 0, 0);
		//출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void subset(int cnt, int s, int b, int sel_count) {
		if(cnt == N) {
			if(sel_count == 0) return;
			int diff = Math.abs(s-b);
			if(diff < min) min = diff;
			return;
		}
		subset(cnt+1, 1*foods[cnt][0], 0+foods[cnt][1], sel_count+1);
		subset(cnt+1, 1, 0, sel_count);
	}
	
}
