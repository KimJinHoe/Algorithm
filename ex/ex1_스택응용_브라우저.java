package Algorithm.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class ex1_스택응용_브라우저 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<String> backword = new Stack<>();
		Stack<String> forword = new Stack<>();

		String current = "http://www.ssafy.com";

		while (true) {
			String input = br.readLine();
			if (input.charAt(0) == 'Q')
				break;

			StringTokenizer st = new StringTokenizer(input, " ");

			switch (st.nextToken()) {
			case "V":
				backword.push(current);
				forword.clear();
				current = st.nextToken();
				break;
			case "B":
				if (backword.isEmpty()) {
					System.out.println("Ignored..");
					continue;
				}
				forword.push(current);
				current = backword.pop();
				break;
			case "F":
				if (forword.isEmpty()) {
					System.out.println("Ignored..");
					continue;
				}
				backword.push(current);
				current = forword.pop();
				break;
			}
			System.out.println(current);
		}
	}
}
