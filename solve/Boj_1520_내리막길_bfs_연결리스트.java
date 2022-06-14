package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1520_내리막길_bfs_연결리스트 {
	
	static public class Node {
		int y;
		int x;
		int height;
		int val; //해당 노드로 올 수 있는 길의 개수
		List<Node> link = new ArrayList<Node>();
		
		public Node(int y, int x, int height) {
			this.y = y;
			this.x = x;
			this.height = height;
		}
	}
	
	static int h, w;
	static Node[][] map;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new Node[h][w];
		
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = new Node(i, j, Integer.parseInt(st.nextToken()));
			}
		}
		
		//실행
		bfs();
		
		//출력
		sb.append(map[h-1][w-1].val);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void bfs() {
		Queue<Node> que = new LinkedList<Node>();
		que.offer(map[0][0]);
		map[0][0].val = 1;
		int dest_height = map[h-1][w-1].height;
		
		while(!que.isEmpty()) {
			int size = que.size();
			
			while(size-->0) {
				Node cur = que.poll();
				int y = cur.y;
				int x = cur.x;
				
				for(int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny<0 || nx<0 || ny>=h || nx>=w) continue;
					if(map[ny][nx].height >= map[y][x].height) continue;
					if(map[ny][nx].height < dest_height) continue;
					
					map[y][x].link.add(map[ny][nx]);
					if(map[ny][nx].val == 0) {
						map[ny][nx].val = map[y][x].val;
						if(ny==h-1 && nx==w-1) continue;
						que.offer(map[ny][nx]);
					} else {
						related_add(map[ny][nx], map[y][x].val);
					}
				}
			}
		}
	}
	
	static void related_add(Node node, int val) {
		int link_size = node.link.size();
		for(int i=0; i<link_size; i++) {
			related_add(node.link.get(i), val);
		}
		node.val += val;
	}
}
