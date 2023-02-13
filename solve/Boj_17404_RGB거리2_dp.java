package cote;
//2023.02.14 120ms 1등
import java.io.*;
import java.util.*;

public class Boj_17404_RGB거리2_dp {
	
	static int N;
	static int[][] map;
	static int[][] dp;
	static int[][] tdp;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		
		//입력
		N = read();
		map = new int[N][3];
		for(int i=0; i<N; i++ ) {
			for(int j=0; j<3; j++) {
				map[i][j] = read();
			}
		}
		
		//실행
		dp = new int[3][3]; //[현재색][첫번째색]
		tdp = new int[3][3];
		for(int i=0; i<3; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][i] = map[0][i];
		}
		
		for(int i=1; i<N; i++) {
			for(int c=0; c<3; c++) { //c: cur color
				Arrays.fill(tdp[c], Integer.MAX_VALUE); //tdp 초기화
				for(int fc=0; fc<3; fc++) { //fc: first color
					if(i==N-1 && c==fc) continue; //마지막 차례인데 첫 색이 같으면 제외
					int min = Integer.MAX_VALUE;
					for(int pc=0; pc<3; pc++) { //pc: past color
						if(c == pc) continue; //그 전과 동일색 제외
						if(min > dp[pc][fc]) min = dp[pc][fc];
					}
					
					if(min != Integer.MAX_VALUE) tdp[c][fc] = min + map[i][c];
				}
			}
			
			//tdp -> dp
			for(int j=0; j<3; j++) {
				dp[j] = tdp[j].clone();
			}
		}
		
		//최종답
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(ans > dp[i][j]) ans = dp[i][j];
			}
		}
		
		//출력
		System.out.println(ans);
	}
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if(c==13) System.in.read();
        return n;
    }
}
