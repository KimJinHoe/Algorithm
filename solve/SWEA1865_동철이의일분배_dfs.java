package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA1865_동철이의일분배_dfs {
	
	static double ans;
	static double[][] arr;
	static int N;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//입력
			ans = 100;
			N = Integer.parseInt(br.readLine());
			arr = new double[N][N];
			visited = new boolean[N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken())*0.01;
				}
			}
			
			//실행
			for(int i=0; i<N; i++) {
				ans *= arr[i][i];
			}
			dfs(0, 0, 100);
			//출력
			sb.append('#').append(tc).append(' ').append(String.format("%.6f", ans)).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int cnt, int flag, double per) {
		if(per<= ans) return;
		if(cnt == N) {
			ans = per;
			return;
		}
		for(int i=0; i<N; i++) {
			if((flag&(1<<i))!=0) continue;
			dfs(cnt+1, flag|(1<<i), per*arr[cnt][i]);
		}
	}
}