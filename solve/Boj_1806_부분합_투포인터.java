package cote;
// 2023.02.01 144ms 3등
import java.io.*;

public class Boj_1806_부분합_투포인터 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		
		int N = read();
		int S = read();
		int[] arr = new int[N];
		boolean[] visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = read();
		}
		
		int left = 0;
		int right = 0;
		int sum = 0;
		int min = Integer.MAX_VALUE;
		while(left <= right) {
			if(!visited[right]) {
				sum += arr[right];
				visited[right] = true;
			}
			if(sum < S) {
				if(right == N-1) break;
				right++;
				continue;
			}
			if(min > right - left) min = right - left;
			sum -= arr[left];
			left++;
		}
		
		
		System.out.println(min==Integer.MAX_VALUE ? 0 : min+1);
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
