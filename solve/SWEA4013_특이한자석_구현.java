package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA4013_특이한자석_구현 {
	
	static int[][] gear; // 각 기어의 톱니날의 값 1:S, 0:N
	static boolean[] visited;
	static int[] gp; //gearpoint. 기어의 화살표가 가르키는 인덱스
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//입력
			int K = Integer.parseInt(br.readLine());
			gear = new int[4][8];
			gp = new int[4];
			for(int i=0; i<4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					gear[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//실행
			for(int i=0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken())-1; //기어 4개의 인덱스를 0~3로 씀
				//시계방향(인덱스 줄어듦) : 입력값 1 > -1로 변환
				//반시계방향(인덱스 증가함) : 입력값 -1 > 1로 변환
				int dir = Integer.parseInt(st.nextToken())*(-1);
				visited = new boolean[4];
				run(num, dir);
			}
			int ans=0;
			for(int i=0; i<4; i++) {
				if(gear[i][gp[i]]==1) ans += (1<<i);
			}
			
			//출력
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run(int num, int dir) {
		visited[num] = true; //방문처리
		int post = (gp[num]+8-2)%8; //전 기어와 맞닿는 톱니날 인덱스
		int next = (gp[num]+2)%8; // 다음 기어와 맞닿는 톱니날 인덱스
		
		//현재 기어가 첫번째 기어가 아니고 전 기어에 방문 안했으면 전 기어 확인
		if(num!=0 && !visited[num-1]) {
			//전 기어의 날과 확인 후, 다르면 전 기어 기준 함수 실행
			if(gear[num-1][(gp[num-1]+2)%8] != gear[num][post]) run(num-1, -dir);
		}
		
		//현재 기어가 마지막 기어가 아니고 다음 기어에 방문 안했으면 다음 기어 확인
		if(num!=3 && !visited[num+1]) {
			//다음 기어의 날과 확인 후, 다르면 다음 기어 기준 함수 실행
			if(gear[num+1][(gp[num+1]+8-2)%8] != gear[num][next]) run(num+1, -dir);
		}
		
		//자신 기어 돌리기
		gp[num] = (gp[num]+dir+8)%8;
	}
}
