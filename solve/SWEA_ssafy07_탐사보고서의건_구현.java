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

public class SWEA_ssafy07_탐사보고서의건_구현 {
	
	static int ans;
	static int F;
	static int N;
	static Queue<Integer> que;
	static List<Integer> whomax;
	static int[] arr;
	static int total_hand;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			F = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			que = new LinkedList<Integer>();
			arr = new int[N+1];
			total_hand = 0;
			
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				total_hand++;
				int num = Integer.parseInt(st.nextToken());
				arr[num]++;
				que.add(num);
			}
			
			//실행
			run();
			
			//출력
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void find_max() {
		whomax = new ArrayList<Integer>();
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N+1; i++) {
			if(max<arr[i]) {
				max = arr[i];
			}
		}
		for(int i=0; i<N+1; i++) {
			if(arr[i] == max) whomax.add(i);
		}
	}
	
	static int left_hand() {
		int cnt = 0;
		int who = 0;
		for(int i=0; i<N+1; i++) {
			if(arr[i]!=0) {
				cnt++;
				who = i;
			}
		}
		if(cnt>1) return 0; //여러명 남은 경우
		else return who; //한명 남은 경우
	}
	
	static void run() {
		
		//턴마다 반복
		while(true) {
			boolean flag = false; // 손을 제거했는지 여부
			//누가 손이 최대로 많은 지 찾기
			find_max();
			int temp = total_hand;
			for(int i=0; i<temp; i++) {
				//손빼기
				int num = que.poll();
				F--;
				if(F==0) {
					ans = num;
					return;
				}
				
				//최대손인가? 다시 넣어야할까?
				if(!flag && whomax.contains(num)) {
					flag = true;
					arr[num]--;
					total_hand--;
					int who_left_hand = left_hand();
					if(who_left_hand != 0) {
						ans = num;
						return;
					}
				} else {
					que.add(num);
				}
			}
		}
	}
}

/*
5
40 5
1 2 3 4 5 5 4 3 2 1 1 2 3 4 5 5 4 3 2 1 1 2 3 4 3 2 1 1 2 1
300 5
1 2 3 4 5 5 4 3 2 1 1 2 3 4 5 5 4 3 2 1 1 2 3 4 3 2 1 1 2 1
15 5
1 2 3 4 5 5 4 3 2 1 1 2 3 4 5 5 4 3 2 1 1 2 3 4 3 2 1 1 2 1
15 3
1 1 2 3 2 3 2 2 1 2
20 6
6 6 6 6 6 5 5 5 5 5 4 4 4 4 4 3 3 3 3 3 2 2 2 2 2 1 1 1 1 1
 */
 */
