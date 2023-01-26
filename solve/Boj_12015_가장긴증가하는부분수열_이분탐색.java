package cote;
//2023.01.26 276ms 1등
import java.io.*;

public class Boj_12015_가장긴증가하는부분수열_이분탐색 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		System.out.println(read());
		System.out.println(read());
		System.out.println(read());
		int N = read();
		int[] arr = new int[N];
		arr[0] = read();
		int idx = 0;
		N--;
		while (N-- > 0) {
			int num = read();

			// 가장 큰 수면 맨 뒤에 추가
			if (arr[idx] < num) {
				arr[++idx] = num;
				continue;
			}

			// 가장 큰 수가 아니면 삽입할 위치를 찾음 (이분탐색 nlogn)
			int low = 0;
			int high = idx;
			int mid = 0;
			while (low <= high) {
				mid = (low + high) >> 1;

				if (arr[mid] < num) {
					low = mid + 1;
				} else if(arr[mid] > num) {
					high = mid - 1;
				}else {
					low = mid;
					break;
				}
			}
			
			//삽입할 위치는 원래 해당 위치의 수보다 무조건 작으므로 그냥 교체
			arr[low] = num;
		}

		System.out.println(idx+1);
	}
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}
