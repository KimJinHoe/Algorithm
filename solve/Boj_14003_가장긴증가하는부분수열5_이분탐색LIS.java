package cote;
// 2023.02.28 728ms 1등
//하지만 못 풀어서 다른 사람 풀이법 참고하고 복습 때 풂.
import java.io.*;
import java.util.*;

public class Boj_14003_가장긴증가하는부분수열5_이분탐색LIS {
	
	static int N;
	static int[] arr;
	static int[] cntArr;
	static List<Integer> list;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = read();
		arr = new int[N];
		cntArr = new int[N]; //해당 arr[i]의 cnt 수
		list = new ArrayList<Integer>(); //순서 출력을 위한 리스트
		
		//숫자 배열 저장
		for(int i=0; i<N; i++) {
			arr[i] = read();
		}
		
		//맨 처음 삽입
		list.add(arr[0]);
		cntArr[0] = 1;
		
		//그 후 실행
		for(int i=1; i<N; i++) {
			int num = arr[i];
			
			//리스트의 마지막 수보다 크면 그냥 추가
			if(num > list.get(list.size()-1)) {
				list.add(num);
				cntArr[i] = list.size();
				continue;
			}
			
			//작으면 이분탐색해서 리스트(LIS)의 적절한 위치에 값 교환
			int idx = binarySearch(num);
			list.set(idx, num);
			cntArr[i] = idx+1;
		}
		
		//첫번째 줄 출력 (리스트 길이 = 최장 길이)
		sb.append(list.size()).append('\n');
		
		//순서 stack에 추가하여 역순 출력 (맨 뒤부터 idx를 줄이고 확인하면서 최종 결과값만 출력하도록) 
		Stack<Integer> st = new Stack<Integer>();
		int idx = list.size();
		for(int i=N-1; i>=0; i--) {
			if(cntArr[i] == idx) {
				idx--;
				st.push(arr[i]);
			}
		}
		
		while(!st.isEmpty()) {
			sb.append(st.pop()).append(' ');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int binarySearch(int num) {
		int low = 0;
        int high = list.size() - 1;
        
		while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = list.get(mid);

            if (midVal < num)
                low = mid + 1;
            else if (midVal > num)
                high = mid - 1;
            else
            	return mid;
		}
		return low;
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c=System.in.read())>32) n = (n<<3) + (n<<1) + (c&15);
		if(c==13) System.in.read();
		return negative ? ~n + 1 : n;
	}
}
