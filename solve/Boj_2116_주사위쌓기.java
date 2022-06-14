package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_2116_주사위쌓기 {
	static int max = Integer.MIN_VALUE;
	static int N;
	static int[][] dices;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		N = Integer.parseInt(br.readLine());
		dices = new int[N][6];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			dices[i][0] = Integer.parseInt(st.nextToken()); //A(0)-F(5) : 마주보는 면의 인덱스 합이 5
			dices[i][1] = Integer.parseInt(st.nextToken()); //B(1)-D(4)
			dices[i][2] = Integer.parseInt(st.nextToken()); //C(2)-E(3)
			dices[i][4] = Integer.parseInt(st.nextToken()); //D(4)-B(1)
			dices[i][3] = Integer.parseInt(st.nextToken()); //E(3)-C(2)
			dices[i][5] = Integer.parseInt(st.nextToken()); //F(5)-A(0)
		}
		//실행
		for(int i=1; i<7; i++) {
			dfs(i, 0);
			if(max == N*6) break;
		}
		
		//출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int num1, int cnt) { //밑 면의 숫자, 선택한 주사위 수
		if(cnt==N) {
			if(max < result) max = result;
			result = 0;
			return;
		}
		
		//num2 찾기
		int num2 = 0;
		for(int i=0; i<6; i++) {
			if(dices[cnt][i] == num1) num2 = dices[cnt][5-i];
		}
		
		//값더하기
		if(num1*num2 == 30) result += 4;
		else if(num1 == 6 || num2 == 6) result +=5;
		else result += 6;
		
		//다음 주사위
		dfs(num2, cnt+1);
	}
}
