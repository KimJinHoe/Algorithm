package Algorithm.ex;

import java.util.Arrays;
import java.util.Scanner;

public class ex4_순열_비트마스킹_np {
	
	static int N, R;
	static int[] input, numbers; // input: 입력수배열, numbers: 선택수 배열
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		
		input = new int[N];
		numbers = new int[R];
		
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
		System.out.println("비트마스킹 순열");
		permutation(0, 0);
		System.out.println("넥스트 순열");
		nextperm(input);
		
	}
	
	//비트 마스킹을 이용한 순열 : 정수 int형이 32개 bit이기 때문에 기존 isSelected가 32개를 넘으면 안 됨
	// flag & 1<<i : flag에서 특정 위치가 1(true)인지 확인
	// flag | 1<<i : flag의 특정 위치를 1로 바꿈 (flag값을 바꾸고 재귀로 넘기는 역할)
	static void permutation(int cnt, int flag) {
		//기저조건
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		//입력받은 모든 수를 현재 자리에 넣어보기
		for(int i=0; i<N; i++) {
			if((flag & 1<<i) != 0) continue;
			numbers[cnt] = input[i];
			//다음수 뽑으러 가기
			permutation(cnt+1, flag|1<<i);
		}
	}
	
	//Next순열
	//시간 복잡도가 낮지만  nPr과 같이 특정 개수의 순열을 뽑아낼 수 없다.
	static void nextperm(int[] input) {
		Arrays.sort(input);
		do {
			//순열출력
			System.out.println(Arrays.toString(input));
		} while(np());
	}
	
	private static boolean np() {
		//1. 교환위치 찾기
		int i= N-1;
		while(i>0 && input[i-1]>=input[i]) i--;
		
		if(i==0) return false;
		
		//2. 교환위치에 교환할 값 찾기
		int j = N-1;
		while(input[i-1]>=input[j]) j--;
		
		//3. 교환위치와 교환할 값 교환하기
		swap(i-1, j);
		
		//4. 교환위치 뒤(꼭대기)부터 맨 뒤까지 만들 수 있는 가장 작은 순열생성(오름차순정렬)
		int k = N-1;
		while(i<k) {
			swap(i++, k--);
		}
		return true;
	}
	
	public static void swap(int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
}
/*
입력
3 2
1 2 3
*/