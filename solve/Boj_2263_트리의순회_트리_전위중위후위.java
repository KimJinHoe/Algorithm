package cote;
//효율성안좋음. 재귀를 트리기준으로 돌리면 효율성 좋음. 참고 https://www.acmicpc.net/source/43177806
/*
ex. 중위입력값:4 2 5 1 6 3 7, 후위입력값:4 5 2 6 7 3 1
      1
    2   3
   4 5 6 7
1. 후위의 마지막 인덱스가 트리의 루트 (1)
2. 루트(1)의 바로 전에 후위입력된 값(3)이 중위입력에서 루트(1)보다 나중에 입력됐으면 해당 값이 루트의 right(3)
3. 루트(1)보다 전에 중위입력한 값이 left트리(4,2,5) + 뒤에 중위입력한 값이 right트리(6,3,7)
4. left트리(4,2,5)에서 가장 나중에 후위입력된 것이 left트리의 루트(2)
5. 단계2를 똑같이 적용해서 루트(2)의 right값은 (5) ... 반복
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_2263_트리의순회_트리_전위중위후위 {
	
	
	static int N;
	static int[] in; //중위 입력 순으로 배열에 입력
	static int[] post; //후위 입력 순으로 배열에 입력
	static int[] idxin; //N개의 노드 순으로 중위순서idx 입력
	static int[] idxpost; //N개의 노드 순으로 후위순서idx 입력
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//입력
		N = Integer.parseInt(br.readLine());
		in = new int[N+1];
		post = new int[N+1];
		idxin = new int[N+1];
		idxpost = new int[N+1];
		//중위입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			in[i] = num;
			idxin[num] = i;
		}
		//후위입력
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			post[i] = num;
			idxpost[num] = i;
		}
		//후위의 마지막 입력정점이 트리의 루트
		int root = Integer.parseInt(st.nextToken());
		post[N] = root;
		idxpost[root] = N;
		//실행
		if(idxin[post[N-1]] > idxin[root]) {
			run(root, post[N-1]);
		} else {
			run(root, -1);
		}
		
		//출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run(int root, int right) {
		//루트체크
		sb.append(root).append(' ');
		idxpost[root] = 0;
		
		//좌측
		int size = idxin[root];
		int left = 0;
		int max = Integer.MIN_VALUE;
		for(int i=size-1; i>0; i--) {
			if(idxpost[in[i]] == 0) break;
			if(max < idxpost[in[i]]) {
				left = in[i];
				max = idxpost[left];
			}
		}
		if(max != Integer.MIN_VALUE) {
			if(idxin[post[idxpost[left]-1]] > idxin[left]) run(left, post[idxpost[left]-1]);
			else run(left, -1);
		}
		
		//우측
		if(right != -1) {
			if(idxin[post[idxpost[right]-1]] > idxin[right]) run(right, post[idxpost[right]-1]);
			else run(right, -1);
		}
	}
	
}