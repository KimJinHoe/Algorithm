package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11052_카드구매하기_dp {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력
		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[N+1];
		int[] memo = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		//실행
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=i; j++) {
				if(memo[i-j]+cards[j] > memo[i]) memo[i] = memo[i-j]+cards[j];
			}
		}
		System.out.println(memo[N]);
		
		
	}
}
