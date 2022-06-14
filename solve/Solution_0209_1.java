package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_0209_1 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input15.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<String> list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			//위쪽
			for(int i=0; i<N/2+N%2; i++) {
				list.add(st.nextToken());
			}
			//나머지 아래쪽을 위에 셔플
			for(int i=0; i<N/2; i++) {
				list.add(2*(i+1)-1, st.nextToken());
			}
			//출력
			sb.append('#').append(tc);
			for(int i=0; i<N; i++) {
				sb.append(' ').append(list.get(i));
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
