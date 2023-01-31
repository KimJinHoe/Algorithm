package cote;
//2023.01.31 204ms 2등 
//이진탐색보다는 투포인터가 훨씬 좋은 방법임. 이진탐색 O(NlogN). 투포인터 O(N)
import java.io.*;

public class Boj_2467_용액_이분탐색 {
	
	static int N;
	static int[] liquid;
	static int min = Integer.MAX_VALUE;
	static int[] ans = new int[2];
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//입력
		N = read();
		liquid = new int[N];
		for(int i=0; i<N; i++) {
			liquid[i] = read();
		}
		
		//실행
		for(int i=0; i<N; i++) {
			if(binarysearch(i) == 0) break;
		}

		//출력
		sb.append(ans[0]).append(' ').append(ans[1]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static int binarysearch(int idx) {
		int key = liquid[idx];
		int low = 0;
		int high = N-1;
		
		//0일 때 처리
		if(key == 0) {
			if(idx == 0) return getmin1(key, liquid[idx+1]); //idx==0 => 왼쪽값없음
			if(idx == N-1) return getmin1(key, liquid[idx-1]); //idx==N-1 => 오른쪽값없음
			return getmin2(key, liquid[idx-1], liquid[idx+1]);
		}
		
		//이분탐색
		while(low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = liquid[mid];
			
			if(-key < midVal) high = mid - 1; //합이 0이 되는 것을 찾을거니 -key를 탐색
			else if(-key > midVal) low = mid + 1;
			else {
				//찾았다는 것은 합이 0이 되는 것을 찾은 경우이므로 끝
				ans[0] = key;
				ans[1] = -key;
				return 0;
			}
		}
		//*모든 경우의 수에 해당 확인하는 인덱스가 자기 자신인지 확인해야 함
		//1. low == N일 때, N-1을 확인 (자기자신이면 N-2를 확인)
		if(low == N) {
			if(idx == N-1) low--;
			return getmin1(key, liquid[--low]);
		}
		
		//2. low == 0일 때, 0을 확인 (자기자신이면 1를 확인)
		if(low == 0) {
			if(idx == 0) low++;
			return getmin1(key, liquid[low]);
		}
		
		//3. low == N-1일 때, N-2와 N-1을 확인 (N-2가 자기자신이면 N-3과 N-1 확인, N-1이 자기자신이면 N-2만 확인)
		if(low == N-1) {
			int llow = N-2;
			int hlow = N-1;
			if(hlow == idx) return getmin1(key, liquid[llow]);
			if(llow == idx) llow -= 1;
			return getmin2(key, liquid[llow], liquid[hlow]);
		}

		//4. low == 1일 때, 0과 1을 확인 (0이 자기자신이면 1만 확인, 1이 자기자신이면 0과 2 확인)
		if(low == 1) {
			int llow = 0;
			int hlow = 1;
			if(llow == idx) return getmin1(key, liquid[hlow]);
			if(hlow == idx) hlow += 1;
			return getmin2(key, liquid[llow], liquid[hlow]);
		}
		
		//5. low == 중간인덱스일 때, low-1과 low를 확인 (low-1이 자기자신이면 low-2와 low확인, low가 자기자신이면 low-1과 low+1 확인)
		int llow = low - 1;
		int hlow = low;
		if(hlow == idx) hlow += 1;
		if(llow == idx) llow -= 1;
		return getmin2(key, liquid[llow], liquid[hlow]);
	}
	
	static int getmin1(int key, int key2) {
		int mix = Math.abs(key+key2);
		if(min > mix) {
			ans[0] = key;
			ans[1] = key2;
			min = mix;
		}
		return min;
	}
	
	static int getmin2(int key, int key2, int key3) {
		int mix2 = Math.abs(key+key2);
		int mix3 = Math.abs(key+key3);
		if(mix2 > mix3) {
			mix2 = mix3;
			key2 = key3;
		}
		
		if(min > mix2) {
			ans[0] = key;
			ans[1] = key2;
			min = mix2;
		}
		return min;
	}
	
	static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    boolean isNegative = n == 13;
	    if (isNegative) n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if (c == 13) System.in.read();
	    return isNegative ? ~n + 1 : n;
	}
}
