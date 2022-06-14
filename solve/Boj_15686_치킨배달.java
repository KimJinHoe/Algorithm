package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_15686_치킨배달 {
	
	static int[][] selected;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		List<int[]> house = new ArrayList<int[]>();
		List<int[]> chicken = new ArrayList<int[]>();
		selected = new int[M][2];
		//배열 입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num == 1) house.add(new int[] {i,j});
				else if(num == 2) chicken.add(new int[] {i,j});
			}
		}
		
		//조합뽑기
		comb(0, 0, M, chicken, house);
		
		//출력
		System.out.println(ans);
	}
	
	static void comb(int cnt, int start, int M,List<int[]> chicken,List<int[]> house) {
		if(cnt == M) {
			//집 기준 치킨집들의 거리를 계산하고 최소값을 찾아 해당 집의 치킨거리를 입력. 그리고 다 더하여 도시의 치킨거리를 구함
			int total = 0;
			int size = house.size();
			for(int i=0; i<size; i++) {
				int min = Integer.MAX_VALUE;
				int[] loc = house.get(i);
				for(int j=0; j<M; j++) {
					int dis = Math.abs(loc[0]-selected[j][0]) + Math.abs(loc[1]-selected[j][1]);
					if(dis<min) min = dis;
				}
				total += min;
			}
			//도시의 치킨거리가 최소면 ans갱신
			if(total < ans) ans = total;
			return;
		}
		for(int i=start; i<chicken.size(); i++) {
			selected[cnt] = chicken.get(i);
			comb(cnt+1, i+1, M, chicken, house);
		}
	}
}

/*
5 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
출력 : 32
 */