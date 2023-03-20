package cote;
//2023.03.21 120ms 1등 (gpwl8411코드 참고 후, 디벨롭)
import java.util.*;
import java.io.*;

public class Boj_1507_궁금한민호_Floyd_모범답안 {
	
	static int[][] map;
	static boolean[][] checked;
	static int N;
	static int ans;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		
		N = read();
		map = new int[N+1][N+1];
		checked = new boolean[N+1][N+1];
		
		//입력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] = read();
				ans += map[i][j];
			}
		}
		
		//Floyd
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(i == k) continue;
				for(int j=1; j<=N; j++) {
					if(i==j || j==k) continue;
					if(checked[i][j]) continue;
					if(map[i][j] > map[i][k] + map[k][j]) {
						System.out.println(-1);
						return;
					}
					if(map[i][j] != map[i][k] + map[k][j]) continue;
					ans -= map[i][j];
					checked[i][j] = true;
				}
			}
		}
		
		System.out.println(ans/2);
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
}
