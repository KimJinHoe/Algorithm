package cote;
//2023.01.30 120ms 2등
import java.io.*;
import java.util.*;

public class Boj_17070_파이프옮기기1_구현 {
	
	static int N;
	static boolean[][] map;
	static int[][][] way;
	
	public static void main(String[] args) throws Exception {
		//입력
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		map = new boolean[N][N];
		way = new int[N][N][3]; //가로, 대각선, 세로
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(read() == 1) {
					map[i][j] = true;
				}
			}
		}
		//(0,0)을 제외한 맨윗줄은 벽이 없다면 가로로만 도착할 수 있음
		//(i,0)와 (i,1)은 무조건 갈 수 없음
		//이걸 미리 처리해주면 범위검사 안해도 됨
		for(int i=1; i<N; i++) {
			if(map[0][i]) break;
			way[0][i][0] = 1;
		}
		
		//실행
		for(int i=1; i<N; i++) {
			for(int j=2; j<N; j++) {
				if(map[i][j]) continue; //현위치가 벽이면 패스
				way[i][j][0] = way[i][j-1][0] + way[i][j-1][1]; //가로=가로+대각선
				way[i][j][2] = way[i-1][j][1] + way[i-1][j][2]; //세로=대각선+세로
				if(map[i-1][j-1] || map[i-1][j] || map[i][j-1]) continue; //대각선=가로+대각선+세로
				way[i][j][1] = way[i-1][j-1][0] + way[i-1][j-1][1] + way[i-1][j-1][2];
			}
		}
		
		//출력
		System.out.println(way[N-1][N-1][0] + way[N-1][N-1][1] + way[N-1][N-1][2]);
		
	}

	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13)
			System.in.read();
		return n;
	}
}
