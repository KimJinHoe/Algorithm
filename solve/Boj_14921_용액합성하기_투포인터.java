package cote;
//2023.01.31 172ms 1등
import java.io.*;

public class Boj_14921_용액합성하기_투포인터 {
	
	static int N;
	static int[] liquid;
	static int min = Integer.MAX_VALUE;
	
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
		
		//실행
		//기준 용액 하나를 잡아 놓고 나머지 2개 용액은 투포인터로 해결
		int left = 0;
		int right = N-1;
		int lval = 0;
		int rval = 0;
		int sum = 0;
		int ans = 0;
		
		while(left < right) {
			lval = liquid[left];
			rval = liquid[right];
			sum = lval + rval;
			if(sum == 0) {
				ans = 0;
				break;
			}
			
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				ans = sum;
			}
			
			if(sum < 0) left++;
			else right--;
		}
		
		//출력
		sb.append(ans);
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
