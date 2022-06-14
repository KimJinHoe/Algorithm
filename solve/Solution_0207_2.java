package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_0207_2 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input7.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=0; tc<10; tc++) {
			int length = Integer.parseInt(br.readLine());
			String str = br.readLine();
			int answer = 1 ;
			int[] results = new int[4];;
			for(int i=0; i<length; i++) {
				char c = str.charAt(i);
				if(c == '(') results[0] ++;
				else if(c== ')') results[0] --;
				else if(c== '[') results[1] ++;
				else if(c== ']') results[1] --;
				else if(c== '{') results[2] ++;
				else if(c== '}') results[2] --;
				else if(c== '<') results[3] ++;
				else if(c== '>') results[3] --;
			}
			
			for(int i=0; i<results.length; i++) {
				if(results[i] != 0) answer=0;
			}
			sb.append('#').append(tc+1).append(" ").append(answer).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
        bw.close();
        br.close();
	}
}
