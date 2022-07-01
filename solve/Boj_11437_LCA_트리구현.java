package cote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_11437_LCA_트리구현 {
	
	static class Node {
		List<Node> nodes = new ArrayList<Node>();
		int val;
		int depth;
		int parent;
		
		public Node(int val) {
			this.val = val;
		}
	}
	
	static int N, M;
	static List<Node> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		//입력
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<=N; i++) {
			list.add(new Node(i));
		}
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			list.get(num1).nodes.add(list.get(num2));
			list.get(num2).nodes.add(list.get(num1));
		}
		//실행
		set_parents(list.get(0), list.get(1), 0); //트리 노드1을 시작으로 각 노드에 부모 정보 넣기

		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			find(list.get(Integer.parseInt(st.nextToken())), list.get(Integer.parseInt(st.nextToken()))); //가까운 조상찾기
		}
		
		//출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void set_parents(Node from, Node cur, int depth) {
		cur.depth = depth;
		cur.parent = from.val;
		
		//자식들 재귀함수
		int size = cur.nodes.size();
		for(int i=0; i<size; i++) {
			if(cur.nodes.get(i) != from) set_parents(cur, cur.nodes.get(i), depth+1);
		}
	}
	
	static void find(Node node1, Node node2) {
		int depth = Math.min(node1.depth, node2.depth);
		//뎁스맞추기
		while(node1.depth != depth) {
			node1 = list.get(node1.parent);
		}
		while(node2.depth != depth) {
			node2 = list.get(node2.parent);
		}
		while(node1.val != node2.val) {
			node1 = list.get(node1.parent);
			node2 = list.get(node2.parent);
		}
		sb.append(node1.val).append('\n');
	}
}