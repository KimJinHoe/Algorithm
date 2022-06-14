package Algorithm.ex;

public class ex2_CompleteBinaryTree_test {
	public static void main(String[] args) {
		int size = 9;
		ex2_CompleteBinaryTree tree = new ex2_CompleteBinaryTree(size);
		
		for(int i=0; i<size; i++) {
			tree.add((char)(65+i)); //A,B,C...
		}
		
		tree.bfs();
		tree.dfsByPreOrder();
		tree.dfsByInOrder();
		tree.dfsByPostOrder();
	}
}
