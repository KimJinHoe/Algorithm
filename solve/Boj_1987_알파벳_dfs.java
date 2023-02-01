package cote;
//visited에 flag를 넣어서 방문처리하면 훨씬 효율적임. 여기서는 read()나 br이나 별 차이없음. 그냥 문자열은 br쓰자
import java.io.*;
import java.util.*;

public class Boj_1987_알파벳_dfs {
	
	static int R, C;
	static int SIZE;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		R = read();
		C = read();
		map = new int[R][C];
		Set<Character> set = new HashSet<Character>();
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j)-'A';
				set.add(str.charAt(j));
			}
			System.in.read();
			System.in.read();
		}
		SIZE = set.size();
		
		dfs(0, 0, 1<<map[0][0], 1);

		System.out.println(max);
	}
	
	static boolean dfs(int y, int x, int flag, int cnt) {
		if(cnt == SIZE) {
			max = SIZE;
			return true;
		}
		
		for(int d=0; d<4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(nx<0 || ny<0 || nx>=C || ny>=R) continue;
			if((flag&(1<<map[ny][nx])) != 0) continue;
			if(dfs(ny, nx, flag|(1<<map[ny][nx]), cnt+1)) return true;
		}
		if(max < cnt) {
			max = cnt;
		}
		return false;
	}
	
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if(c == 13) System.in.read();
		return n;
	}
}
