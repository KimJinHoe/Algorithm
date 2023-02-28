package cote;
// 출발점 0으로 갈 수 있는 도시의 비트(CANBIT)를 0110이고 flag가 1110이면
// (0110&1110) == 0110로 이미 출발점으로 돌아갈 수 있는 도시를 다 지남 -> 갈 수 없음
import java.util.*;
import java.io.*;

public class Boj_2098_외판원순회_dp {
	
	static int CANTGO = 17000001; //큰 수
	static int INIT = 20000000; //CANTGO보다 큰 수
	static int N;
	static int[][] map;
	static int[][] dp;
	static int R;
	static int CANBIT;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		R = (1<<N)-1;
		map = new int[N][N];
		dp = new int[N][R];
		
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], INIT);
			for(int j=0; j<N; j++) {
				map[i][j] = read();
				if(j == 0 && map[i][j] != 0) CANBIT |= 1<<i;
			}
		}
		System.out.println(dfs(0, 1));
	}
	
	static int dfs(int cur, int flag) {
		if(flag == R) {
			return map[cur][0];
		}
		
		if((CANBIT&flag) == CANBIT) return CANTGO;
		if(dp[cur][flag] != INIT) return dp[cur][flag];
		
		for(int i=1; i<N; i++) {
			if((flag&1<<i) != 0 || map[cur][i] == 0) continue;
			dp[cur][flag] = Math.min(dp[cur][flag], dfs(i, flag|1<<i) + map[cur][i]);
		}
		return dp[cur][flag];
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
