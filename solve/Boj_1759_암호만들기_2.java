package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//Boj 1759 암호 만들기
/* 입력
4 6
a t N i s w
 */
public class Boj_1759_암호만들기_2 {

	static int N, R;
	static char[] result;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		char[] strs = new char[N];
		result = new char[R];
		// 배열입력
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			strs[i] = st.nextToken().charAt(0);
		}
		// 오름차순 정렬
		Arrays.sort(strs);
		// next조합 실행
		comb(0, 0, strs);
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void comb(int cnt,int start, char[] strs) {
		if(cnt == R) {
			int moum_cnt = 0;
			for(int i=0; i<R; i++) {
				char str = result[i];
				if (str == 'a' || str == 'e' || str == 'i' || str == 'o' || str == 'u') moum_cnt++;
			}
			if (moum_cnt >= 1 && R - moum_cnt >= 2) sb.append(result).append('\n');
			return;
		}
		for(int i=start; i<N; i++) {
			result[cnt] = strs[i];
			comb(cnt+1, i+1, strs);
		}
	}
}
