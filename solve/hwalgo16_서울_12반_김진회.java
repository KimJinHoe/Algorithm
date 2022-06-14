package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//SWEA7465
public class hwalgo16_서울_12반_김진회 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] person = new int[N];
			int cnt = 0;
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int pa = person[a];
				int pb = person[b];
				//전부 속하지 않으면 새로운 무리를 만듦
				if(pa==0 && pb==0) {
					cnt++;
					person[a] = cnt;
					person[b] = cnt;
				}
				
				//하나만 무리에 속하지 않으면 무리에 속해줌
				else if(pa*pb==0 && pa+pb>=1) {
					if(pa==0) person[a] = pb;
					else person[b] = pa;
				}
				
				//전부 무리가 있으면 한 무리를 다른 무리에 합침
				else {
					for(int i=0; i<N; i++) {
						if(person[i] == pb) person[i] = pa;
					}
				}
			}
			
			//중복되지 않게 Hashset에 person배열 넣기
			//어느 무리에도 속하지 않는 사람들 따로 cnt
			cnt = 0;
			Set<Integer> set = new HashSet<Integer>();
			for(int i=0; i<N; i++) {
				if(person[i] == 0) cnt++;
				else set.add(person[i]);
			}
			cnt += set.size();
			//출력
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
