package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1205_등수구하기_구현 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int ans = -1;
		//N=0이면 다음 줄 안 읽음
		int[][] arr = new int[P][2]; //값,랭크
		for(int i=0; i<P; i++) {
			Arrays.fill(arr[i], -1);
		}
		//기존 배열 입력
		if(N == 0) ans = 1;
		else if(N != 0) {
			st = new StringTokenizer(br.readLine());
			int rank=1;
			for(int i=0; i<N; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				if(i!=0 && arr[i-1][0]!=arr[i][0]) rank = i+1;
				arr[i][1] = rank;
			}
		}
		//실행
		for(int i=0; i<P; i++) {
			if(S > arr[i][0]) {
				if(i==0) ans = 1;
				else if(S==arr[i-1][0]) ans = arr[i-1][1];
				else ans = i+1;
				break;
			}
		}
		//출력
		System.out.println(ans);
	}
}
