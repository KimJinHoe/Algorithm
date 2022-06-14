package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_0209_1_배열 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input15.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			//위쪽 카드 수
			int upside = N/2+N%2;
			
			//위쪽 카드 arr에 저장
			String[] arr = new String[upside];
			for(int i=0; i<upside; i++) arr[i] = st.nextToken();
			
			//출력
			sb.append('#').append(tc);
			for(int i=0; i<N/2; i++) sb.append(' ').append(arr[i]).append(' ').append(st.nextToken());
			
			//홀수면 하나 더
			if(N%2==1) sb.append(' ').append(arr[upside-1]);
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
