package Algorithm.solve;
시간초과 하나하나 카운트하면 안됨
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

public class SWEA5987_달리기 {

	static int[][] map;
	static int N, M;
	static long ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= 2; tc++) {
			// 입력
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int[] numbers = new int[N];
			//어떤 사람(idx)이 다른 어떤 사람들과 연관되어 있는 지 저장
			List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			for(int i=0; i<=N; i++) {
				list.add(new ArrayList<Integer>());
			}
			map = new int[N][N];
			M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				list.get(num1).add(num2);
				list.get(num2).add(-num1);
			}
			perm(0, 0, numbers, list);
			// 출력
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void perm(int cnt, int flag, int[] numbers, List<ArrayList<Integer>> list) {
		
		if (cnt == N) {
			ans++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) continue;
			int size = list.get(i+1).size();
			for(int j=0; j<size; j++) {
				int other = list.get(i+1).get(j);
				int otherr = Math.abs(other)-1;
				if((flag&1<<otherr) != 1<<otherr) continue; //연관된 사람이 아직 안 뽑혔으면 패스
				//other>0 : i가 other을 이겼다.
				//i가 뒤늦게 뽑혔다 > i가 늦게 들어왔다. 즉, other>0이면 return
				if(other>0) return; 
			}
			numbers[cnt] = i;
			// 다음수 뽑으러 가기
			perm(cnt + 1, flag | 1 << i, numbers, list);
		}
	}
}
