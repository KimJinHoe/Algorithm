package cote;
//ArrayList는 삽입,삭제의 정렬에 시간이 오래 걸리니 Deque를 사용하자!
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16235_나무재테크_Deque {
	
	static class Tree {
		int y;
		int x;
		int age;
		
		public Tree(int y, int x, int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}
	}
	
	static int N, M, K;
	static int[][] map; //땅에 있는 양분
	static Deque<Tree> trees = new ArrayDeque<Tree>();
	static int[][] winter; //겨울에 추가되는 양분
	static int[] dy = {-1,-1,0,1,1,1,0,-1}; //팔방탐색(12시부터 시계방향)
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			Arrays.fill(map[i], 5);
		}
		winter = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				winter[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			trees.add(new Tree(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken())));
		}
		
		//실행
		run();
		
		//출력
		sb.append(trees.size());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run() {
		
		//K년 반복
		while(K-->0) {
			int[][] summer = new int[N][N];
			Queue<Tree> addTree = new LinkedList<Tree>(); //가을 번식할 트리
			
			//봄
			int size = trees.size();
			while(size-->0) {
				Tree tree = trees.pollFirst();
				//양분먹기. 못 먹으면 죽기.
				if(map[tree.y][tree.x] >= tree.age) {
					map[tree.y][tree.x] -= tree.age++;
					trees.offerLast(tree);
					if(tree.age%5 == 0) {
						for(int d=0; d<8; d++) {
							int ny = tree.y + dy[d];
							int nx = tree.x + dx[d];
							if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
							addTree.offer(new Tree(ny, nx, 1));
						}
					}
				} else {
					summer[tree.y][tree.x] += tree.age/2; 
				}
			}
			
			//여름은 겨울과 같이 진행
			
			//가을
			while(!addTree.isEmpty()) {
				trees.offerFirst(addTree.poll()); // 가장 어린 나무부터 양분을 먹으니 첫번째에 넣음
			}
			
			//겨울
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] += winter[i][j]+ summer[i][j];
				}
			}
			
			if(trees.size() == 0) return; //나무가 없으면 중지
		}
	}
	
}