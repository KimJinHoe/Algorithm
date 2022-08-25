package algo;
import java.util.*;
import java.io.*;

public class Boj_18428_감시피하기 {
	
	static int N;
	static char[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean ans;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/algo/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		//입력
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		//실행
		dfs(0, 0, 0);
		
		//출력
		if(ans) bw.write("YES");
		else bw.write("NO");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int y, int x, int cnt) {
		if(ans) return; //가능한 경우를 이미 찾았으면 종료
		if(cnt == 3) {
			//맵에서 T를 찾아서 감시안걸리는지 체크
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++){
					if(map[i][j] != 'T') continue;
					if(check(i, j)) continue; //통과
					else return; //걸림
				}
			}
			ans = true;
			return;
		}
		
		for(int j=x+1; j<N; j++) {
			if(map[y][j] != 'X') continue;
			map[y][j] = 'O';
			dfs(y, j, cnt+1);
			map[y][j] = 'X';
		}
		
		for(int i=y+1; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 'X') continue;
				map[i][j] = 'O';
				dfs(i, j, cnt+1);
				map[i][j] = 'X';
			}
		}
	}
	
	static boolean check(int y, int x) {
		for(int d=0; d<4; d++) {
			int ny = y;
			int nx = x;
			while(true) {
				ny += dy[d];
				nx += dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=N) break; //통과(범위밖)
				if(map[ny][nx] == 'S') return false; //감시 걸림
				if(map[ny][nx] == 'O') break; //통과
				if(map[ny][nx] == 'T') break; //통과
			}
		}
		return true; //성공
	}
}
