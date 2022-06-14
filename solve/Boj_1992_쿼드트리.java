package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1992_쿼드트리 {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input28.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        //배열입력
        int[][] arr = new int[N][N];
        for(int i=0; i<N; i++) {
        	String line = br.readLine();
        	for(int j=0; j<N; j++) {
        		arr[i][j] = line.charAt(j)-'0';
        	}
        }
        //실행
        run(0, 0, N, arr);
        //출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void run(int x, int y, int n, int[][] arr) {
		//다 같은지 판별
		int flag = 0;
		label: for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(arr[x][y] != arr[i][j]) {
					flag = 1;
					break label;
				}
			}
		}
		//같다면 문자 출력
		if(flag==0) {
			sb.append(arr[x][y]);
			return;
		}
		
		sb.append('(');
		run(x, y, n/2, arr); //1사분면
		run(x, y+n/2, n/2, arr); //2사분면
		run(x+n/2, y, n/2, arr); //3사분면
		run(x+n/2, y+n/2, n/2, arr); //4사분면
		
		sb.append(')');
	}
}
