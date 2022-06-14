package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Boj2563 색종이
public class hwlago06_서울_12반_김진회 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input19.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		boolean[][] space = new boolean[100][100];
		//색종이 붙이기
		for(int num=0; num<N; num++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int i=x; i<x+10; i++) {
				for(int j=y; j<y+10; j++) space[i][j] = true;
			}
		}
		//검은 영역 구하기
		int cnt = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(space[i][j]) cnt++;
			}
		}
		System.out.println(cnt);
	}
}
