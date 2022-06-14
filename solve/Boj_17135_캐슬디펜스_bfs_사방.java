package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17135_캐슬디펜스_bfs_사방 {
	
	static int N, M, D;
	static int max = Integer.MIN_VALUE;
	static int[] archer = new int[3];
	static int[] dy = {0,-1,0}; //좌상우
	static int[] dx = {-1,0,1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num == 1) total++;
			}
		}
		//실행
		comb(0, 0, total, map);

		//출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void comb(int cnt, int start, int total, int[][] map) {
		if(cnt == 3) {
			//map의 깊은 복사
			int[][] temp = new int[N][M];
			for(int i=0; i<N; i++) {
				temp[i] = map[i].clone();
			}
			//해당 궁수 조합으로 게임실행
			int kill_cnt = run(total, temp);
			if(max < kill_cnt) max = kill_cnt;
			return;
		}
		
		for(int i=start; i<M; i++) {
			archer[cnt] = i;
			comb(cnt+1, i+1, total, map);
		}
	}
	
	static int run(int total, int[][] map) {
		int kill_cnt=0;
		int[][] target = new int[3][2]; // 각 궁수의 타깃 좌표값
		while(total!=0) {
			//타겟찾기
			for(int num=0; num<3; num++) {
				target[num] = bfs(archer[num], map);
			}
			
			//타겟 죽이기
			for(int num=0; num<3; num++) {
				if(target[num]==null) continue; //사정거리에 닿는 적이 없으면 패스
				int y = target[num][0];
				int x = target[num][1];
				//적이 있었는데 죽였으면 (타겟겹쳐서 뒤늦게 죽인 것 방지)
				if(map[y][x]==1) {
					map[y][x] = 0;
					kill_cnt++;
					total--;
				}
			}
			//적을 쏴서 전부 없앴으면 끝
			if(total==0) return kill_cnt;
			
			//아래로 이동
			for(int j=0; j<M; j++) { //가장 아래줄 이동
				if(map[N-1][j]==1) {
					total--;
					//이동해서 적이 전부 사라지면 끝
					if(total==0) return kill_cnt;
				}
				map[N-1][j]=map[N-2][j];
			}
			for(int i=N-2; i>0; i--) { //중간줄 이동
				for(int j=0; j<M; j++) {
					map[i][j]=map[i-1][j];
				}
			}
			for(int j=0; j<M; j++) { //가장 윗 줄은 0으로
				map[0][j] = 0;
			}
			
		}
		return kill_cnt;
	}
	
	static int[] bfs(int num, int[][] map) {
		//바로 위에 적이 있으면 즉시 종료
		if(map[N-1][num] == 1) return new int[] {N-1, num};
		Queue<int[]> que = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		que.offer(new int[] {N-1, num});
		visited[N-1][num] = true;
		
		int DD = D-1; //사정거리
		while(DD-->0) {
			int size = que.size();
			
			while(size-->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				
				//사방탐색
				for(int d=0; d<3; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny<0 || nx<0 || ny>=N || nx>=M) continue; //범위탐색
					if(map[ny][nx] == 1) return new int[] {ny, nx}; //적이 있으면 즉시 종료
					if(visited[ny][nx]) continue; //방문확인
					//적이 없으면 큐에 넣고 다시 탐색
					que.offer(new int[] {ny, nx});
					visited[ny][nx] = true;
				}
			}
		}
		return null;
	}
}
