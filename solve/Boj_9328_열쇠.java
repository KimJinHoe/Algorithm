package cote;
//2023.02.18 172ms 1등
//@kku64r
//아이디어 : bfs
//문을 만나면, 열쇠가 있는지 보고, 없으면 미리 생성된 대기 큐(26개)에 문 좌표를 삽입, 키를 얻으면 해당 알파벳의 대기 큐를 메인 큐에 삽입
// 2중 외각 패딩 : '외각1 .패딩' + '외각2 * 패딩'
import java.util.*;
import java.io.*;

public class Boj_9328_열쇠 {
	
	static int T;
	static int H, W;
	static char[][] map;
	static boolean[][] visited;
	static boolean[] keys;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = read();
		
		//tc반복
		while(T-->0) {
			H = read();
			W = read();
			map = new char[H+4][W+4];
			visited = new boolean[H+4][W+4];
			keys = new boolean[26];
			
			//맵 초기화 (2중 외각 패딩)
			Arrays.fill(map[0], '*');
			Arrays.fill(map[H+3], '*');
			Arrays.fill(map[1], '.');
			Arrays.fill(map[H+2], '.');
			for(int i=1; i<H+3; i++) {
				map[i][0] = '*';
				map[i][W+3] = '*';
				map[i][1] = '.';
				map[i][W+2] = '.';
			}
			
			for(int i=2; i<H+2; i++) {
				for(int j=2; j<W+2; j++) {
					map[i][j] = (char) System.in.read();
				}
				System.in.read();
				System.in.read(); //윈도우에서만
			}
			
			//사전에 갖고 있던 열쇠 저장
			int c;
			while((c = System.in.read()) > 32) {
				if(c != '0') keys[c-'a'] = true;
			}
			System.in.read(); //윈도우에서만

			sb.append(bfs()).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int bfs() {
		Queue<int[]> que = new LinkedList<int[]>(); //메인 큐
		Queue<int[]>[] doorQue = new LinkedList[26]; //문 대기 큐
		for(int i=0; i<26; i++) {
			doorQue[i] = new LinkedList<int[]>();
		}
		
		//시작점 삽입 (2중 패딩으로 인해 편의성 향상. 벽패딩:범위조건 연산 불필요. 길패딩(시작점 1,1로 통일 가능)
		que.offer(new int[] {1, 1});
		visited[1][1] = true;
		
		int result = 0;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dy[d];
				int nx = cur[1] + dx[d];
				
				if(visited[ny][nx]) continue;
				char c = map[ny][nx];
				if(c == '*') continue;
				if(c >= 'a') {
					keys[c-'a'] = true;
					//대기큐에 있던 좌표들 메인큐에 전부 삽입
					while(!doorQue[c-'a'].isEmpty()) que.offer(doorQue[c-'a'].poll());
				}
				else if(c >= 'A') {
					//열쇠 있으면 메인큐 삽입, 없으면 대기큐 삽입
					if(keys[c-'A']) que.offer(new int[] {ny, nx});
					else doorQue[c-'A'].offer(new int[] {ny, nx});
					visited[ny][nx] = true;
					continue;
				}
				else if(c == '$') result++;
				//., $, a~z만 여기로 옴
				que.offer(new int[] {ny, nx});
				visited[ny][nx] = true;
			}
		}
		
		return result;
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return n;
	}
}
