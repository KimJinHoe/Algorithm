package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA9760_PokerGame {
	
	static char typeEqual;
	static int[] val;
	static String[] result = new String[] 
			{"Straight Flush", "Four of a Kind", "Full House", "Flush",
			"Straight", "Three of a kind", "Two pair", "One pair", "High card"};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//입력
			val = new int[13]; //1~13
			typeEqual = 'n';
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<5; i++) {
				String str = st.nextToken();
				char ctype = str.charAt(0);
				char cval = str.charAt(1);
				//타입처리
				if(typeEqual == 'n') typeEqual = ctype;
				else if(typeEqual != ctype) typeEqual = 'x';
				//값처리
				if(cval <= '9') val[cval-'1']++;
				else if(cval == 'A') val[0]++;
				else if(cval == 'T') val[9]++;
				else if(cval == 'J') val[10]++;
				else if(cval == 'Q') val[11]++;
				else if(cval == 'K') val[12]++;
			}
			
			//출력
			sb.append('#').append(tc).append(' ').append(result[run()]).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int run() {
		int check4 = 0;
		int check3 = 0;
		int check2 = 0;
		for(int i=0; i<13; i++) {
			if(val[i]>=4) check4++;
			else if(val[i]==3) check3++;
			else if(val[i]==2) check2++;
		}
		boolean con = continuous5();
		if(typeEqual!='x' && con) return 0;
		if(check4!=0) return 1;
		if(check3!=0 && check2!=0) return 2;
		if(typeEqual !='x') return 3;
		if(con) return 4;
		if(check3 != 0) return 5;
		if(check2 == 2) return 6;
		if(check2 == 1) return 7;
		return 8;
	}
	
	static boolean continuous5() {
		int con = 0;
		for(int i=0; i<13; i++) {
			if(val[i]>1) return false;
			if(val[i]==1) {
				con++;
				if(con==5) return true;
			}
			else if(con>0 && val[i]==0) return false;
		}
		return true;
	}
}