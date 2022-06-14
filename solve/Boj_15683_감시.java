package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_15683_감시 {
	
	static int N, M;
	static int[][] map;
	static int[][] cctv = new int[8][3]; //[cctv넘버][종류,y좌표,x좌표]
	static int c_cnt = 0; //cctv 개수
	static int[] dy = {-1, 1, 0, 0}; //상하좌우
	static int[] dx = {0, 0, -1, 1};
	static int[] cases; //cctv가 보는 방향의 케이스 (1,3,4는 0~4의 값을 가질 수 있고 2는 0과 1, 5는 하나의 케이스인 0만을 가질 수 있음)
	static String[][] where = { //1~5번 cctv가 볼 수 있는 방향의 케이스
			{"1","2","3","4"},
			{"34", "12"},
			{"14", "24", "23", "13"},
			{"314", "142", "423", "231"},
			{"1234"}};
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		//배열 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				//cctv면 cctv배열에 저장
				if(num>=1 && num<6) {
					cctv[c_cnt][0] = num;
					cctv[c_cnt][1] = i;
					cctv[c_cnt][2] = j;
					c_cnt++;
				}
			}
		}
		//실행
		cases = new int [c_cnt];
		find_case(0);
		//출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	//모든 경우의 수를 배열에 저장하고 0을 카운트하는 함수
	static void find_case(int cnt) {
		if(cnt == c_cnt) {
			//실행
			run(-1);
			//사각지대 카운트하기
			int zero_cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) zero_cnt++;
				}
			}
			//min값 구하기
			if(zero_cnt < min) min = zero_cnt;
			//초기화
			run(1);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(cctv[cnt][0]==2 && i==2) break;
			else if(cctv[cnt][0]==5 && i==1) break;
			
			cases[cnt] = i;
			find_case(cnt+1);
		}
	}
	//각 cctv가 바라보는 방향의 경우의 수를 모두 고려하여 look를 실행시키는 함수 
	static void run(int n) {
		for(int i=0; i<c_cnt; i++) {
			int type = cctv[i][0] - 1;
			String str = where[type][cases[i]];
			for(int j=0; j<str.length(); j++) {
				int view = str.charAt(j) - '1';
				look(i, view, n);
			}
		}
	}
	//해당 방향을 보고 값을 바꾸는 함수
	static void look(int i, int view, int n) {
		int new_y = cctv[i][1];
		int new_x = cctv[i][2];
		while(true) {
			new_y += dy[view];
			new_x += dx[view];
			if(new_y >=0 && new_x >=0 && new_y < N && new_x < M) {
				if(map[new_y][new_x] == 6) break;
				else if(map[new_y][new_x] <= 0) map[new_y][new_x] += n;
			}
			else break;
		}
	}
}
