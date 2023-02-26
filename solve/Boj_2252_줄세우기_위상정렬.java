package cote;

import java.io.*;
import java.util.*;

public class Boj_2252_줄세우기_위상정렬 {
	
	static int N, M;
	static int[] arr;
	static List<List<Integer>> lists = new ArrayList<List<Integer>>();
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = read();
		M = read();
		arr = new int[N+1];
		for(int i=0; i<N+1; i++) {
			lists.add(new ArrayList<Integer>());
		}
		
		while(M-->0) {
			int a = read();
			int b = read();
			lists.get(a).add(b);
			arr[b]++;
		}
		
		label: while(cnt != N) {
			for(int i=1; i<N+1; i++) {
				if(arr[i] != 0) continue;
				if(func(i)) break label;
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static boolean func(int i) {
		arr[i] = -1;
		sb.append(i).append(' ');
		if(++cnt == N) return true;
		for(Integer num : lists.get(i)) {
			if(--arr[num] != 0) continue;
			if(func(num)) return true;
		};
		return false;
	}
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}
