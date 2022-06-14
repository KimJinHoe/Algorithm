package Algorithm.ex;

import java.util.Scanner;

public class ex4_조합_np_바이너리카운팅 {
	
	static int[] input;
	static int N,R;
	//static int[] p;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		
		input = new int[N];
		
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}	

		System.out.println("넥스트 조합");
		nextcomb();
		System.out.println("-----------------------------------------------");
		
		System.out.println("바이너리 카운팅");
		binarysubset();
	}
	
	//넥스트 조합
	static void nextcomb() {
		// p배열에 0보다 큰 값으로 R개를 맨 뒤부터 채운다.
		// 4C2 0011
		int[] p = new int[N];
		int cnt = 0;
		while(++cnt<=R) p[N-cnt]=1;
		
		do {
			for(int i=0; i<N; i++) {
				if(p[i]==1) System.out.print(input[i]+" ");
			}
			System.out.println();
		}while(np(p));
	}
	
	private static boolean np(int[] p) {
		// 1. 교환위치 찾기
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i]) i--;

		if (i == 0) return false;

		// 2. 교환위치에 교환할 값 찾기
		int j = N - 1;
		while (p[i - 1] >= p[j]) j--;

		// 3. 교환위치와 교환할 값 교환하기
		swap(p, i - 1, j);

		// 4. 교환위치 뒤(꼭대기)부터 맨 뒤까지 만들 수 있는 가장 작은 순열생성(오름차순정렬)
		int k = N - 1;
		while (i < k) {
			swap(p, i++, k--);
		}
		return true;
	}

	public static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
	
	private static void binarysubset() {
		for(int flag=0, casecnt = 1<<N; flag<casecnt; flag++) {
			// flag : 원소들의 선택상태의 비트열
			// 각 비트열의 상태를 확인
			for(int i=0; i<N; i++) { 
				if((flag&1<<i) != 0) {
					System.out.print(input[i]+" ");
				}
			}
			System.out.println();
		}
	}
}
