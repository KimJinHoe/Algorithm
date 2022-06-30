package cote;
//최적의 효울성은 KMP알고리즘이 좋음. (문자열 비교하고 맞으면 bomb길이만큼 배열포인터 이동)
import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_9935_문자열폭발_stack {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		Stack<Character> stack2 = new Stack<Character>();
		//입력
		String str = br.readLine();
		String bomb = br.readLine();
		//실행
		int len = str.length();
		int blen = bomb.length();
		for(int i=0; i<len; i++) {
			char c = str.charAt(i);
			stack.push(c);
			if(stack.size()>=blen && c==bomb.charAt(blen-1)) {
				for(int j=blen-1; j>-1; j--) {
					char temp = stack.pop();
					if(temp == bomb.charAt(j)) {
						stack2.push(temp);
					} else {
						stack.push(temp);
						while(!stack2.isEmpty()) {
							stack.push(stack2.pop());
						}
						break;
					}
				}
				stack2.clear();
			}
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		//출력
		if(sb.length() == 0) sb.append("ALURF");
		bw.write(sb.reverse().toString());
		bw.flush();
		bw.close();
		br.close();
	}
}

/*
uhuru0614님의 모범답안
public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();

        int inputSize = input.length;
        int bombSize = bomb.length;

        int top = 0;
        int index = 0;

        while (index < inputSize) {
            input[top++] = input[index];
            if (top >= bombSize) {
                int i = 1;
                for (; i <= bombSize; i++) {
                    if (input[top - i] != bomb[bombSize - i]) break;
                }
                if (i == bombSize + 1) {
                    for (i = 0; i < bombSize; i++) top--;
                }
            }
            index++;
        }

        if (top == 0) {
            System.out.println("FRULA");
        } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            bw.write(input, 0, top);
            bw.close();
        }

    }

 */
