package Algorithm.solve;
//324ms, 6등
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

public class Boj_2812_크게만들기_구현 {
	
	static int N, K;
	static int[] arr;
	static String input;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		String input = br.readLine();
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = input.charAt(i)-'0';
		}
		
		//실행
		int cur = 0;
		int cur2 = 1;
		while(cur2<N && K>0) {
//			System.out.println(Arrays.toString(arr) + " " + cur + " " + cur2 + "  K:" + K);
			if(arr[cur] < arr[cur2]) {
				K--;
				arr[cur] = -1;
				while(cur>=0 && arr[cur]==-1) cur--;
				if(cur < 0) {
					cur = cur2;
					cur2 += 1;
				}
			}
			else {
				cur++;
				cur2++;
				if(arr[cur] == -1) cur = cur2 - 1;
			}
		}
		//N이상으로 돌렸지만 K가 남았을 때는 뒤에만 제거
		cur = N-1;
		while(K-->0) {
			if(arr[cur] != -1) arr[cur--] = -1;
			else {
				cur--;
				K++;
			}
		}
		
		//출력
		for(int num : arr) {
			if(num != -1) sb.append(num);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
