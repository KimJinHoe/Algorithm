package cote;

import java.util.*;
import java.io.*;

public class Boj_7662_이중우선순위큐 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = read();
		while(T-->0) {
			int N = read();
			Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
			Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
			PriorityQueue<Integer> pque1 = new PriorityQueue<Integer>();
			PriorityQueue<Integer> pque2 = new PriorityQueue<Integer>(Collections.reverseOrder());
			
			while(N-->0) {
				if(isInput()) {
					int num = read();
					pque1.add(num);
					pque2.add(num);
					continue;
				}
				if(read() == -1) {
					while(!pque1.isEmpty() && map1.containsKey(pque1.peek())) {
						int key = pque1.poll();
						int val = map1.get(key);
						if(val == 1) map1.remove(key);
						else map1.replace(key, val-1);
					}
					if(pque1.isEmpty()) continue;
					int num = pque1.poll();
					if(map2.containsKey(num)) map2.replace(num, map2.get(num)+1);
					else map2.put(num, 1);
				}
				else {
					while(!pque2.isEmpty() && map2.containsKey(pque2.peek())) {
						int key = pque2.poll();
						int val = map2.get(key);
						if(val == 1) map2.remove(key);
						else map2.replace(key, val-1);
					}
					if(pque2.isEmpty()) continue;
					int num = pque2.poll();
					if(map1.containsKey(num)) map1.replace(num, map1.get(num)+1);
					else map1.put(num, 1);
				}
			}
			
			//최대출력
			while(!pque2.isEmpty() && map2.containsKey(pque2.peek())) {
				int key = pque2.poll();
				int val = map2.get(key);
				if(val == 1) map2.remove(key);
				else map2.replace(key, val-1);
			}
			if(pque2.isEmpty()) {
				sb.append("EMPTY\n");
				continue;
			}
			sb.append(pque2.peek()).append(' ');
			
			//최소출력
			while(!pque1.isEmpty() && map1.containsKey(pque1.peek())) {
				int key = pque1.poll();
				int val = map1.get(key);
				if(val == 1) map1.remove(key);
				else map1.replace(key, val-1);
			}
			sb.append(pque1.peek()).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static boolean isInput() throws Exception {
		int c = System.in.read();
		System.in.read();
		return c=='I'?true:false;
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
