package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//SWEA 9229
public class hwalgo04_서울_12반_김진회 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input11.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			int max = -1;
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					int sum = arr[i]+arr[j];
					if(sum<=M && sum>max) max = sum; 
				}
			}
			
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}