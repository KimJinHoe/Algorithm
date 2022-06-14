package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_17143_낚시왕_구현 {
	
	static int R,C,M;
	static int[] dy = {0,-1,1,0,0};
	static int[] dx = {0,0,0,1,-1}; //0상하우좌
	static int[][] map;
	static int[][] shark;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			Arrays.fill(map[i], -1);
		}
		M = Integer.parseInt(st.nextToken());
		shark = new int[M][5];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken())-1;
			int num2 = Integer.parseInt(st.nextToken())-1;
			map[num1][num2] = i;
			shark[i][0] = num1;
			shark[i][1] = num2;
			for(int j=2; j<5; j++) {
				shark[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 출력
		sb.append(run());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int run() {
		int ans = 0;
		boolean[] iscatch = new boolean[M];
		for(int j=0; j<C; j++) { //1. 낚시왕이 오른쪽으로 한 칸 이동
			//2. 상어잡기
			for(int i=0; i<R; i++) {
				if(map[i][j]==-1) continue; //상어가 없으면 패스
				ans += shark[map[i][j]][4]; //잡은 상어의 크기를 더하고
				iscatch[map[i][j]] = true; //잡았다고 표시
				map[i][j] = -1; //상어를 없앰
				break;
			}
			
			for(int i=0; i<M; i++) {
				if(iscatch[i]) continue;
				map[shark[i][0]][shark[i][1]] = -1;
			}
			
			//3.상어 이동
			for(int num=0; num<M; num++) {
				if(iscatch[num]) continue; //잡힌 상어면 패스
				int y = shark[num][0];
				int x = shark[num][1];
				
				//방향에 따른 이동
				int d = shark[num][3];
				if(d/3==0) { //상하
					int s = shark[num][2]%(2*(R-1)); //2(R-1)만큼 이동하면 제자리
					for(int k=0; k<s; k++) {
						if(y==0) d = 2;
						else if(y==R-1) d = 1;
						y += dy[d];
					}
				} else { //우좌
					int s = shark[num][2]%(2*(C-1)); //2(C-1)만큼 이동하면 제자리
					for(int k=0; k<s; k++) {
						if(x==0) d = 3;
						else if(x==C-1) d = 4;
						x += dx[d];
					}
				}
				
				//상어정보 갱신
				shark[num][0] = y;
				shark[num][1] = x;
				shark[num][3] = d;
				
				//다른 상어와 겹쳤는데
				if(map[y][x] != -1) {
					//작으면 잡아먹힘
					if(shark[num][4]<shark[map[y][x]][4]) {
						iscatch[num] = true;
						continue;
					//크면 그 전껄 잡아먹음
					} else iscatch[map[y][x]] = true;
				}
				//아니면 갱신
				map[y][x] = num;
			}
		}
		return ans;
	}
}
