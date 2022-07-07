package cote;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_3584_가장가까운공통조상_구현 {
	
	static int T, N;
	static int[] nodes;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//테케반복
		T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			N = Integer.parseInt(br.readLine());
			nodes = new int[N+1]; //각 노드의 부모를 값으로 입력
			for(int i=0; i<N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				nodes[Integer.parseInt(st.nextToken())] = num1;
			}
			
			//실행
			st = new StringTokenizer(br.readLine());
			sb.append(lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
		}
		
		//출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int lca(int a, int b) {
		Queue<Integer> aque = new ArrayDeque<Integer>();
		Queue<Integer> bque = new ArrayDeque<Integer>();
		
		//a의 조상들 큐에 담기
		while(a != 0) {
			aque.offer(a);
			a = nodes[a];
		}
		
		//b의 조상들 큐에 담기
		while(b != 0) {
			bque.offer(b);
			b = nodes[b];
		}
		
		//깊이 맞추기
		int dif = aque.size() - bque.size();
		if(dif >= 0) {
			while(dif-->0)
				aque.poll();
		} else {
			while(dif++<0)
				bque.poll();
		}
		
		//같은 조상 찾기
		a = aque.poll();
		b = bque.poll();
		while(a != b) {
			a = aque.poll();
			b = bque.poll();
		}
		return a;
	}
}