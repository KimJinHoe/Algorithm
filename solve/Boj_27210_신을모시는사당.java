package cote;

import java.util.*;
import java.io.*;

public class Boj_27210_신을모시는사당 {
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int acnt = 0;
		int bcnt = 0;
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			if(st.nextToken().charAt(0) == '1') {
				acnt++;
				if(bcnt>0) bcnt--;
				max = Math.max(max, acnt);
			} else {
				bcnt++;
				if(acnt>0) acnt--;
				max = Math.max(max, bcnt);
			}
		}
		System.out.println(max);
	}
}
