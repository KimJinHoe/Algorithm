package Algorithm.solve;
못품
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA8458_원점으로집합 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			//입력
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int odd_max = Integer.MIN_VALUE;
			int even_max = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i] += Math.abs(Integer.parseInt(st.nextToken()));
				}
				if(arr[i]%2==0) { //짝수, 0
					if(arr[i] > even_max) even_max = arr[i];
				} else { //홀수
					if(arr[i] > odd_max) odd_max = arr[i];
				}
			}
			//실행
			//전부 짝수거나 홀수면 실행
			int ans;
			if(even_max==Integer.MIN_VALUE || odd_max==Integer.MIN_VALUE) {
				ans = run(odd_max==Integer.MIN_VALUE?even_max:odd_max);
			} else { //짝수 홀수 섞였을 때 실행(최소공배수)
				ans = -1;
			}
			
			//출력
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int run(int num) {
		int n =1;
//		int n = (int)((-1+Math.sqrt(1+1*num))/2);
		int total = n*(n+1)/2;
		
		if(total>=num && (total-num)%2==0 ) {
			return n;
		}
		for(int i=n+1; i<65535; i++) {
			total += i;
			if((total-num)%2==0 ) {
				return i;
			}
		}
		
		return -1;
	}
}
