package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Boj_10026_적록색약_사방bfs {
	
	static int[] dy = {-1,1,0,0}; //상하좌우
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		//배열입력
		char[][] map = new char[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		//출력
		sb.append(bfs(N, map, 0)).append(' ').append(bfs(N, map, 1));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int bfs(int N, char[][] map, int flag) {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> que = new LinkedList<>();
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//이미 방문 했으면 패스
				if(visited[i][j]) continue;
				
				//안했으면 큐에 넣어서 bfs실행
				que.offer(new int[] {i,j});
				visited[i][j] = true;
				
				//더 이상 같은 구역이 없을 때까지 실행
				while(!que.isEmpty()) {
					int[] xy = que.poll();
					int y = xy[0];
					int x = xy[1];
					char color = map[y][x];
					//적록색맹이면 녹색을 적색이라 침
					if(flag==1 && color=='G') color = 'R';
					//사방탐색
					for(int dir=0; dir<4; dir++) {
						int ny = y + dy[dir];
						int nx = x + dx[dir];
						//범위 밖이면 패스
						if(ny>=N || nx>=N || ny<0 || nx<0) continue;
						//적록색맹이면 녹색을 적색이라 침
						int ncolor = map[ny][nx];
						if(flag==1 && ncolor=='G') ncolor = 'R';
						//같은 색이고 방문 안했으면
						if(ncolor == color && !visited[ny][nx]) {
							visited[ny][nx] = true;
							que.offer(new int[] {ny, nx});
						}
					}
				}
				//무사히 끝났으면 구역 카운트 +1
				cnt++;
			}
		}
		return cnt;
	}
}
