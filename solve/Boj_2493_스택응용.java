package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_2493_스택응용 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input8.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<int[]> top = new Stack<>();

		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			while(!top.isEmpty()) {
				if(top.peek()[0] < n)
					top.pop();
				else {
					sb.append(top.peek()[1]).append(' ');
					break;
				}
			}
			
			if(top.empty()) sb.append("0 ");
			top.push(new int[] {n, i+1});
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}