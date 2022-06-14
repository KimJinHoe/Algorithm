package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class hwalgo01_서울_12반_김진회 {
	static int x; //x, y좌표
	static int y;
	static int status; // 진행방향(dx, dy) 결정
	static int cnt; //대입할 숫자
	static int[] dy = {0, 1, 0, -1}; //우,하,좌,상 (달팽이 진행경로)
	static int[] dx = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t=0; t<tc; t++) {
			cnt = 1;
			status = 0;
			x = 0;
			y = 0;
			int n = Integer.parseInt(br.readLine());
			System.out.printf("#%d\n", t+1);
			int[][] arr = new int[n][n];
			snail_arr(arr, n);
			for(int z=0; z<n; z++) {
				for(int x=0; x<n; x++) {
					System.out.printf("%d ",arr[z][x]);
				}
				System.out.println();
			}
		}
		
	}
	static void snail_arr(int[][] arr, int n) {
		if(n==1) return;
		if(cnt==1) {
			arr[y][x] = 1;
			for(int i=0; i<n-1; i++) {
				x += dx[status%4];
//				y += dy[status%4];
				cnt++;
				arr[y][x] = cnt;
			}
			status++;
		}
		for(int i=0; i<n-1; i++) {
//			x += dx[status%4];
			y += dy[status%4];
			cnt++;
			arr[y][x] = cnt;
		}
		status++;
		for(int i=0; i<n-1; i++) {
			x += dx[status%4];
//			y += dy[status%4];
			cnt++;
			arr[y][x] = cnt;
		}
		status++;
		snail_arr(arr, n-1);
	}
}
