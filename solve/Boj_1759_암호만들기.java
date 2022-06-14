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
public class Boj_1759_암호만들기 {

	static int N, R;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb_temp = new StringBuilder();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		char[] strs = new char[N];
		boolean[] moum = new boolean[N];
		// 배열입력
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			strs[i] = st.nextToken().charAt(0);
		}
		// 오름차순 정렬
		Arrays.sort(strs);
		// 모음은 따로 표시
		for (int i = 0; i < N; i++) {
			char str = strs[i];
			if (str == 'a' || str == 'e' || str == 'i' || str == 'o' || str == 'u')
				moum[i] = true;
		}
		// next조합 실행
		nextcomb(strs, moum);
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void nextcomb(char[] strs, boolean[] moum) {
		// N개 중 R개의 조합을 구할 때 뒤부터 1로 채움 ex) 001111
		int cnt = 0;
		int[] p = new int[N];
		while (++cnt <= R) p[N - cnt] = 1;
		// np실행 및 sb합치기
		do {
			int moum_cnt = 0;
			for (int i = 0; i < N; i++) {
				if (p[i] == 1) {
					sb_temp.append(strs[i]);
					// 모음이면 카운트
					if (moum[i]) moum_cnt++;
				}
			}
			// 모음 1개이상, 자음 2개 이상이면 출력에 포함
			if (moum_cnt >= 1 && R - moum_cnt >= 2) sb.insert(0, sb_temp.toString()).insert(R, '\n');
			sb_temp.setLength(0);
		} while (np(p));
	}

	private static boolean np(int[] p) {
		// 1. 교환위치 찾기
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i]) i--;

		if (i == 0) return false;

		// 2. 교환위치에 교환할 값 찾기
		int j = N - 1;
		while (p[i - 1] >= p[j]) j--;

		// 3. 교환위치와 교환할 값 교환하기
		swap(p, i - 1, j);

		// 4. 교환위치 뒤(꼭대기)부터 맨 뒤까지 만들 수 있는 가장 작은 순열생성(오름차순정렬)
		int k = N - 1;
		while (i < k) swap(p, i++, k--);
		return true;
	}

	static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
}
