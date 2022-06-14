package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_18443_우체국2_dp {

	static int N, R;
	static long L;
	static long[] home;
	static int[] ansIdx;
	static long answer;
	static long[] ansrr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		L = Long.parseLong(st.nextToken());
		home = new long[N];
		long[][] map = new long[N + 1][N];
		ansIdx = new int[R];
		answer = Long.MAX_VALUE;
		ansrr = new long[R];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			home[i] = Long.parseLong(st.nextToken());
		}
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				map[i][j] = Math.min(Math.abs(home[i] - home[j]), L - Math.abs(home[i] - home[j]));
			}
		}
		
		//실행
		dfs(0, map);
		Arrays.sort(ansrr);
		// 출력
		sb.append(answer).append('\n');
		for(int i=0; i<R-1; i++) {
			sb.append(ansrr[i]).append(' ');
		}
		sb.append(ansrr[R-1]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void dfs(int cnt, long[][] map) {
		if(cnt==R) {
			long num = ans(map);
			if(answer > num) {
				answer = num;
				for(int i=0; i<R; i++) {
					ansrr[i] = home[ansIdx[i]];
				}
			}
			return;
		}
		
		sum(map); // 1. sum 갱신
		List<Integer> list = min(map); // 2. sum의 min 찾기
		int size = list.size();
		for(int i=0; i<size; i++) {
			
			//깊은 복사
			long[][] temp = new long[N+1][N];
			for(int j=0; j<N+1; j++) {
				temp[j] = map[j].clone();
			}
			
			arrange(list.get(i), temp); //3. 맵 갱신
			ansIdx[cnt] = list.get(i);
			dfs(cnt+1, temp);
		}
		
	}
	
	static long ans(long[][] map) {
		long ans = 0;
		for(int i=0; i<N; i++) {
			long min = Long.MAX_VALUE;
			for(int j=0; j<R; j++) {
				if(min>map[i][ansIdx[j]]) min = map[i][ansIdx[j]];
			}
			ans += min;
		}
		return ans;
	}
	
	static void arrange(int idx, long[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > map[i][idx])
					map[i][j] = map[i][idx];
			}
		}
	}

	static List<Integer> min(long[][] map) {
		long min = Long.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if (min > map[N][i]) {
				min = map[N][i];
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<N; i++) {
			if(min==map[N][i]) list.add(i);
		}
		return list;
	}

	static void sum(long[][] map) {
		for (int j = 0; j < N; j++) {
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += map[i][j];
			}
			map[N][j] = sum;
		}
	}
}
