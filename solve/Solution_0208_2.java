package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_0208_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input10.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		//테스트 케이스 반복
		for (int tc = 1; tc <= 10; tc++) {
			int length = Integer.parseInt(br.readLine());
			LinkedList<Integer> list = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			//원본 암호문 입력
			while(st.hasMoreTokens()) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			//명령어 갯수 및 내용 입력 
			int cmd_num = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<cmd_num; i++) {
				String cmd_type = st.nextToken();
				int idx = Integer.parseInt(st.nextToken());
				int num_new = Integer.parseInt(st.nextToken());
				//명령어 실행
				for(int j=0; j<num_new; j++) {
					list.add(idx, Integer.parseInt(st.nextToken()));
					idx++;
				}
			}
			//출력
			sb.append('#').append(tc).append(' ');
			int cnt = 0;
			for(Integer i : list) {
				sb.append(i).append(' ');
				if(++cnt==10) break;
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
