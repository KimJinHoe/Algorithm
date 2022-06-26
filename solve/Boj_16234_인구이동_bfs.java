package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16234_인구이동_bfs {
	
	static int N, L, R;
	static int[][] map;
	static Queue<int[]> que;
	static int[][] visited;
	static int[][] memo;
	static int[] dy = {-1,1,0,0}; //상하좌우
	static int[] dx = {0,0,-1,1};
	
	static int ans;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/test/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//실행
		bfs();
		
		//출력
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void bfs() {
		que = new LinkedList<int[]>();
		visited = new int[N][N];
		memo = new int[N*N+1][2]; //[0]:마을 수가 몇개인지, [1]:인구총합
		int union = 1;
		
		//국경선 오픈
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j] != 0) continue; //방문했으면 패스
				que.offer(new int[] {i, j});
				visited[i][j] = union;
				memo[union][0]++;
				memo[union][1] += map[i][j];
				
				while(!que.isEmpty()) {
					int[] cur = que.poll();
					int y = cur[0];
					int x = cur[1];
					
					for(int d=0; d<4; d++) {
						int ny = y + dy[d];
						int nx = x + dx[d];
						if(ny<0 || nx<0 || ny>=N || nx>=N) continue; //범위 내
						if(visited[ny][nx] != 0) continue; //방문처리
						if(Math.abs(map[y][x]-map[ny][nx])< L || Math.abs(map[y][x]-map[ny][nx])>R) continue; //인구수 차이 검사
						que.offer(new int[] {ny, nx});
						visited[ny][nx] = union;
						memo[union][0]++;
						memo[union][1] += map[ny][nx];
					}
				}
				union++;
			}
		}
		if(union == N*N+1) return; //연합이 없으면 종료
		
		//평균 인구수 계산
		for(int i=1; i<union; i++) {
			memo[i][1] /= memo[i][0];
		}
		
		//인구이동
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = memo[visited[i][j]][1];
			}
		}
		
		ans++;
		bfs();
	}
	
}