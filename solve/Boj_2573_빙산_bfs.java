package cote;
//2023.03.18 392ms 3등
import java.io.*;
import java.util.*;

public class Boj_2573_빙산_bfs {

	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int[][] map;
	static int N, M;
	
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		Queue<int[]> que = new LinkedList<int[]>(); //sea que
		Queue<int[]> ique = new LinkedList<int[]>(); //ice que
		
		N = read();
		M = read();
		map = new int[N+2][M+2]; //외각패딩
		int cnt = 0; //빙산개수
		
		//1. map 입력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				int num = read();
				if(num == 0) continue; //바다면 패스
				ique.add(new int[] {i, j});
				map[i][j] = num;
				cnt++;
			}
		}
		
		//2. 빙산 주변 바다 파악
		int size = ique.size();
		while(size-->0) {
			int[] cur = ique.poll();
			
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dy[d];
				int nx = cur[1] + dx[d];
				if(map[ny][nx] != 0) continue;
				que.add(new int[] {ny, nx});
				map[ny][nx] = -1; //중복으로 세면 안되니 -1로 처리
			}
			ique.add(cur); //또 써야하니 다시 넣기
		}
		
		//3. bfs 실행
		int time = 0;
		while(!que.isEmpty()) {
			
			//3-1) 분리됐는지 판별
			while(!ique.isEmpty()) {
				int[] cur = ique.poll();
				if(map[cur[0]][cur[1]] <= 0) continue; //바다면 continue
				ique.add(cur); //빙산이면 또 써야하니 다시 넣기
				
				//빙산을 bfs로 돌아서 분리됐는지 판별
				Queue<int[]> cque = new LinkedList<int[]>(); //check큐
				boolean[][] checked = new boolean[N+2][M+2];
				cque.add(cur);
				checked[cur[0]][cur[1]] = true;
				int ccnt = 1;
				
				while(!cque.isEmpty()) {
					int[] ccur = cque.poll();
					
					for(int d=0; d<4; d++) {
						int ny = ccur[0] + dy[d];
						int nx = ccur[1] + dx[d];
						if(map[ny][nx] <= 0) continue;
						if(checked[ny][nx]) continue;
						checked[ny][nx] = true;
						cque.add(new int[] {ny, nx});
						ccnt++;
					}
				}
				
				if(ccnt == cnt) break; //아직 분리안됨 -> 계속 진행. 분리되면 종료
				System.out.println(time);
				return;
			}
			
			time++;
			
			//3-2) 빙산 녹이기
			size = que.size();
			while(size-->0) {
				int[] cur = que.poll();
				
				int flag = 0;
				for(int d=0; d<4; d++) {
					int ny = cur[0] + dy[d];
					int nx = cur[1] + dx[d];
					
					//해당 바다 주변에 빙산이 있으면
					if(map[ny][nx] > 0) {
						flag++;
						if(--map[ny][nx] != 0) continue; //빙산 녹인 후, 여전히 빙산인지 판별. 바다됐으면 큐에 추가.
						que.add(new int[] {ny, nx});
						flag--;
						cnt--; //빙산 개수 감소
					}
				}
				if(flag != 0) que.add(cur); //아직 녹일 빙산이 있으면 큐에 다시 넣음
			}
			
			//3-3) 빙하가 없으면 0
			if(cnt == 0) {
				System.out.println(0);
				return;
			}
			
		}
    }
    
	static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    boolean isNegative = n == 13;
	    if (isNegative) n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if(c == 13) System.in.read();
	    return isNegative ? ~n + 1 : n;
	}

}