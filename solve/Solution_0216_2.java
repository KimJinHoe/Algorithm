package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//SWEA 4012
public class Solution_0216_2 {
	
	static int N;
	static int[] food;
	static int[] food_A;
	static int[] food_B;
	static int synergy_A;
	static int synergy_B;
	static int min;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input27.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        //테스트 케이스 반복
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	food = new int[N];
        	Arrays.fill(food, -1);
        	food_A = new int[N/2];
        	food_B = new int[N/2];
        	min = Integer.MAX_VALUE;
        	//배열입력
        	int[][] synergy = new int[N][N];
        	for(int i=0; i<N; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine()," ");
        		for(int j=0; j<N; j++) synergy[i][j] = Integer.parseInt(st.nextToken());
        	}
        	comb(0,0, synergy);
        	sb.append('#').append(tc).append(' ').append(min).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void comb(int cnt, int start, int[][] synergy) {
		if(cnt == N/2) {
			int bi = 0;
			int ai = 0;
			//food A, B에 각 재료들 저장
			for(int i=0; i<N; i++) {
				if(food[i] != -1) food_A[ai++] = i;
				else food_B[bi++] = i;
			}
			//시너지 합산
			synergy_A = 0;
        	synergy_B = 0;
			for(int i : food_A) {
				for(int j=0; j<N; j++) {
					if(food[j] != -1) synergy_A += synergy[i][j];
				}
			}
			for(int i : food_B) {
				for(int j=0; j<N; j++) {
					if(food[j] == -1) synergy_B += synergy[i][j];
				}
			}
			//min 구하기
			int diff = Math.abs(synergy_A-synergy_B);
			if(diff < min) {
				min = diff;
			}
			return;
		}
		for(int i=start; i<N; i++) {
			food[i] = i;
			comb(cnt+1, i+1, synergy);
			food[i] = -1;
		}
	}

}
