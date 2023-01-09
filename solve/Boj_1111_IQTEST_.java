package cote;

import java.io.*;
import java.util.*;

//2023.01.09 기준 124ms 15등(시간효율 공동 2등)
public class Boj_1111_IQTEST_ {
	
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		// 배열에 수 넣기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2개인데 수가 같으면 3번째도 같음
		if(N == 2 && arr[0]==arr[1]) {
			bw.write(Integer.toString(arr[0]));
			bw.flush();
			bw.close();
			return;
		}
		//2개 이하면 다양한 수 가능
		else if(N <= 2) {
			bw.write("A");
			bw.flush();
			bw.close();
			return;
		}
		
		// 조건에 맞는 i와 num을 찾음 (a*i + num)
		for(int i=-200; i<=200; i++) {
			int num = arr[1] - arr[0] * i;
			int cnt = 2;
			while(arr[cnt]-arr[cnt-1]*i == num) {
				if(cnt == N-1) {		
					bw.write(Integer.toString(arr[N-1]*i+num));
					bw.flush();
					bw.close();
					return;
				}
				cnt++;
			}
		}
		bw.write("B");
		bw.flush();
		bw.close();
	}
}
