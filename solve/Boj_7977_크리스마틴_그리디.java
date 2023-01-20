package cote;
//2023.1.20 1등 132ms
import java.io.*;

public class Boj_7977_크리스마틴_그리디 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		char[] str = br.readLine().toCharArray();
		char[] dna = {'G','C','A','T'};
		int[] cnt = new int[4];
		
		for(int i=0; i<str.length; i++) {
			if(str[i] == 'G') {
				cnt[0]++;
			} else if(str[i] == 'C') {
				cnt[1]++;
			} else if(str[i] == 'A') {
				cnt[2]++;
			} else {
				cnt[3]++;
			}
		}
		
		int min = Integer.MAX_VALUE;
		char c = 'G';
		for(int i=0; i<4; i++) {
			if(min > cnt[i]) {
				min = cnt[i];
				c = dna[i];
			}
		}
		
		sb.append(min).append('\n');
		while(N-->0) {
			sb.append(c);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}