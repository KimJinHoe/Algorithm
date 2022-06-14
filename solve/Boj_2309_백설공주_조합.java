package Algorithm.solve;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Boj_2309_백설공주_조합 {
	static int[] Arr = new int[9];
	static int[] res = new int[7];
	static int[] temp = new int[7];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input23.txt"));
		Scanner sc = new Scanner(System.in);
		//배열입력
		for (int i = 0; i < 9; i++) {
			Arr[i] = sc.nextInt();
		}
		//조합 이용
		comb(0,0,0);
		Arrays.sort(temp);
		//출력
		for(int i = 0;i<7;i++) {
			System.out.println(temp[i]);
		}
	}

	private static boolean comb(int start, int cnt,int sum) {
		//7개의 조합을 뽑았을 때
		if(cnt == 7) {
			//합이 100이면 return true
			if(sum == 100) {
				temp = res.clone();
				return true;
			}
			return false;
		}
		for(int i = start;i<9;i++) {
			res[cnt] = Arr[i];
			//7개 조합이 100일 때까지 (true) 조합반복
			if(comb(i + 1, cnt + 1,sum+Arr[i])) return true;
		}
		return false;
	}
	
}
