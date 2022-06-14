package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//정올 1681 해밀턴 순환회로
public class hwalgo18_서울_12반_김진회 {
	
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] adjMatrix = new int[N][N];
		//배열입력받기
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//비트마스킹 순열
		int[] selected = new int[N+1]; //0이 맨앞, 맨뒤로 총 2개
		perm(0, 0, N-1, selected, adjMatrix, 0);
		System.out.println(min);
	}
	
	static void perm(int cnt, int flag, int n, int[] selected, int[][] adjMatrix, int sum) {
		//가지치기
		if(sum >= min) return;
		
		if(cnt == n) {
			sum += adjMatrix[selected[cnt]][selected[cnt+1]];
			if(sum < min) min = sum;
			return;
		}
		
		for(int i=0; i<n; i++) {
			if((flag&1<<i) != 0) continue;
			selected[cnt+1] = i+1;
			perm(cnt+1, flag|1<<i, n, selected, adjMatrix, sum+adjMatrix[selected[cnt]][selected[cnt+1]]);
		}
	}
}
