package codetest;

import java.io.*;
import java.util.*;

public class cotest01 {
	
	static class Node {
		int cnt;
		List<Integer> flaglist = new ArrayList<Integer>();
		List<Integer> peoplenumlist = new ArrayList<Integer>();
		
		public Node(int cnt) {
			this.cnt = cnt;
		}
	}
	static int N;
	static int[] people;
	static int half;
	static Node[] nodes = new Node[4001];
	static int max = Integer.MIN_VALUE;
	static int wans;
	
	public static void main(String[] args) {
		int[] weight = {100, 60, 40, 20, 35, 45};
		people = weight;
		N = people.length;
		
		//nodes 초기화
		for(int i=0; i<4001; i++) {
			nodes[i] = new Node(0);
		}
		
		//총몸무게 절반 계산
		for(int i=0; i<N; i++) {
			half += people[i];
		}
		half = half/2 + 1;
		
		//부분조합
		for(int i=1; i<N; i++) {
			pick(0 ,i, 0, 0);
		}
		
		//해당 몸무게 2가지 이상이면 가능하고, 선수가 다르면 뽑음
		int w = -1;
		for(Node node : nodes) {
			w++;
			if(node.cnt < 2) continue;
			
			//선수 겹치는지 확인
			List<Integer> list = node.flaglist;
			List<Integer> peoplenumlist = node.peoplenumlist;
			int size = list.size();
			
			for(int i=0; i<size; i++) {
				int flag1 = list.get(i);
				for(int j=0; j<size; j++) {
					if(i == j) continue; //같은 경우면 continue
					int flag2 = list.get(j);
					if((flag1|flag2) != flag1+flag2) continue; //선수가 겹치면  continue
					int num = peoplenumlist.get(i) + peoplenumlist.get(j);
					if(max > num) continue; // 참여 인원이 max보다 크면 진행
					max = num;
					wans = w;
				}
			}
		}

		System.out.println(max + " " + wans);
	}
	
	static void pick(int cnt, int R, int flag, int sum) {
		if(sum > half) return;
		
		if(cnt == R) {
			nodes[sum].cnt++;
			nodes[sum].flaglist.add(flag);
			nodes[sum].peoplenumlist.add(cnt);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((flag & 1<<i) != 0) continue;
			pick(cnt+1, R, flag|1<<i, sum+people[i]);
		}
	}
}
