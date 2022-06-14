package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_2839_설탕배달 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int ini_N = N;
		
		if(N==4 || N==7) System.out.println(-1);
		else if(N%5 == 0) System.out.println(N/5);
		else if(N%5==1 || N%5==3) System.out.println((N/5)+1);
		else if(N%5==2 || N%5==4) System.out.println((N/5)+2);
	}
}

/*
입력
18
4
6
9
11
*/
