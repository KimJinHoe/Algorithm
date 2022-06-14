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

public class Boj_1194_달이차오른다 {
	
	static int ans = -1;
	static int h, w;
	static char[][] map;
	static int[] start = new int[2]; //시작점 y, x 좌표
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		for(int i=0; i<h; i++) {
			String line = br.readLine();
			for(int j=0; j<w; j++) {
				map[i][j] = line.charAt(j);
				if(line.charAt(j)=='0') {
					start[1] = j;
					start[0] = i;
				}
			}
		}
		//실행
		run();
		
		//결과
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run() {
		Queue<int[]> que = new LinkedList<int[]>();
		boolean visited[][][] = new boolean[h][w][1<<6];
		que.offer(new int[] {start[0], start[1], 0, 0}); //y좌표, x좌표, 열쇠획득여부, 총 이동 횟수
		visited[start[0]][start[1]][0] = true;
		
		while(!que.isEmpty()) {
			int size = que.size();
			
			while(size-->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				int key = cur[2];
				int cnt = cur[3];
				
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					int nkey = key;
					if(ny<0 || nx<0 || ny>=h || nx>=w) continue;
					if(visited[ny][nx][key]) continue;
					if(map[ny][nx] == '#') continue;
					if(map[ny][nx] == '1') {
						ans = cnt+1;
						return;
					}
					if('A'<=map[ny][nx] && map[ny][nx]<='F'
							&& (key&(1<<('F'-map[ny][nx]))) == 0) continue;
					if('a'<=map[ny][nx] && map[ny][nx]<='f' && (key&(1<<('f'-map[ny][nx]))) == 0) nkey = key + (1<<('f'-map[ny][nx]));
					visited[ny][nx][nkey] = true;
					que.offer(new int[] {ny, nx, nkey, cnt+1});
				}
			}
		}
	}

}
