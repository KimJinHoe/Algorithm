package Algorithm.solve;
//1728ms. 효율성 안좋음
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Boj_10282_해킹_Prim리스트변형2 {
	
	static int N;
	static List<int[]>[] map;
	static int ans;
	static int time;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테케 반복
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
        	//전역변수 초기화 및 입력 받기
        	ans = 0;
        	time = Integer.MIN_VALUE;
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	int start = Integer.parseInt(st.nextToken());
        	map = new ArrayList[N+1];
        	for(int i=1; i<N+1; i++) {
        		map[i] = new ArrayList<int[]>();
        	}
        	
        	//map 입력받기
        	for(int i=0; i<M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int to = Integer.parseInt(st.nextToken());
        		int from = Integer.parseInt(st.nextToken());
        		int val = Integer.parseInt(st.nextToken());
        		map[from].add(new int[] {to, val});
        	}
        	
        	//실행
    		run(start);
    		
    		//출력
            sb.append(ans).append(' ').append(time).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void run(int start) {
		boolean[] visited = new boolean[N+1];
		int[] minEdge = new int[N+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[start] = 0;
		for(int i=1; i<N+1; i++) {
			int min = Integer.MAX_VALUE;
			int from = 0;
			
			//출발점 결정
			for(int j=1; j<N+1; j++) {
				if(!visited[j] && min>minEdge[j]) {
					min = minEdge[j];
					from = j;
				}
			}
			
			if(from == 0) break; //cur==0은 더 이상 방문할 곳이 없다는 뜻
			visited[from] = true; //방문처리 
			
			int size = map[from].size();
			for(int j=0; j<size; j++) {
				int[] arr = map[from].get(j);
				int to = arr[0];
				int val = arr[1];
				if(visited[to]) continue; //방문했으면 패스
				if(minEdge[to] > val+minEdge[from])
					minEdge[to] = val+minEdge[from];
			}
		}
		for(int i=1; i<N+1; i++) {
			if(visited[i]) ans++; //감염된 컴퓨터 카운트.
			if(minEdge[i]!=Integer.MAX_VALUE && time<minEdge[i])
				time = minEdge[i]; //가장 큰 minEdge가 답 time. 
		}
		
	}
}