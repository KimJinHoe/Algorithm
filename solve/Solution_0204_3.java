package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_0204_3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][n];
			int sum = 0;
			int center = (n-1)/2;
			int status = 0;
			
			for(int i=0; i<n; i++) {
				String str = br.readLine();
				for(int j=0; j<n; j++) {
					arr[i][j] = str.charAt(j) - '0';
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(status==0 && j>=center-i && j<=center+i) {
						sum += arr[i][j];
					}
					else if(status==1 && j>=center-(n-1-i) && j<=center+(n-1-i)) {
						sum += arr[i][j];
					}
				}
				if(center-i == 0) status = 1;
			}
			sb.append("#").append(tc+1).append(" ").append(sum).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
