package cote;
//2023-01-27 124ms 8등
import java.io.*;
import java.util.*;
public class Boj_1043_거짓말_adj_union {

	static int N,M,K;
	static boolean[][] adj;
	static boolean[] visited;
	static boolean[] knowtruth;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		N = read();
		M = read();
		adj = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		knowtruth = new boolean[N+1];
		List<Queue<Integer>> quelist = new ArrayList<Queue<Integer>>();
		
		
		//진실아는사람
		K = read();
		if(K == 0) {
			System.out.println(M);
			return;
		}
		while(K-->0) {
			knowtruth[read()] = true;
		}
		
		//파티반복해서 adj만듦
		int tM = M;
		while(tM-->0) {
			Queue<Integer> que = new LinkedList<Integer>();
			int num = read();
			int host = read();
			que.add(host);
			num--;
			while(num-->0) {
				int member = read();
				que.add(member);
				adj[host][member] = true;
				adj[member][host] = true;
			}
			quelist.add(que);
		}
		
		//전염(?)
		for(int i=1; i<=N; i++) {
			if(!visited[i] && knowtruth[i]) dfs(i);
		}
		
		//답 찾기
		for(int i=0; i<M; i++) {
			Queue<Integer> que = quelist.get(i);
			while(!que.isEmpty()) {
				if(!knowtruth[que.poll()]) continue;
				cnt++;
				break;
			}
		}
		
		//출력
		System.out.println(M-cnt);
    }
	
	static void dfs(int cur) {
		visited[cur] = true;
		for(int i=1; i<=N; i++) {
			if(visited[i]) continue;
			if(!adj[cur][i]) continue;
			knowtruth[i] = true;
			dfs(i);
		}
	}
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}
