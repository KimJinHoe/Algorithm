package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1767_프로세서연결하기_부분집합 {

	static int N;
	static int[][] map;
	static int min;
	static int max;
	static List<int[]> list;
	static int[] dr = {-1,1,0,0}; //상하좌우
	static int[] dc = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE; //최소 전선 총합
			max = Integer.MIN_VALUE; //최대 연결 코어 수
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1) {
						map[i][j] = num;
						 // 가장자리 코어는 리스트에 추가 안 함
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;
						list.add(new int[] {i, j});
					}
				}
			}

			// 실행
			go(0, 0);
			// 출력

			sb.append('#').append(tc).append(" ").append(min).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();  
		bw.close();
		br.close();
	}

private static void go(int index,int cCnt) { // 부분집합으로 코어 전선놓기 시도 , cCnt: 전원과 연결된 코어수
		
		if(index == list.size()) {
			int res = getLength();
			if(max<cCnt) {
				max = cCnt;
				min = res;
			}else if(max == cCnt) { // 최대 연결 코어수가 같다면
				if(min>res) min = res;
			}
			return;
		}
		
		int[] core = list.get(index);
		int r = core[0];
		int c = core[1];
		// 전선을 놓아보기(4방향으로)
		for (int d = 0; d < 4; d++) {
			if(isAvailable(r, c, d)) { // 현재 코어의 r,c 위치에서 d방향으로 전선을 놓을 수 있다면
				setStatus(r, c, d, 2); //전선 놓기
				go(index+1, cCnt+1);
				setStatus(r, c, d, 0); // 전선 지우기
			}
		}
		// 전선 놓지 않기
		go(index+1, cCnt);
	}
	private static boolean isAvailable(int r,int c ,int d) { // r,c 위치에서 d 방향으로 전선을 놓을수 있는지 체크
		int nr=r, nc=c;
		while (true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break;
			//다른 코어나 전선을 만나면 return false;
			if(map[nr][nc]>=1) return false;
		}
		return true;
	}
	private static void setStatus(int r,int c,int d, int s) {// r,c 위치에서 d방향으로 전선을 놓거나(2) 지우거나(0) 
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break;
			map[nr][nc] = s;
		}
	}
	private static int getLength() { // 놓아진 전선의길이의 합 계산
		int lCnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c]==2) lCnt++;
			}
		}
		return lCnt;
	}
}
