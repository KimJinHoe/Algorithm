package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1149_RGB거리_dp {
	
	static int N;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][3];
		for(int i=0; i<N; i++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//실행
		dp(map);
		
		//출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dp(int[][] map) {
		int[] memo = new int[3]; //
		for(int i=0; i<N; i++) {
			for(int c=0; c<3; c++) { //c: cur color
				int min = Integer.MAX_VALUE;
				for(int pc=0; pc<3; pc++) { //pc: past color
					if(pc==c) continue; //동일색 제외
					if(min>memo[pc]) min = memo[pc];
				}
				
				map[i][c] += min;
				}
				for(int j=0; j<3; j++) {
					memo[j] = map[i][j];
			}
		}
		for(int j=0; j<3; j++) {
			if(min>memo[j]) min = memo[j];
		}
	}
}
