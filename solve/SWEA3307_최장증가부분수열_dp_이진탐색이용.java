package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA3307_최장증가부분수열_dp_이진탐색이용 {

	static int N;
	static int[] number;
	static int[] LIS;
	static int size;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			//데이터 입력
			N = Integer.parseInt(br.readLine());
			number = new int[N];
			LIS = new int[N];
			Arrays.fill(LIS, 1);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			
			//실행
			size = 0;
			for (int i=0; i < N; i++) {

	            int temp = Arrays.binarySearch(LIS, 0, size, number[i]); // 리턴값 : -insertPoint -1
	            temp = Math.abs(temp)-1;//삽입위치
	            LIS[temp] = number[i];// temp 자리에 값을 update 하면 그 의미는 
	            			     // 0인덱스 위치부터 temp위치까지의 원소 갯수가  temp위치에 저장된 그 값을 마지막으로 하는 LIS 길이가 됨
	            				 // 배열의 원소가 LIS를 이루는 구성요소와는 관계가 없다.

	            if (size == temp) {// 삽입위치의 인덱스와 크기가 같으면(결국,마지막이 삽입위치라는 얘기임) 크기 1늘림.
	                size++;
	            }
	        }
			
			//출력
			sb.append('#').append(tc).append(" ").append(size).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}