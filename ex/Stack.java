package Algorithm.ex;
//연결리스트로 스택구현
public class Stack {
	private Node top;
	
	public void push(String data) {
		// 첫번째 노드(top)로 삽입
		top = new Node(data, top);
	}
	
	public String pop() {
		if(isEmpty()) return null;
		
		// 첫번째 노드(top) 삭제
		Node popNode = top;
		top = popNode.link;
		
		popNode.link = null;
		return popNode.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public String toString() {
		// top부터 마지막 노드까지 쭉 돌며 data를 문자열로 합치기
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for(Node cur = top; cur!=null; cur=cur.link) {
			sb.append(cur.data).append(',');
		}
		if(!isEmpty()) sb.setLength(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
}
