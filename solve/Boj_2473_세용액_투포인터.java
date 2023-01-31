package cote;
//2023.01.31 196ms 1등
import java.io.*;
import java.util.Arrays;

public class Boj_2473_세용액_투포인터 {
	
	static int N;
	static int[] liquid;
	static long min = Long.MAX_VALUE;
	static int[] ans = new int[3];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//입력
		N = read();
		liquid = new int[N];
		for(int i=0; i<N; i++) {
			liquid[i] = read();
		}
		Arrays.sort(liquid);
		
		//실행
		//기준 용액 하나를 잡아 놓고 나머지 2개 용액은 투포인터로 해결
		label: for(int i=0; i<N-2; i++) {
			int left = i+1;
			int right = N-1;
			long ival = liquid[i];
			long lval = 0;
			long rval = 0;
			long sum = 0;
			
			while(left < right) {
				lval = liquid[left];
				rval = liquid[right];
				sum = ival + lval + rval;
				if(sum == 0) {
					ans[0] = (int) ival;
					ans[1] = (int) lval;
					ans[2] = (int) rval;
					break label;
				}
				
				if(min > Math.abs(sum)) {
					min = Math.abs(sum);
					ans[0] = (int) ival;
					ans[1] = (int) lval;
					ans[2] = (int) rval;
				}
				
				if(sum < 0) left++;
				else right--;
			}
		}
		
		//출력
		sb.append(ans[0]).append(' ').append(ans[1]).append(' ').append(ans[2]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    boolean isNegative = n == 13;
	    if (isNegative) n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if (c == 13) System.in.read();
	    return isNegative ? ~n + 1 : n;
	}
}
