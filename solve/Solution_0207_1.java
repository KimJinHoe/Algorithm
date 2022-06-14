package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
  
public class Solution_0207_1 {
  
    static int result = 0;
  
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/Algorithm/solve/input6.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
  
        for (int tc = 1; tc <= T; tc++) {
            StringBuilder sb = new StringBuilder();
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int L = Integer.parseInt(line[1]);
            result = 0;
            //점수, 칼로리 입력   // 0: 맛점수, 1: 칼로리
            int[][] values = new int[N][2];
            for (int i = 0; i < N; i++) {
                line = br.readLine().split(" ");
                values[i][0] = Integer.parseInt(line[0]);
                values[i][1] = Integer.parseInt(line[1]);
            }
  
            com_food(N, L, values, 0, 0, 0);
            sb.append('#').append(tc).append(" ").append(result).append('\n');
            bw.write(sb.toString());
        }
  
        bw.flush();
        bw.close();
        br.close();
    }
  
    static void com_food(int N, int L, int[][] values, int idx, int score, int cal) {
        if (idx == N) {
            if (cal <= L) result = Math.max(result, score);
            return;
        }
  
        com_food(N, L, values, idx + 1, score + values[idx][0], cal + values[idx][1]);
        com_food(N, L, values, idx + 1, score, cal);
    }
}