package Algorithm.solve;
못품
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Boj_1287_할수있다 {
	
	static String str;
	static int cur;
	static int ans;
	static boolean flag = true;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		str = br.readLine();
		
		//실행
		run();
		
		//출력
		if(!flag) sb.append("ROCK");
		else sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void run() {
		if(!isokay()) {
			flag = false;
			return;
		}
		
		List<Integer> list = new ArrayList<Integer>();
		int[] arr = new int[4]; //+-, */, (, )
		int size = str.length();
		for(int i=0; i<size; i++) {
			int c = str.charAt(i)-'0'; //(:-8, ):-7, *:-6, +:-5, -:-3, /:-1 
			if(c == -5) arr[0]++;
			else if(c == -3) arr[0]++;
			else if(c == -6) arr[1]++;
			else if(c == -1) arr[1]++;
			else if(c == -8) arr[2]++;
			else if(c == -7) arr[3]++;
			list.add(c);
		}
		arrange(arr, list);
	}
	
	static void arrange(int[] arr, List<Integer> list) { //(:-8, ):-7, *:-6, +:-5, -:-3, /:-1
		int size = str.length();
		for(int i=0; i<size-1; i++) {
			int pre = list.get(i-1);
			int cur = list.get(i);
			int post = list.get(i+1);
			
			//가운데가 곱하기 혹은 나누기면서
			if(cur==-6 || cur==-1) {
				//양 옆이 숫자면
				if(pre>=0 && post>='0'&&post<='9')) {
					list.remove(i-1);
					list.remove(i-1);
					list.remove(i-1);
					if(cur=='*') list.add(i-1, pre-'0')
					list.add(i-1, )
				}
			}
		}
	}
	
	static boolean isokay() {
		int size = str.length();
		int pre_flag = 1;
		int cur_flag = 0;
		int garo_flag = 0;
		
		for(int i=0; i<size; i++) {
			char c = str.charAt(i);
			if(c>='0' && c<='9') cur_flag = 0;
			else if(c=='+' || c=='-') cur_flag = 1;
			else if(c=='*' || c=='/') cur_flag = 2;
			else if(c=='(') {
				if(i>0 && cur_flag==0) return false; //ex. 5(
				else if(i<size-1 && ('*'<=str.charAt(i+1) && '/'>=str.charAt(i+1))) return false; //ex. (+
				garo_flag++;
				continue;
			}
			else if(c==')') {
				garo_flag--;
				if(garo_flag < 0) return false;
				if(cur_flag!=0) return false;
				else if(i<size-1 && ('0'<=str.charAt(i+1))) return false;
				continue;
			}
			if(pre_flag==0 && cur_flag==0) return false; //숫자가 연속으로 오는 경우
			else if(pre_flag!=0 && cur_flag!=0) return false; //연산자가 연속으로 오는 경우
			pre_flag = cur_flag;
		}
		if(garo_flag != 0) return false;
		if(cur_flag != 0) return false;
		return true;
	}
}