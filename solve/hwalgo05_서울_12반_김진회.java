package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//SWEA1210
public class hwalgo05_서울_12반_김진회 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input16.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int SIZE = 100;
				
		// 테스트 케이스 반복
		for (int tc = 0; tc < 10; tc++) {
			int T = Integer.parseInt(br.readLine());
			int x = 0;
			int y = 99;
			int[][] arr = new int[SIZE][SIZE];
			//배열 입력
			for(int i=0; i<SIZE; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<SIZE; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//2위치 찾기
			for(int j=0; j<SIZE; j++) {
				if(arr[99][j] == 2) x = j; 
			}
			//위로 올라가기
			while(y-- > 0) {
				int dir = 0;
				//좌우 살피기
				if(x-1>=0 && arr[y][x-1]==1) dir = -1;
				else if(x+1<SIZE && arr[y][x+1]==1) dir = 1;
				//옆에 길이 있다면
				if(dir != 0) {
					// x좌표 옮기기
					do {
						x += dir;
					} while(x>=0 && x<SIZE && arr[y][x] == 1);
					x-=dir;
				}
			}
			//출력
			sb.append('#').append(T).append(' ').append(x).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
