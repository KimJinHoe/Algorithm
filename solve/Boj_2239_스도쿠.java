package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Boj_2239_스도쿠 {
	static int map[][];
    
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//입력
		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		//실행
		run();
		
		//출력
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	static boolean run() { 
		Point cur = findZero();
		
		//안 채운 숫자가 없으면 종료
		if (cur == null) { 
			return true;
		}
		
		//후보 숫자 찾기 
		List<Integer> numbers = getNumbers(cur.x, cur.y); 
		
		//후보 숫자를 찾지 못한 경우 
		if (numbers.size() == 0) {
			return false; 
		}
		
		//후보 수를 차례대로 넣어보며 재귀
		for (int i : numbers) {
			map[cur.x][cur.y] = i;
			if (run()) return true;
		}
		//백트래킹
		map[cur.x][cur.y] = 0;
		return false; 
	}

	static Point findZero() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == 0)
					return new Point(i, j);
			}
		}
		return null;
	}

	static List<Integer> getNumbers(int x, int y) { // 현재 위치 가능한 숫자 찾기 행검색, 열검색, 3*3검색 
		List<Integer> numbers = new ArrayList<Integer>();
		boolean[] check = new boolean[10];

		for (int i = 0; i < 9; i++) {
			check[map[x][i]] = true; //같은 행에있는 숫자 체크
			check[map[i][y]] = true; //같은 열에있는 숫자 체크
		}
		
		//3*3 위치 찾아서 있는 숫자 체크
		int startx = x/3*3;
		int starty = y/3*3;
		for (int i = startx; i < startx+3; i++) {
			for (int j = starty; j < starty+3; j++) {
				check[map[i][j]] = true;
			}
		}
		
		//아직 체크안된 숫자를 후보숫자에 넣음
		for (int i = 1; i < 10; i++) {
			if (!check[i]) numbers.add(i);
		}
		return numbers;
	}
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	
}