package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_2605_줄세우기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input14.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int num = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] student = new int[num];
		ArrayList<Integer> result = new ArrayList<>();
		//입력
		for(int i=0; i<num; i++) {
			student[i] = Integer.parseInt(st.nextToken());
			result.add(i-student[i], i+1);
		}
		
		for(int a : result) {
			sb.append(a).append(' ');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
