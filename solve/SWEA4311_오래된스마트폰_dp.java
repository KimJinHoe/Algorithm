package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA4311_오래된스마트폰_dp {
	
	static int n, o, m;
	static List<Integer> opers;
	static int[] dp = new int[1000];
	static List<Integer> list;
	static int ans_num;
	
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
			n = Integer.parseInt(st.nextToken());
			o = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			opers = new ArrayList<Integer>();
			list = new ArrayList<Integer>();
			Arrays.fill(dp, -1);
			
			//숫자 입력 받음
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				int num = Integer.parseInt(st.nextToken());
				list.add(num);
				dp[num] = 1;
			}
			//연산자 입력 받음
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<o; i++) {
				opers.add(Integer.parseInt(st.nextToken()));
			}
			
			//희망 숫자 입력 받음
			ans_num = Integer.parseInt(br.readLine());
			
			//실행
			basic_input();
			//basic_input에서 원하는 숫자가 안만들어지면
			if(dp[ans_num]==-1) {
				run();
				//만들어지면 =연산 추가
				if(dp[ans_num] != -1) dp[ans_num]++;
			}
			
			//출력
			sb.append('#').append(tc).append(' ').append(dp[ans_num]).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void basic_input() {
		int size = list.size();
		for(int i=0; i<size; i++) {
			if(list.get(i)==0) continue;
			for(int j=0; j<size; j++) {
				int num = list.get(i)*10+list.get(j);
				dp[num] = 2;
				list.add(num);
			}
		}
		for(int i=0; i<size; i++) {
			if(list.get(i)==0) continue;
			for(int j=0; j<size; j++) {
				for(int k=0; k<size; k++) {
					int num = list.get(i)*100+list.get(j)*10+list.get(k);
					dp[num] = 3;
					list.add(num);
				}
			}
		}
	}
	
	static void exe_oper(int num1, int num2) {
		if(dp[num1]+dp[num2] > m-2) return;
		int num;
		for(int i=0; i<o; i++) {
			int oper = opers.get(i);
			if(oper == 1) {
				num = num1 + num2;
			} else if (oper == 2) {
				num = num1 - num2;
			} else if (oper == 3) {
				num = num1 * num2;
			} else{
				if(num2 == 0) continue;
				num = num1 / num2;
			}
			if(num<0 || num>999) continue;
			if(dp[num] == -1) {
				dp[num] = dp[num1] + dp[num2] + 1;
				list.add(num);
			}
		}
	}
	
	static void run() {
		int temp_size = 0; //맨 처음에 size와 값을 다르게 하려고
		int size = list.size();
		int first_size = size;
		
		while(temp_size != size) {
			for(int i=temp_size; i<size; i++) { //추가 된 것들.
				for(int j=0; j<first_size; j++) { //맨 처음 기존 있었던 것들.
					exe_oper(list.get(i), list.get(j));
				}
			}
			if(dp[ans_num] != -1) return;
			temp_size = size;
			size = list.size();
		}
	}
}