package Algorithm.solve; 

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1074_Z {
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken()); //행
		int c = Integer.parseInt(st.nextToken()); //열
		int size = 2<<N; //한 변의 사이즈 2^N
		
		find(size, r, c);
		System.out.println(cnt);
	}

	private static void find(int size, int r, int c) {
		if(size == 1)
			return;
		
		if(r < size/2 && c < size/2) { //1사분면
			find(size/2, r, c);
		}
		else if(r < size/2 && c >= size/2) { //2사분면
			cnt += size * size / 4;
			find(size/2, r, c - size/2);
		}
		else if(r >= size/2 && c < size/2) { //3사분면
			cnt += (size * size / 4) * 2;
			find(size/2, r - size/2, c);
		}
		else { //4사분면
			cnt += (size * size / 4) * 3;
			find(size/2, r - size/2, c - size/2);
		}
	}
}
//입력 2 3 1
//입력 10 512 512
