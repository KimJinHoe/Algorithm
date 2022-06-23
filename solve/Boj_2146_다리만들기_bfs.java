package Algorithm.solve;
//172ms. 3등. @kku64r
//1. bfs로 각 섬마다 섬코드를 넣음 (섬코드를 2부터 넣어서 기존 1값과 구분. 방문처리 visited 배열이 필요가 없음)
//2. 각 섬의 가장자리에서 bfs를 돌려 다리를 생성
//3. 섬코드가 다른 땅과 만나면 다리길이 값을 합쳐 답 출력 (que의 size는 다 돌려 최소의 길이값을 구함)

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2146_다리만들기_bfs {
	
	static int N;
	static int[][] map;
	static int[][] val; //다리 길이
	static int min = Integer.MAX_VALUE;;
	static int[] dy = {-1,1,0,0}; //상하좌우
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		val = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//실행
		run();
		
		//출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run() {
		Queue<int[]> que = new LinkedList<int[]>(); //가장자리 땅에서부터 다리를 놓을 큐
		
		//섬의 섬코드 표기
		int code = 2;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 1) continue; //이미 체크된 땅이거나 바다면 패스
				bfs_code(i, j, code++, que);
			}
		}
		
		//다리 놓기
		int depth = 1; //다리 길이
		boolean flag = false;
		while(!que.isEmpty()) {
			int size = que.size();
			depth++;
			if(flag) break; //연결된 다리가 있으면 정지
			
			while(size-->0) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];
				
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny<0 || nx<0 || ny>=N || nx>=N) continue; //범위 내
					if(map[ny][nx] == 0) { //바다면 다리를 놓음
						map[ny][nx] = map[y][x]; //섬코드 입력
						val[ny][nx] = depth; //다리 길이 입력
						que.offer(new int[] {ny, nx});
					}
					else if(map[ny][nx] != map[y][x]) { //다른 섬의 코드면 연결되는 것
						if(min > val[ny][nx] + val[y][x]) {
							min = val[ny][nx] + val[y][x];
							flag = true;
						}
					}
				}
			}
		}
		
	}
	
	static void bfs_code(int i, int j, int code, Queue<int[]> que) {
		Queue<int[]> que_code = new LinkedList<int[]>(); //섬의 코드를 입력하기 위한 큐
		que_code.offer(new int[] {i, j});
		map[i][j] = code;
		
		while(!que_code.isEmpty()) {
			int[] cur = que_code.poll();
			
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dy[d];
				int nx = cur[1] + dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=N) continue; //범위 내
				if(map[ny][nx] == 0) { //바다면 길이 1의 다리를 놓음
					que.offer(new int[] {ny, nx});
					map[ny][nx] = code; //섬코드 입력
					val[ny][nx] = 1;
				} 
				else if(map[ny][nx] == 1) { //아직 체크안된 땅이면 계속 진행
					que_code.offer(new int[] {ny, nx});
					map[ny][nx] = code; //섬코드 입력
				}
			}
		}
	}
}
