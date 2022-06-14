package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//정올 1828
public class hwalgo11_서울_12반_김진회 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input25.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[][] c = new int[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			c[i][0] = Integer.parseInt(st.nextToken());
			c[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(c, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}			
		});
		
		int cnt = 1;
		int max = c[0][1];
		
		for(int i=1; i<N; i++) {
			if(max < c[i][0]) {
				max = c[i][1];
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
