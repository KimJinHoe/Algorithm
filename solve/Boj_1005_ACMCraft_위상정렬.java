package cote;
//2023.02.26 376ms 2등
import java.util.*;
import java.io.*;

public class Boj_1005_ACMCraft_위상정렬 {
	
	static int N, K;
	static int[] drr; //해당 건물 건설 걸리는 시간
	static int[] wait; //건설 착수하기까지 대기시간
	static int[] cntArr;
	static List<List<Integer>> lists;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = read();
		
		//테케 반복
		TC: while(T-->0) {
			N = read();
			K = read();
			drr = new int[N+1];
			wait = new int[N+1];
			cntArr = new int[N+1];
			
			//리스트 초기화
			lists = new ArrayList<List<Integer>>();
			for(int i=0; i<N+1; i++) {
				lists.add(new ArrayList<Integer>());
			}
			
			//시간입력
			for(int i=1; i<N+1; i++) {
				drr[i] = read();
			}
			
			//순서입력
			while(K-->0) {
				int a = read();
				int b = read();
				lists.get(a).add(b);
				cntArr[b]++;
			}
			
			//승리 건물
			int W = read();

			//실행
			Queue<Integer> que = new LinkedList<Integer>();
			while(true) {
				for(int i=1; i<N+1; i++) {
					if(cntArr[i] != 0) continue;
					if(i == W) {
						sb.append(drr[W] + wait[W]).append('\n'); //건설 걸리는 시간 + 대기 시간
						continue TC;
					}
					que.add(i);
				}
				
				while(!que.isEmpty()) {
					int num = que.poll();
					cntArr[num] = -1;
					for(Integer next : lists.get(num)) {
						cntArr[next]--;
						wait[next] = Math.max(wait[next], wait[num]+drr[num]);
					}
				}
				
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
}
