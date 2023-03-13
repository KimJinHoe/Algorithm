package cote;
//2023.03.13 120ms 3등
import java.io.*;
import java.util.*;

public class Boj_1918_후위표기식_Deque {
	
	static Deque<String> st = new ArrayDeque<String>();
	static Deque<Integer> opers = new ArrayDeque<Integer>();
	
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int c = 0;
		int cnt = 0; // '(' 카운트
		
		while((c=System.in.read()) != -1) {
			if(c <= 32) break;
			
			//연산자
			if(c < 'A') { //opers ASCI CODE: 40~47
				opers.offer(c);
				if(c == '(') cnt++;
				else if(c == ')') {
					
					//'(' 앞에 있는 연산자와 연산값 빼서 뒤로 넣기
					int oper = opers.pollFirst();
					cnt--;
					int tmp = cnt;
					while(tmp!=0  || oper!='(') {
						opers.offer(oper);
						if(oper == '(') tmp--;
						else st.offer(st.pollFirst());
						oper = opers.pollFirst();
					}
					
					//'('와 ')' 안에 있는 연산자들 계산하고 결과 뒤에 넣기
					oper = opers.pollFirst();
					sb.append(st.pollFirst());
					while(oper != ')') {
						sb.append(st.pollFirst()).append((char)oper);
						oper = opers.pollFirst();
					}
					
					// ()바로 앞에 곱셈이나 나눗셈있었으면 처리
					if(opers.size() != 0) oper = opers.peekLast();
					if(oper=='*' || oper=='/') {
						opers.pollLast();
						sb.insert(0, st.pollLast()).append((char)oper);
					}
					st.offer(sb.toString());
					sb.setLength(0);
				}
				continue;
			}
			
			//연산값
			st.offer(Character.toString(c));
			if(opers.size() == 0) continue;
			int oper = opers.peekLast();
			if(oper=='*' || oper=='/') {
				opers.pollLast();
				sb.append(st.pollLast()); // B
				sb.insert(0, st.pollLast()).append((char)oper); // AB*
				st.offer(sb.toString());
				sb.setLength(0);
			}
		}
		
		sb.append(st.pollFirst());
		int size = opers.size();
		while(size-->0) {
			int oper = opers.pollFirst();
			sb.append(st.pollFirst()).append((char)oper);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
    }
    
}