package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Boj_1253_좋다_HashMap {
	
	static int N;
	static int ans;
	static int[] nums;
	static Map<Integer, Integer> maps = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			nums[i] = num;
			if(!maps.containsKey(num)) {
				maps.put(num, 1);
			}
			else {
				maps.put(num, maps.get(num)+1);
			}
		}
        
        //실행
		run();
        
		//출력
        sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run() {
        for(int i=0; i<N; i++) {
        	for(int j=i+1; j<N; j++) {
        		int num = nums[i] + nums[j];
        		if(maps.containsKey(num)) {
        			int val = maps.get(num);
        			// 0,0 -> 0  |  0,0,0 -> 3
        			if(nums[i]==0 && nums[j]==0) {
        				if(val < 3) continue;
        				ans += val;
        			}
        			//0,1 -> 0  |  0,1,1 -> 2
        			else if(nums[i]==0 || nums[j]==0) {
        				if(val < 2) continue;
        				ans += val;
        			}
        			else {
        				ans+= val;
        			}
        			maps.remove(num);
        		}
        	}
        }
	}
}