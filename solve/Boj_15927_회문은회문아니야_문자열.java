package cote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_15927_회문은회문아니야_문자열 {
	
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		//입력
		String str = br.readLine();
		String str1 = null;
		String str2 = null;
		//실행
		int len = str.length();
		//길이가 짝수일 때
		if(len%2==0) {
			str1 = str.substring(0, len/2);
			str2 = sb.append(str.substring(len/2, len)).reverse().toString();
			//회문아니면 답
			if(!str1.equals(str2)) ans = len;
			else {
				//회문이면 인덱스 1 줄여서 다시 확인
				str1 = str1.substring(0, len/2-1);
				str2 = str2.substring(1);
				if(!str1.equals(str2)) ans = len-1;
				else ans = -1;
			}
		}
		//길이가 홀수일 때
		else {
			str1 = str.substring(0, len/2);
			str2 = sb.append(str.substring(len/2+1, len)).reverse().toString();
			//회문아니면 답
			if(!str1.equals(str2)) ans = len;
			else {
				//회문이면 인덱스 1 줄여서 다시 확인
				str1 = str.substring(1, len/2+1);
				if(!str1.equals(str2)) ans = len-1;
				else ans = -1;
			}
			
		}
		
		//출력
		System.out.println(ans);
	}
}
