package cote;
//2023.1.13 120ms 11등
import java.io.*;

public class Boj_16637_괄호추가하기_조합 {
	
	static int N;
	static int[] numbers;
	static char[] opers; //0,1,2: +,-,*
	static int R;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//입력
		N = Integer.parseInt(br.readLine());
		R = N/2; // 주어진 연산자의 수 = 괄호 가능한 개수
		numbers = new int[R+1];
		opers = new char[R+1]; // 첫 번째는 +를 넣음
		opers[0] = '+';
		
		String str = br.readLine();
		for(int i=0; i<N; i++) {
			if(i%2==0) numbers[i/2] = str.charAt(i) - '0';
			else opers[i/2 + 1] = str.charAt(i);
		}
		
		//실행
		comb(0, 0, 0);
		
		//출력
		bw.write(Integer.toString(max));
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void comb(int cnt, int flag, int num) {
		if(cnt == R) {
			//마지막에 괄호 안했으면 마지막 수 단순 연산하기 (코드 잘못짜서 이 조건 추가해야 함..)
			if((flag&(1<<(R-1))) == 0) num = oper(num, cnt);
			if(max < num) max = num;
			return;
		}
		
		//전 숫자가 괄호에 선택됐으면 현재 숫자는 괄호에 포함 못 함
		if(cnt>0 && (flag&(1<<(cnt-1))) != 0) {
			comb(cnt+1, flag, num); //안하거나
		} else {
			comb(cnt+1, flag|(1<<cnt), oper2(num, cnt)); //선택하거나
			comb(cnt+1, flag, oper(num, cnt)); //안하거나				
		}
	}
	
	//그냥 num과 연산
	static int oper(int num, int idx) {
		char c = opers[idx];
		if(c == '+') return num + numbers[idx];
		if (c == '-') return num - numbers[idx];
		return num * numbers[idx];
	}
	
	//다음꺼와 괄호연산하고 num과 연산
	static int oper2(int num, int idx) {
		//괄호 먼저 연산
		int num2 = numbers[idx];
		char c2 = opers[idx+1];
		if(c2 == '+') num2 += numbers[idx+1];
		else if (c2 == '-') num2 -= numbers[idx+1];
		else num2 *= numbers[idx+1];
		
		//num과 연산
		char c = opers[idx];
		if(c == '+') return num + num2;
		if (c == '-') return num - num2;
		return num * num2;
	}
}