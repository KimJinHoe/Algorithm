package Algorithm.ex;
//전위중위후위, bfs, 완전이진트리
import java.util.LinkedList;
import java.util.Queue;

public class ex2_CompleteBinaryTree {
	
	private char[] nodes;
	private final int SIZE;
	private int lastIndex;
	
	public ex2_CompleteBinaryTree (int size) {
		nodes = new char[size+1];
		SIZE = size;
	}
	
	public boolean isEmpty() {
		return lastIndex==0;
	}
	
	public boolean isFull() {
		return lastIndex==SIZE;
	}
	
	public void add(char e) {
		if(isFull()) return;
		nodes[++lastIndex] = e;
	}
	
	public void bfs() {
		if(isEmpty()) return;
		
		//이진트리의 탐색 순서 관리
		Queue<Integer> que = new LinkedList<Integer>();
		
		//루트가 가장 먼저 탐색되도록 que에 넣기
		que.offer(1);
		int depth = 0;
		
		while(!que.isEmpty()) {
			System.out.print("level " + depth+1 + ": ");
			//같은 너비의 노드들 모두 탐색, 같은 행에 출력
			int size= que.size();
			
			while(--size >= 0) {
				int cur = que.poll();
				System.out.print(nodes[cur] + "\t");
				
				//현재 노드의 자식노드들의 인덱스를 다음에 순서가 되었을 때 탐색되도록 큐에 넣어준다.
				//왼쪽 노드
				if(cur*2 <= lastIndex) que.offer(cur*2);
				//오른쪽 노드
				if(cur*2+1 <= lastIndex) que.offer(cur*2+1);
			}
			System.out.println();
			depth++;
		}
	}
	
	public void dfsByPreOrder() {
		System.out.println("dfsByPreOrder");
		dfsByPreOrder(1);
		System.out.println();
	}
	
	private void dfsByPreOrder(int cur) {
		//기저조건
		if(cur > lastIndex) return;
		
		//현재 노드 방문
		System.out.print(nodes[cur]+" ");;
		
		//현재노드의 자식노드들 방문
		//왼쪽 노드
		dfsByPreOrder(cur*2);
		//오른쪽 노드
		dfsByPreOrder(cur*2+1);
		
	}
	
	public void dfsByInOrder() {
		System.out.println("dfsByInOrder");
		dfsByInOrder(1);
		System.out.println();
	}
	
	private void dfsByInOrder(int cur) {
		//기저조건
		if(cur > lastIndex) return;
		
		//왼쪽 노드
		dfsByInOrder(cur*2);
		//현재 노드 방문
		System.out.print(nodes[cur]+" ");;
		//오른쪽 노드
		dfsByInOrder(cur*2+1);
		
	}
	
	public void dfsByPostOrder() {
		System.out.println("dfsByPostOrder");
		dfsByPostOrder(1);
		System.out.println();
	}
	
	private void dfsByPostOrder(int cur) {
		//기저조건
		if(cur > lastIndex) return;
		
		//왼쪽 노드
		dfsByPostOrder(cur*2);
		//오른쪽 노드
		dfsByPostOrder(cur*2+1);
		//현재 노드 방문
		System.out.print(nodes[cur]+" ");;
		
	}
}
