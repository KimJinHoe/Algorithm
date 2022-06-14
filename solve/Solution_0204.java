package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_0204 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=0; tc<10; tc++) {
			//dump 제한 수 입력
			int set_cnt = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			int cnt = 0;
			int[] arr = new int[100];
			//배열 입력
			for(int i=0; i<100; i++) {
				arr[i] = Integer.parseInt(str[i]);
			}
			//dump실행 후, 최대-최소 cnt에 입력
			cnt = dump(arr, set_cnt);
			sb.append("#").append(tc+1).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static int dump(int[] arr, int set_cnt) {
		for(int i=0; i<set_cnt; i++) {
			Arrays.sort(arr);
			if(arr[99]-arr[0] <= 1) break;
			arr[99]--;
			arr[0]++;
		}
		Arrays.sort(arr);
		return arr[99] - arr[0];
	}
	
}
