package cote;
//2023.03.03 120ms 1등
//프림알고리즘 조금 변형 (거의 동일)
import java.util.*;
import java.io.*;

public class Boj_4386_별자리만들기_Prim {
	
	static int N;
	static double[][] stars;
	static double[][] map;
	static boolean[] visited;
	static double[] dist;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		stars = new double[N][2];
		map = new double[N][N];
		visited = new boolean[N];
		dist = new double[N];
		Arrays.fill(dist, Double.MAX_VALUE);
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
			
			for(int j=0; j<i; j++) {
				map[i][j] = map[j][i] = Math.sqrt(Math.pow(stars[i][0]-stars[j][0], 2) + Math.pow(stars[i][1]-stars[j][1], 2));
			}
		}
		
		double ans = 0;
		int start = 0;
		dist[start] = 0;
		visited[start] = true;
		for(int i=0; i<N; i++) {
			int idx = 0;
			double min = Double.MAX_VALUE;
			for(int j=0; j<N; j++) {
				if(visited[j]) continue;
				if(dist[j] > map[start][j]) dist[j] = map[start][j];
				if(min > dist[j]) {
					min = dist[j];
					idx = j;
				}
			}
			
			ans += dist[idx];
			start = idx;
			visited[idx] = true;
		}
		
		System.out.println(ans);
	}
}
