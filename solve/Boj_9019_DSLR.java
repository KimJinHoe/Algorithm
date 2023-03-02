package cote;
//2023.03.02 1980ms 7등
import java.util.*;
import java.io.*;

public class Boj_9019_DSLR {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = read();
		while(T-->0) {
			int start = read();
			int end = read();
			int[] parents = new int[10001];
			char[] opers = new char[10001];
			Queue<Integer> que = new LinkedList<Integer>();
			que.offer(start);
			Arrays.fill(parents, -1);
			parents[start] = -2;
			
			label: while(!que.isEmpty()) {
				int num = que.poll();
				int D = (num<<1)%10000;
				int S = num==0?9999:num-1;
				int L = num*10%10000 + num/1000;
				int R = num/10 + num%10*1000;
				
				if(parents[D] == -1) {
					parents[D] = num;
					opers[D] = 'D';
					if(D == end) break label;
					que.offer(D);
				}
				if(parents[S] == -1) {
					parents[S] = num;
					opers[S] = 'S';
					if(S == end) break label;
					que.offer(S);
				}
				if(parents[L] == -1) {
					parents[L] = num;
					opers[L] = 'L';
					if(L == end) break label;
					que.offer(L);
				}
				if(parents[R] == -1) {
					parents[R] = num;
					opers[R] = 'R';
					if(R == end) break label;
					que.offer(R);
				}
			}
			Stack<Character> st = new Stack<Character>();
			while(end != -2) {
				st.push(opers[end]);
				end = parents[end];
			}
			
			st.pop();
			while(!st.isEmpty()) {
				sb.append(st.pop());
			}
			sb.append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c=System.in.read())>32) n = (n<<3) + (n<<1) + (c&15);
		if(c==13) System.in.read();
		return negative ? ~n + 1 : n;
	}
}
