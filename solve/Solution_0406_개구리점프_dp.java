package Algorithm.solve;
/*
7 3
1 0 0 
1 2 2
0 3 0
3 -10 -5
15 -10 50
15 -10 10
0 6 4
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_0406_개구리점프_dp {
	
	static int[] dx = {-1,0,1};
	static int H,W;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int[][] map = new int[H][W];
		int[][] memo = new int[H][W];
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("--------------------------");
		run(map, memo);
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(memo[i][j]==Integer.MIN_VALUE)System.out.print(0+" ");
				else System.out.print(memo[i][j]+" ");
			}
			System.out.println();
		}
		sb.append(memo[H-1][W-1]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run(int[][] map, int[][] memo) {
		memo[0]=map[0];
		for(int i=1; i<H; i++) {
			for(int j=0; j<W; j++) {
				memo[i][j] = Integer.MIN_VALUE;
				if(map[i][j]==0) continue;
				for(int d=0; d<3; d++) {
					int ny = i - 1;
					int nx = j + dx[d];
					if(ny<0 || nx<0 || nx>=W) continue;
					if(map[ny][nx] == 0) continue;
					if(memo[i][j]<map[i][j]+memo[ny][nx]) memo[i][j] = map[i][j]+memo[ny][nx];
				}
			}
		}
	}
}
