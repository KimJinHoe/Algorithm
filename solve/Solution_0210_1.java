package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//SWEA 사칙연산 유효성 검사
public class Solution_0210_1 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input18.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());
			int result = 1;
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				//몇 번째인지는 불필요하니 날림
				st.nextToken();
				//유효성이 아직 유효하다면 검사
				if(result == 1) {
					//몇 개의 문자가 남았는 지 확인
					if(st.countTokens() == 3) {
						//받은 것이 숫자면 유효성 0
						if(st.nextToken().charAt(0)-'0' >= 0) result = 0;
					}
					//리프노드일 때
					else if(st.countTokens() == 1) {
						//받은 것이 기호면 유효성 0
						if(st.nextToken().charAt(0)-'0' < 0) result = 0;
					}	
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
