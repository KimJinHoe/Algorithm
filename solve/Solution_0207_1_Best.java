package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_0207_1_Best {
 
    // 재료 수, 제한 칼로리를 static 으로 사용
	static int N,L;
    // dp 배열 => Dynamic Programming
    static int[] dp;
      
    public static void main(String[] args) throws Exception {
 
        // 빠른 입력을 위한 BufferedReader 사용
    	System.setIn(new FileInputStream("src/Algorithm/solve/input6.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        // 테스트 케이스의 수 읽기, 그 횟수만큼 반복
        int ts = Integer.parseInt(br.readLine());
        for (int t = 1; t <= ts; t++) {
 
            // 한 줄을 읽어서 공백을 기준으로 문자열을 나눔
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            // 첫 번째는 재료의 수, 두 번째는 제한 칼로리
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            // dp 배열 객체 생성
            dp = new int[L+1];
             
            // 모든 재료의 정보를 받으며 계산 진행
            for (int i = 0; i < N; i++) {
                // 한 줄을 입력받아 공백을 기준으로 나누어 score, calory 받기
                st = new StringTokenizer(br.readLine(), " ");
                int score = Integer.parseInt(st.nextToken());
                int calory = Integer.parseInt(st.nextToken());
 
                // sol 함수를 통해 계산
                sol(score, calory);
            }
              
            // dp 배열의 마지막 원소가 정답이 됨?
            System.out.println("#"+t+" " + dp[L]);
        }
    }
 
    // score 와 calory를 통해 계산을 진행하는 함수
    public static void sol(int score, int calory) {
        // i 는 제한 칼로리로 부터 sol 을 호출할 때의
        // 칼로리까지 1씩 줄이며 반복
        for (int i = L; i >= calory; i--) {
 
            // dp[i] 의 값이 score + dp[i-calory] 보다 작으면 dp[i] 갱신
            // dp[i] 는 재료 조합의 calory 합이 i 가 될 때의 최대 점수
            if(dp[i] < score + dp[i-calory]){
                dp[i] = score + dp[i-calory];
            }
        }
    }
  
}