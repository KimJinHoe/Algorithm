package cote;
import java.io.*;
import java.util.StringTokenizer;
/*
풀이 
ABC = min( A(BC), (AB)C )
ABCD = min( A(BCD), (AB)(CD), (ABC)D )
...
 */
public class Boj_11049_행렬곱셈순서_dp {
	
	static int N;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//입력
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			st.nextToken();
		}
		st = new StringTokenizer(br.readLine());
		arr[N-1] = Integer.parseInt(st.nextToken());
		arr[N] = Integer.parseInt(st.nextToken());
		dp = new int[N+1][N+1];
		
		//실행
		//i: (AB)C면 A, A(BC)면 A    (start의 start)
		//se: (AB)C면 B, A(BC)면 A   (start의 end)
		//se+1: (AB)C면 C, A(BC)면 B (end의 start)
		//end: (AB)C면 C, A(BC)면 C  (end의 end)
		for(int size=1; size<N; size++) {
			for(int i=1; i<=N-size; i++) {
				int se = i; //i+0 
				int end = i + size; 
				dp[i][end] = dp[i][se]+dp[se+1][end]+arr[i-1]*arr[se]*arr[end]; //dp배열 초기화
				
				for(int k=1; k < size; k++) {
					se = i + k; 
					dp[i][end] = Math.min(dp[i][end], dp[i][se]+dp[se+1][end]+arr[i-1]*arr[se]*arr[end]);
				}
			}
		}

		//출력
		sb.append(dp[1][N]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
}