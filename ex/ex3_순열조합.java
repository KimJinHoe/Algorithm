package Algorithm.ex;

import java.util.Arrays;
import java.util.Scanner;

public class ex3_순열조합 {
	
	static int N, R;
	static int[] input, numbers; // input: 입력수배열, numbers: 선택수 배열
	static int[] part_numbers;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		
		input = new int[N];
		part_numbers = new int[N];
		numbers = new int[R];
		isSelected = new boolean[N];
		
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
		
		System.out.println("순열");
		permutation(0);
		System.out.println("조합");
		combination(0,0);
		System.out.println("부분집합");
		for(int n=0; n<=N; n++ ) {
			subset(0,0,n);
		}
		
	}
	
	//순열
	static void permutation(int cnt) {
		//기저조건
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		//입력받은 모든 수를 현재 자리에 넣어보기
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			
			numbers[cnt] = input[i];
			isSelected[i] = true;
			//다음수 뽑으러 가기
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	//조합
	static void combination(int cnt,int start) {
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i=start; i<N; i++) {
			numbers[cnt] = input[i];
			combination(cnt+1, i+1);
		}
	}
	//부분집합 (조합 변형)
	static void subset(int cnt,int start, int n) {
		if(cnt == n) {
			System.out.println(Arrays.toString(part_numbers));
			return;
		}
		for(int i=start; i<N; i++) {
			part_numbers[cnt] = input[i];
			subset(cnt+1, i+1, n);
		}
	}
}
/*
입력
3 2
1 2 3
*/