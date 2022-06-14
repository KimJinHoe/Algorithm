package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_0208_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input9.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
        for (int tc = 0; tc < 10; tc++) {
        	Queue<Integer> que = new LinkedList<>();
        	int temp = 1;
        	int T = Integer.parseInt(br.readLine());
        	StringTokenizer st = new StringTokenizer(br.readLine()," ");
        	int min = 99999;
        	int[] nums = new int[8];
        	for(int i=0; i<8; i++) {
        		int num = Integer.parseInt(st.nextToken());
        		if(num < min) min = num; 
        		nums[i] = num;
        	}
        	min = min/15*15;
        	
        	for(int i=0; i<8; i++) {
        		nums[i] = nums[i] - min + 15;
        		que.offer(nums[i]);
        	}
        	
        	label: while(true) {
        		for(int i=1; i<=5; i++) {
            		temp = que.poll()-i;
            		if(temp<0) temp = 0;
            		que.offer(temp);
            		if(temp==0) break label;;
            	}
        	}
        	
        	sb.append('#').append(T);
        	for(int i=0; i<8; i++) {
        		sb.append(' ').append(que.poll());
        	}
        	sb.append('\n');
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}
