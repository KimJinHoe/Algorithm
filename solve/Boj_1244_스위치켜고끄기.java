package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1244_스위치켜고끄기 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//스위치 배열 입력받기
		int N = Integer.parseInt(br.readLine());
		int[] switchs = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			switchs[i] = Integer.parseInt(st.nextToken());
		
		//학생 수 만큼 실행
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			//남학생이면
			if(sex == 1) {
				for(int j=1; j<=N; j++) {
					if(j%num==0)
						switchs[j] ^= 1;
				}
			}
			
			//여학생이면
			else {
				switchs[num] ^= 1;
				int cnt = 0;
				while(num-cnt > 1 && num+cnt < N) {
					cnt++;
					if(switchs[num-cnt] == switchs[num+cnt]) {
						switchs[num-cnt] ^= 1;
						switchs[num+cnt] ^= 1;
					}
					else break;
				}
			}
		}
		
		//출력
		int cnt = 0;
		while(++cnt < N+1) {
			sb.append(switchs[cnt]).append(' ');
			if(cnt%20 == 0) sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
