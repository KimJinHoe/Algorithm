package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_14725_개미굴 {

	static StringBuilder sb = new StringBuilder();
	static String[] giho = new String[16];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//입력
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N*15];
		int idx = 0;
		// ex1) arr에 'B ', 'B A ', 'A', 'A B ', 'A B C ', 'A B C D '.... 저장
		while(N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			StringBuilder temp = new StringBuilder();
			while(k-->0) {
				arr[idx++] = temp.append(st.nextToken()).append(' ').toString();
			}
		}
		
		//실행
		make_giho(); //연산을 줄이기 위해 '--' 기호 미리 생성
		String[] arr2 = Arrays.copyOf(arr, idx); //인덱스에 맞춰서 자르고 복사
		Arrays.sort(arr2); // 문자열 정렬 ex1. 'A ', 'A', 'A B ', 'A B C' ...
		
		sb.append(arr2[0]).append('\n');
		for(int i=1; i<idx; i++) {
			if(arr2[i].equals(arr2[i-1])) continue; //arr2 배열에서 같은 값 제거
			String[] a = arr2[i].split(" ");
			int size = a.length - 1;
			sb.append(giho[size]).append(a[size]).append('\n');
		}
		
		//출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void make_giho() {
		giho[0] = "";
		for(int i=1; i<15; i++) {
			giho[i] = giho[i-1].concat("--");
		}
	}
}
