package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1158_요세푸스 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input17.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> que = new LinkedList<>();
        // 큐 입력
        for (int i = 1; i <= N; i++) que.offer(i);
 
        sb.append("<");
        // Queue의 사이즈가 1일 때까지 반복한다.
        while(que.size() != 1) {
            // K-1번째까지  맨 뒤로 보낸다.
            for (int i = 0; i < K - 1; i++) que.offer(que.poll());
            // K번째 값은 poll한 후 출력한다.
            sb.append(que.poll() + ", ");
        }
        
        // 마지막 하나 출력
        sb.append(que.poll() + ">");
        //출력
        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

