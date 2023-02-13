package cote;
//2023.02.14 648ms 2등
//범위조건을 쓰지말고 외각패딩을 사용하면 더 효율적임 (메모리 조금을 더 사용하고 범위조건 4개 연산을 안해도 됨.
import java.io.*;
import java.util.*;

public class Boj_16946_벽부수고이동하기4_bfs {
	
	static int N, M;
	static int[][] map;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int[] arr = new int[1000001];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//입력
		N = read();
		M = read();
		map = new int[N+2][M+2];
		Arrays.fill(map[0], 1);
		Arrays.fill(map[N+1], 1);
		for(int i=1; i<=M; i++) {
			map[i][0] = 1;
			map[i][M+1] = 1;
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				map[i][j] = System.in.read() - '0';
			}
			System.in.read();
			System.in.read(); //windows에서만
		}
		
		//bfs실행
		bfs();
		
		//벽계산 및 출력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] != 1) {
					sb.append(0);
					continue;
				}
				
				Set<Integer> set = new HashSet<Integer>(); //그룹 중복 더하기 방지
				for(int d=0; d<4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					if(map[ny][nx] >= 0) continue;
					if(set.contains(map[ny][nx])) continue;
					map[i][j] += arr[-map[ny][nx]];
					set.add(map[ny][nx]);
				}
				sb.append(map[i][j]%10);
			}
			sb.append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void bfs() {
		Queue<int[]> que = new LinkedList<int[]>();
		int groupNum = -1;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] != 0) continue;
				que.offer(new int[] {i, j});
				map[i][j] = groupNum;
				int cnt = 1;
				
				while(!que.isEmpty()) {
					int[] cur = que.poll();
					
					for(int d=0; d<4; d++) {
						int ny = cur[0] + dy[d];
						int nx = cur[1] + dx[d];
						if(map[ny][nx] != 0) continue;
						map[ny][nx] = groupNum;
						que.offer(new int[] {ny, nx});
						cnt++;
					}
				}
				
				arr[-groupNum] = cnt;
				groupNum--;
			}
		}
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while((c=System.in.read())>32) n = (n<<3) + (n<<1) + (c&15);
		if(c==13) System.in.read();
		return n;
	}
}
