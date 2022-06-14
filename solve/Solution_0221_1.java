package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//SWEA Contact 못품
public class Solution_0221_1 {
		
	public static void main(String[] args) {
		System.setIn(new FileInputStream("src/Algorithm/solve/input29.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        for(int tc=1; tc<=1; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine()," ");
        	int N = Integer.parseInt(st.nextToken());
        	int S = Integer.parseInt(st.nextToken());
        	int[][] arr = new int[100][100];
        	st = new StringTokenizer(br.readLine()," ");
        	for(int i=0; i<N/2; i++) {
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        	}
        	
        	
        	sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}
