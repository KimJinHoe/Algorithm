package cote;
//2023.02.13 1등 128ms 자바 통과한 사람 2명
import java.io.*;
import java.math.BigInteger;
import java.util.Stack;

public class Boj_1287_할수있다_BigIntegerStack {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<Character> operator = new Stack<>();
        Stack<BigInteger> nums = new Stack<>();
        int len = input.length();

        /////////////////////2023.02.13 문제 재채점되면서 추가한 조건
        char first = input.charAt(0);
        char last = input.charAt(len-1);
        if(input.contains("()") ||
           first == ')' || first == '+' || first == '-' || first == '/' || first == '*' ||
          last == '(' || last == '+' || last == '-' || last == '/' || last == '*') {
        	bw.write("ROCK");
            bw.flush();
            return;
        }
        //////////////////

        for (int i = 0; i < len; i++) {
        	
            BigInteger temp = BigInteger.ZERO;
            boolean isDigit = false;
            
            //숫자면 nums에 추가함
            while (i< len && Character.isDigit(input.charAt(i))) {
                temp = temp.multiply(BigInteger.TEN);
                temp = temp.add(BigInteger.valueOf(input.charAt(i)-'0'));
                i++;
                isDigit = true;
            }
            if(isDigit) nums.add(temp);

            //i가 범위를 넘으면 그만
            if(i >= len) break;
            
            //숫자 판별 로직에서 i++로 다음은 무조건 숫자가 아닌 문자임
            char c = input.charAt(i);
            // ')'일 때,
            if (c == ')') {
            	// '('를 만날 때까지 연산 실행
                while (!operator.isEmpty() && operator.peek() != '(') {
                    if (!calc(nums, operator.pop())) {
                        return;
                    }
                }
                // '('가 없으면 ROCK
                if (operator.isEmpty() || operator.peek() != '(') {
                    bw.write("ROCK");
                    bw.flush();
                    return;
                }
                operator.pop();
                
                // '('일 때, oper에 추가
            } else if (c == '(') {
                operator.push(c);
                
                //다른 문자일 때, 연산자가 스택에 없으면 추가. 연산자가 있으면 검사
            } else {
                while (!operator.isEmpty() && getPrio(operator.peek()) >= getPrio(c)) {
                	//문자가 사칙연산이 아니면 ROCK
                    if (getPrio(operator.peek()) == -1 || getPrio(c) == -1) {
                        bw.write("ROCK");
                        bw.flush();
                        return;
                    }
                    
                    //앞 연산이 연산 우선순위가 높으면 계산
                    if (!calc(nums, operator.pop())) {
                        return;
                    }
                }
                operator.add(c);
            }
        }
        //연산 남았으면 계산
        while (!operator.isEmpty()) {
            if (!calc(nums, operator.pop())) {
                return;
            }
        }
        //연산이 다 끝났는데 nums에 남은 숫자가 1개가 아니면 ROCK
        if(nums.size() != 1){
            bw.write("ROCK\n");
            bw.flush();
            //정상 출력
        }else {
            bw.write(nums.pop().toString() + "\n");
            bw.flush();
        }
    }

    private static boolean calc(Stack<BigInteger> nums, char op) throws IOException {
        //연산자에 계산할 숫자가 2개가 아니면 ROCK
    	if (nums.size() < 2) {
            bw.write("ROCK");
            bw.flush();
            return false;
        }
        BigInteger p1 = nums.pop();
        BigInteger p2 = nums.pop();

        //두번째 수가 0이고 연산자가 '/'일 때만 ROCK
        if (p1.equals(BigInteger.ZERO) && op =='/') {
            bw.write("ROCK");
            bw.flush();
            return false;
        }

        //사칙연산을 수행하고 사칙연산이 아닌 기호면 ROCK
        if (op == '+')
            nums.push(p2.add(p1));
        else if (op == '-')
            nums.push(p2.subtract(p1));
        else if (op == '*')
            nums.push(p2.multiply(p1));
        else if (op == '/')
            nums.push(p2.divide(p1));
        else {
            bw.write("ROCK");
            bw.flush();
            return false;
        }
        return true;
    }

    //우선순위(?) 리턴
    private static int getPrio(int ch) {
        if (ch == '(') return 0;
        else if (ch == '*' || ch == '/') return 2;
        else if (ch == '+' || ch == '-') return 1;
        else return -1;
    }
}