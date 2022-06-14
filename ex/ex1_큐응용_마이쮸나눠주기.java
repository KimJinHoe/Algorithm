package Algorithm.ex;

import java.util.LinkedList;
import java.util.Queue;

public class ex1_큐응용_마이쮸나눠주기 {
	public static void main(String[] args) {
		int N = 20; //마이쮸개수
		int person = 1; //대기열에 새롭게 등장하는 사람의 번호
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {person,1});
		
		while(N>0) {
			int[] p = que.poll();
			int available_cnt = (N>=p[1]) ? p[1] : N;
			N -= available_cnt;
			
			if(N==0) {
				System.out.println("마지막 마이쮸를 가져간 사람 : " + p[0]+", 가져간 마이쮸 개수 : "+available_cnt);
			} else {
				System.out.println("마이쮸를 가져간 사람 : " + p[0]+", 가져간 마이쮸 개수 : "+available_cnt);
				
				//받은 사람 넣기, 개수 한개 늘려서..
				p[1]++;
				que.offer(p);
				//새로운 사람 넣기
				que.offer(new int[] {++person,1});
			}
		}
		
	}
}
