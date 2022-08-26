package algo;
import java.util.*;
import java.io.*;

public class Boj_11000_강의실배정 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/algo/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		//입력
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//실행
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
		});

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(arr[0][1]);
		
		for(int i=1; i<N; i++) {
			if(pq.peek() <= arr[i][0]) pq.poll();
			pq.add(arr[i][1]);
		}
		
		//출력
		sb.append(pq.size());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
