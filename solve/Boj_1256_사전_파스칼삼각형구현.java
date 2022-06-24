package Algorithm.solve;
//124ms. 3등. 파스칼의 삼각형 이용
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1256_사전_파스칼삼각형구현 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] paskal = new int[M+1][N];
		
		//실행
		make_paskal(paskal, M, N);
		run(M, -1, paskal, M, N, K, sb);
		
		//출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void make_paskal(int[][] paskal, int M, int N) {
		Arrays.fill(paskal[1], 1);
		for(int i=2; i<M+1; i++) {
			paskal[i][0] = 1;
			for(int j=1; j<N; j++) {
				paskal[i][j] = paskal[i][j-1] + paskal[i-1][j]; 
				if(paskal[i][j]<0) paskal[i][j]=Integer.MAX_VALUE;
			}
		}
	}
	
	static void run(int m, int before, int[][] paskal, int M, int N, int K, StringBuilder sb) {
		//i는 aaaazzz에서 z가 앞으로 몇 번 건너뛰었나를 나타냄
		//ex) N+M-m-i: aaaz->3, aaza->2, azaa->1, zaaa->0 (인덱스는 0부터)
		int i=0;
		while(K>paskal[m][i]) {
			K -= paskal[m][i];
			i++;
			if(i == N) break;
		}
		int val = N+M-m-i;
		for(int j=1; j<val-before; j++) {
			sb.append('a');
		}
		sb.append('z');
		//더 넣을 z가 있으면 재귀함수
		if(m-1>0) {
			run(m-1, val, paskal, M, N, K, sb);
		}
		//K가 범위 넘을 때: 더 넣을 z가 없는데 (재귀의 끝) 남은 K가 있으면, 기존 sb를 초기화하고 -1 출력  
		else if(K>1) {
			sb.setLength(0);
			sb.append("-1");
		}
		//더 넣을 z가 없으면 (재귀의 끝) 남은 a를 출력
		else {
			int temp = N+M-sb.toString().length();
			for(int j=0; j<temp; j++) {
				sb.append('a');
			}
		}
	}
}