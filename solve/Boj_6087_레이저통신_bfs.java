package cote;
//1등. 128ms.
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_6087_레이저통신_bfs {
	
	static int[][] C = new int[2][2]; //통신기 2개의 좌표
	static int[][] map;
	static int W, H;
	static int[] dy = {-1,1,0,0}; //상하좌우
	static int[] dx = {0,0,-1,1};
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		int cnt = 0;
		for(int i=0; i<H; i++) {
			String line = br.readLine();
			for(int j=0; j<W; j++) {
				char c = line.charAt(j);
				if(c=='.') map[i][j] = -1; //길
				else if(c=='*') map[i][j] = -2; //벽
				else {
					C[cnt][0] = i;
					C[cnt][1] = j;
					if(cnt++ == 0) map[i][j] = -2; //출발지는 벽으로 처리함.
					else map[i][j] = -3; //도착지
				}
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
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] {C[0][0], C[0][1]}); //y좌표, x좌표, 거울 수
		int mirror = -1;
		while(!que.isEmpty()) {
			int size = que.size();
			mirror++;
			while(size-->0) {
				int[] cur = que.poll();
				
				for(int d=0; d<4; d++) {
					int ny = cur[0];
					int nx = cur[1];
					while(true) {//한 방향 탐색
						ny += dy[d];
						nx += dx[d];
						if(ny<0 || nx<0 || ny>=H || nx>=W) break; //범위
						if(map[ny][nx] == mirror) continue; //이미 방문한 곳이지만 자기와 거울 갯수가 같으면 그 방향은 탐색함
						if(map[ny][nx] >= 0) break; //이미 방문한 곳이면 한 방향 탐색 종료
						if(map[ny][nx] == -1) { //길
							map[ny][nx] = mirror;
							que.offer(new int[] {ny, nx});
						}
						else if(map[ny][nx] == -2) break; //벽이면 한 방향 탐색 종료
						else if(map[ny][nx] == -3) { //도착지
							ans = mirror;
							return;
						}
					}
				}
			}
		}
	}
}