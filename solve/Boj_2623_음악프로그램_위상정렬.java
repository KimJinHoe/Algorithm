package cote;
//2023.02.20 136ms 1등
import java.util.*;
import java.io.*;

public class Boj_2623_음악프로그램_위상정렬 {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		
		int N = read();
		int M = read();
		List<Queue<Integer>> list = new ArrayList<>();
		Queue<int[]> que = new LinkedList<int[]>();
		int[] arr = new int[N+1];
		for(int i=0; i<=N; i++) {
			list.add(new LinkedList<Integer>());
		}
		
		while(M-->0) {
			int num = read() - 1;
			int post = read();
			while(num-->0) {
				int next = read();
				list.get(post).add(next);
				arr[next]++;
				post = next;
			}
		}
		for(int i=1; i<=N; i++) {
			que.add(new int[] {i, arr[i]});
		}
		
		int cnt = 0;
		int pass = 0;
		Arrays.fill(arr, 0);
		while(cnt != N-pass) {
			int[] cur = que.poll();
			if(cur[1] != arr[cur[0]]) {
				cnt++;
				que.add(cur);
				continue;
			}
			Queue<Integer> q = list.get(cur[0]);
			while(!q.isEmpty()) arr[q.poll()]++;
			pass++;
			cnt = 0;
			sb.append(cur[0]).append('\n');
		}
		
		if(pass != N) {
			System.out.println("0");
			return;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return n;
	}
	
}
