package cote;
//2023.01.31 504ms 15등
//다익스트라(혹은 Prim)의 변형으로 해결했지만 Union Kruskal이 더 효율적임
//간선이 많으면=> Prim 사용, Vertex가 많으면 Kruskal 쓰자
import java.io.*;
import java.util.*;

public class Boj_1197_최소스패닝트리_Prim {
	
	public static void main(String[] args) throws Exception{
		//입력
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		int V = read();
		int E = read();
		List<List<int[]>> list = new ArrayList<List<int[]>>();
		boolean[] visited = new boolean[V+1];
		int[] dist = new int[V+1];
		int ans = 0;
		
		for(int i=0; i<V+1; i++) {
			list.add(new ArrayList<int[]>());
		}
		
		//입력
		while(E-->0) {
			int num1 = read();
			int num2 = read();
			int val = read();
			list.get(num1).add(new int[] {num2, val});
			list.get(num2).add(new int[] {num1, val});
		}
		
		//실행
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		for(int c=1; c<V+1; c++) {
			int min = Integer.MAX_VALUE;
			int cur = 1;
			for(int i=1; i<V+1; i++) {
				if(min > dist[i] && !visited[i]) {
					min = dist[i];
					cur = i;
				}
			}
			
			visited[cur] = true;
			List<int[]> curlist = list.get(cur);
			int size = curlist.size();
			for(int i=0; i<size; i++) {
				int[] tmp = curlist.get(i);
				int idx = tmp[0];
				int val = tmp[1];
				if(visited[idx]) continue;
				if(val < dist[idx]) dist[idx] = val;
			}
			ans += dist[cur];
		}
		
		//출력
		System.out.println(ans);
	}
	
	static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    boolean isNegative = n == 13;
	    if (isNegative) n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if (c == 13) System.in.read();
	    return isNegative ? ~n + 1 : n;
	}
}
