package Algorithm.solve;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Boj_17406_배열돌리기4 {
    static int N, M,K, min = Integer.MAX_VALUE;;
    static int[][] arr ;
    static int[] order ;
    static boolean[] checked;
	static Rotate rot[];
	
	public static void main(String[] args)throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input22.txt"));

		Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //행 x row
        M = sc.nextInt(); //열 y col
        K = sc.nextInt(); //연산 개수 
        
        arr = new int[N][M];
        //배열 입력
        for(int i=0;i<N;i++) {
        	for(int j=0;j<M;j++) {
        		arr[i][j] = sc.nextInt();
        	}
        }
        //명령어 저장하는 객체
        rot = new Rotate[K]; 
        for(int i=0;i<K;i++) {
            int r = sc.nextInt()-1;
            int c = sc.nextInt()-1;
            int s = sc.nextInt();
        	rot[i] = new Rotate(r,c,s);
        }

        order = new int[K]; 
        checked = new boolean[K];
        run(0);
        //출력
        System.out.println(min);
    }
	
	static void run(int cnt) {
		//K만큼 명령을 다 수행한 경우 min을 찾음
		if(cnt==K) {
			for(int i=0; i<N; i++) 
				min = Math.min(min, IntStream.of(arr[i]).sum());
			return;
		}
		//명령어 K개수 순열
		for(int i=0; i<K; i++) {
			//해당 명령어 실행했으면 패스
			if(checked[i]) continue;
			
			checked[i] = true;
			//명령어 순서 저장
			order[cnt] = i;
			//정사각형 좌표
			int rs = rot[order[cnt]].r - rot[order[cnt]].s; //왼쪽위 y좌표
			int cs = rot[order[cnt]].c - rot[order[cnt]].s; //왼쪽위 x좌표
			int re = rot[order[cnt]].r + rot[order[cnt]].s; //오른쪽아래 y좌표
			int ce = rot[order[cnt]].c + rot[order[cnt]].s; //오른쪽아래 x좌표
			//s만큼 시계방향 돌림
			for(int j=0; j<rot[order[cnt]].s; j++)
				rightRotate(rs+j, re-j, cs+j, ce-j);
			//다음 명령어 실행
			run(cnt+1);  
			//해당 명령어 끝내고 배열값을 원래대로 돌려놓음
			for(int j=0; j<rot[order[cnt]].s; j++)
				leftRotate(rs+j, re-j, cs+j, ce-j); 
			checked[i] = false;
		}
	}
	
	static void rightRotate(int rs, int re, int cs, int ce) {
		int temp = arr[rs][cs];
		for(int i=rs; i<re; i++) arr[i][cs] = arr[i+1][cs]; 
		for(int i=cs; i<ce; i++) arr[re][i] = arr[re][i+1];
		for(int i=re; i>rs; i--) arr[i][ce] = arr[i-1][ce];
		for(int i=ce; i>cs; i--) arr[rs][i] = arr[rs][i-1];
		arr[rs][cs+1] = temp;
	}
	static void leftRotate(int rs, int re, int cs, int ce) {
		int temp = arr[rs][cs];
		for(int i=cs; i<ce; i++) arr[rs][i] = arr[rs][i+1];
		for(int i=rs; i<re; i++) arr[i][ce] = arr[i+1][ce]; 
		for(int i=ce; i>cs; i--) arr[re][i] = arr[re][i-1];
		for(int i=re; i>rs; i--) arr[i][cs] = arr[i-1][cs];
		arr[rs+1][cs] = temp;
	}
	//명령어 값 저장하는 객체
	static class Rotate {
		int r, c, s;
		public Rotate(int r, int c, int s) {
			this.r = r;	this.c = c;	this.s = s;
		}
		@Override
		public String toString() {
			return "Rotate [r=" + r + ", c=" + c + ", s=" + s + "]";
		}
	}
}

