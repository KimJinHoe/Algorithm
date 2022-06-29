package cote;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Boj_1655_가운데를말해요_PQ {

	static int N;
	static short M;
	static PriorityQueue<Short> left = new PriorityQueue<Short>(Collections.reverseOrder());
	static PriorityQueue<Short> right = new PriorityQueue<Short>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		//입력
		N = Integer.parseInt(br.readLine());
		
		//실행
		//1번째
		M = Short.parseShort(br.readLine());
		sb.append(M).append('\n');
		
		for(int i=2; i<=N; i++) {
			short num = Short.parseShort(br.readLine());
			if(i%2 == 0) {
				if(num>=M) {
					right.offer(num);
				} else {
					right.offer(M);
					left.offer(num);
					M = left.poll();
				}
			}
			else {
				if(num<=M) {
					left.offer(num);
				} else {
					left.offer(M);
					right.offer(num);
					M = right.poll();
				}
			}
			sb.append(M).append('\n');
		}

		//출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
