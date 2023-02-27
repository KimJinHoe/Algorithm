package cote;
//2023-02-27 1040ms 3등
//풀이법 참고해서 3번 복습하고 풀었음..
import java.util.*;
import java.io.*;

public class Boj_1202_보석도둑 {
	
	static int N, K;
	static long ans;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		K = read();
		
		//보석, 가방 무게 오름차순
		PriorityQueue<int[]> stones = new PriorityQueue<int[]>((o1, o2) -> o1[0] - o2[0]);
		PriorityQueue<Integer> bags = new PriorityQueue<Integer>();
		
		//보석 입력
		while(N-->0) {
			stones.add(new int[] {read(), read()});
		}
		
		//가방 입력
		for(int i=0; i<K; i++) {
			bags.add(read());
		}
		
		
		PriorityQueue<Integer> pque = new PriorityQueue<Integer>(Collections.reverseOrder());
		while(K-->0) {
			int bag = bags.poll();
			while(!stones.isEmpty() && stones.peek()[0] <= bag) {
				pque.add(stones.poll()[1]);
			}
			
			if(!pque.isEmpty()) ans += pque.poll();
		}
		
		System.out.println(ans);
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
