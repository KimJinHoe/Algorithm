package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class hwalgo02_서울_12반_김진회 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int tc=0; tc<t; tc++) {
			String[] nm = br.readLine().split(" ");
			int n = Integer.parseInt(nm[0]);
			int m = Integer.parseInt(nm[1]);
			int[][] arr = new int[n][n];
			int max = 0;
			//배열 입력
			for(int i=0; i<n; i++) {
				String[] line =  br.readLine().split(" ");
				for(int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(line[j]);
				}
			}
			//sum구하기
			for(int i=0; i<n-m+1; i++) {
				for(int j=0; j<n-m+1; j++) {
					int sum = 0;
					for(int x=0; x<m; x++) {
						for(int y=0; y<m; y++) {
							sum +=arr[i+x][j+y];
						}
					}
					if(sum>max) max = sum;
				}
			}
			sb.append('#').append(tc+1).append(' ').append(max).append('\n');
		}
		System.out.println(sb.toString());
	}
}
