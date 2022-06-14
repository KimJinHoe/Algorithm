package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//SWEA 준환이의 양팔저울
public class Solution_0218_1 {
	
	static int N;
	static int ans;
	static int total;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	int[] weight = new int[N];
        	ans = 0;
        	total = 0;
        	StringTokenizer st = new StringTokenizer(br.readLine()," ");
        	for(int i=0; i<N; i++) {
        		weight[i] = Integer.parseInt(st.nextToken());
        		total += weight[i];
        	}
        	perm(0, 0, 0, 0, weight, total);
        	sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void perm(int cnt, int flag, int left, int right, int[] weight, int remain) {
		if(left>=right+remain) {
			int count=1;
			for(int i=0; i<N-cnt; i++) count *= 2;
			for(int i=1; i<N-cnt+1; i++) count *= i;

			ans += count;
			return;
		}
		if(cnt == N) {
			ans++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			//왼쪽
			if((flag & 1<<i) != 0) continue;
			perm(cnt+1, flag|1<<i, left+weight[i], right, weight, remain-weight[i]);
			//오른쪽
			if(right+weight[i]>left) continue;;
			perm(cnt+1, flag|1<<i, left, right+weight[i], weight, remain-weight[i]);
		}
	}
}
