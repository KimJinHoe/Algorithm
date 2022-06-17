package Algorithm.solve;
//536ms 2등~
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_14267_회사문화1_dp {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//입력
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] people = new int[N+1][2]; //상사, 칭찬값
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			people[i][0] = Integer.parseInt(st.nextToken());
		}
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			people[Integer.parseInt(st.nextToken())][1] += Integer.parseInt(st.nextToken());
		}
		
		//출력
		sb.append('0');
		for(int i=2; i<=N; i++) {
			people[i][1] += people[people[i][0]][1];
			sb.append(' ').append(people[i][1]);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
