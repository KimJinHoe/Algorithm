package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj_3040_일곱난쟁이 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input13.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//난쟁이 배열 입력
		int N = 9;
		int[] dwarf = new int[N];
		int sum = 0;
		for(int i=0; i<N; i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
			sum += dwarf[i];
		}
		//출력 순서를 위한 clone 생성
		int[] answer = dwarf.clone();
		
		//정렬을 해서 최적화
		Arrays.sort(dwarf);
		//두 개 합 찾아야 하는 값 구함
		sum -= 100;
		int target1=0, target2=0;
		label: for(int i=0; i<N; i++) {
			for(int j=N-1; j>=0; j--) {
				if(dwarf[i] + dwarf[j] < sum) break;
				else if(dwarf[i] + dwarf[j] > sum) continue;
				else {
					target1 = dwarf[i];
					target2 = dwarf[j];
					break label;
				}
			}
		}
		
		//clone에서 target제외하고 출력
		for(int i=0; i<N; i++) {
			if(answer[i]!=target1 && answer[i]!=target2) {
				sb.append(answer[i]).append('\n');
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
