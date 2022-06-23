package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_14503_로봇청소기 {
	
	static int[] dy = {-1,0,1,0}; //북동남서. (0,1,2,3)+3 -> 3,4,5,6
	static int[] dx = {0,1,0,-1}; //(3,4,5,6)%4 -> 3,0,1,2 (방향의 왼쪽) 
	static int ans;
	static int N, M;
	static int[][] map;
	static int x;
	static int y;
	static int dir;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//실행
		run();
		
		//출력
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run() {
		int cnt = 0;
		while(true) {
			//1. 현재 위치를 청소한다.
			if(map[y][x] == 0) {
				map[y][x]= 2;
				ans++;
			}
			//2a. 현재 위치의 바로 왼쪽에 아직 청소하지 않은 빈 공간이 존재한다면,
			//왼쪽 방향으로 회전한 다음 한 칸을 전진하고 1번으로 돌아간다.
			int ny = y + dy[(dir+3)%4];
			int nx = x + dx[(dir+3)%4];
			if(map[ny][nx] == 0) {
				dir = (dir+3)%4;
				y = ny;
				x = nx;
				cnt = 0;
			}
			//2a. 그렇지 않을 경우, 왼쪽 방향으로 회전한다.
			else {
				dir = (dir+3)%4;
				cnt++;
			}
			//2b. 1번으로 돌아가거나 후진하지 않고 2a번 단계가 연속으로 네 번 실행되었을 경우,
			//바로 뒤쪽이 벽이라면 작동을 멈춘다.
			if(cnt == 4) {
				ny = y + dy[(dir+2)%4];
				nx = x + dx[(dir+2)%4];
				if(map[ny][nx] == 1) return;
				//2b. 그렇지 않다면 한 칸 후진한다.
				else {
					y = ny;
					x = nx;
					cnt = 0;
				}
			}
		}
	}
}
