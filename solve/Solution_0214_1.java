package Algorithm.solve;
//SWEA6808
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_0214_1 {
	//나의 카드
	static int[] me_nums = new int[9];
	static int[] you_nums = new int[9];
	static int[] you_result = new int[9];
	static boolean[] isSelected = new boolean[9];
	static int win_cnt;
	static int me_point;
	static int you_point;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input24.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//총 경우의 수
		int total = 1;
		for(int i=1; i<=9; i++) total *= i;
		
		//테스트 케이스 반복
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			//초기화
			boolean[] nums = new boolean[18];
			win_cnt = 0;
			
			//자신의 카드 입력
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<9; i++) {
				int num = Integer.parseInt(st.nextToken());
				me_nums[i] = num;
				nums[num-1] = true;
			}
			
			//상대방 카드 입력
			int cnt = 0;
			for(int i=0; i<18; i++) {
				if(!nums[i]) you_nums[cnt++] = i+1;
			}
			
			//순열로 상대방 카드의 모든 경우의 수 실행
			perm(0);
			sb.append('#').append(tc).append(' ').append(win_cnt).append(' ').append(total-win_cnt).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void perm(int cnt) {
		//기저조건
		if(cnt == 9) {
			me_point = 0;
			you_point = 0;
			for(int i=0; i<9; i++) {
				int me = me_nums[i];
				int you = you_result[i];
				if(me>you) me_point += me+you;
				else if(me<you) you_point += me+you;
			}
			if(me_point > you_point) win_cnt++;
			return;
		}
		
		//입력받은 모든 수를 현재 자리에 넣어보기
		for(int i=0; i<9; i++) {
			if(isSelected[i]) continue;
			
			you_result[cnt] = you_nums[i];
			isSelected[i] = true;
			//다음수 뽑으러 가기
			perm(cnt+1);
			isSelected[i] = false;
		}
	}
	
}
