package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//SWEA3289서로소집합, Boj1717
public class Solution_0222_1 {
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        //테스트 케이스 반복
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	sb.append('#').append(tc).append(' ');
        	StringTokenizer st = new StringTokenizer(br.readLine()," ");
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	
        	//배열 만들기
        	parents = new int[N];
        	for(int i=0; i<N; i++) parents[i] = i;
        	
        	//명령어 실행하기
        	for(int i=0; i<M; i++) {
        		st= new StringTokenizer(br.readLine()," ");
        		int action = Integer.parseInt(st.nextToken());
        		int a = Integer.parseInt(st.nextToken())-1; // 0인덱스에 들어가는 수는 1이니 인덱스와 맞춤 
        		int b = Integer.parseInt(st.nextToken())-1;
        		switch(action) {
        			case 0: union(a,b); break;
        			case 1: sb.append(check(a,b)); break;
        		}
        	}
        	sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
	
	//최상위 부모 찾기
	static int findset(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findset(parents[a]);
	}
	
	//다른 부모를 가지면 합치기
	static void union(int a, int b) {
		int aRoot = findset(a);
		int bRoot = findset(b);
		if(aRoot == bRoot) return;
		parents[bRoot] = aRoot;
	}
	
	//같은 루트를 갖고 있는지 확인
	static int check(int a, int b) {
		int aRoot = findset(a);
		int bRoot = findset(b);
		if(aRoot == bRoot) return 1;
		return 0;
	}
}
