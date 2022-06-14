package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
//SWEA 5644
public class Solution_0216_1 {
	
    static int[] pA; //pA, pB 위치 {x, y}
    static int[] pB;
    static int[] dy = {0 ,-1, 0, 1, 0}; // X 상 우 하 좌
    static int[] dx = {0 ,0, 1, 0, -1};
    static int total_time;
    static int num_bc;
    static int power;
     
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/Algorithm/solve/input26.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        //테스트 케이스 반복
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            total_time = Integer.parseInt(st.nextToken());
            num_bc = Integer.parseInt(st.nextToken()); 
            int[][] bc = new int[num_bc][4]; // bc의 정보를 저장
            int[] act_pA = new int[total_time]; //사람들의 행동 저장
            int[] act_pB = new int[total_time];
            //사람들 위치 초기화
            pA = new int[2];
            pB = new int[] {9,9};
            power = 0;
            //사람A 행동 입력
            st = new StringTokenizer(br.readLine()," ");
            for(int i=0; i<total_time; i++) act_pA[i] = Integer.parseInt(st.nextToken());
            //사람B 행동 입력
            st = new StringTokenizer(br.readLine()," ");
            for(int i=0; i<total_time; i++) act_pB[i] = Integer.parseInt(st.nextToken());
            //BC 정보 입력
            for(int i=0; i<num_bc; i++) {
                st = new StringTokenizer(br.readLine()," ");
                bc[i][0] = Integer.parseInt(st.nextToken())-1; //x인덱스
                bc[i][1] = Integer.parseInt(st.nextToken())-1; //y인덱스
                bc[i][2] = Integer.parseInt(st.nextToken()); //충전범위
                bc[i][3] = Integer.parseInt(st.nextToken()); //충전량
            }
            run(0, act_pA, act_pB, bc);
            sb.append('#').append(tc).append(' ').append(power).append('\n');
        }
         
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
     
    static void run(int time, int[] act_pA, int[] act_pB, int[][] bc) {
        //현재 위치가 BC범위 내 지역인지 확인
        int max_A = 0; //max:현 시간에 BC한테 받을 수 있는 가장 큰 충전량 
        int max_A_idx =0; //그 max의 인덱스
        int next_max_A = 0; //max 다음으로 큰 충전량
        int max_B = 0;
        int max_B_idx =0;
        int next_max_B = 0;
        for(int i=0; i<num_bc; i++) {
            //A사람 확인
            int d =Math.abs(pA[0]-bc[i][0]) + Math.abs(pA[1]-bc[i][1]);
            if(d <= bc[i][2]) {
                if(bc[i][3] > max_A) {
                    next_max_A = max_A;
                    max_A = bc[i][3];
                    max_A_idx = i;
                }
                else if(bc[i][3] >=next_max_A) {
                    next_max_A = bc[i][3];
                }
            }
            //B사람 확인
            d =Math.abs(pB[0]-bc[i][0]) + Math.abs(pB[1]-bc[i][1]);
            if(d <= bc[i][2]) {
                if(bc[i][3] > max_B) {
                    next_max_B = max_B;
                    max_B = bc[i][3];
                    max_B_idx = i;
                }
                else if(bc[i][3] >=next_max_B) {
                    next_max_B = bc[i][3];
                }
            }
        }
        //A,B의 max가 같은 BC의 충전량일 때
        if(max_A!=0 && max_A==max_B && max_A_idx==max_B_idx) {
            if(next_max_A==0 && next_max_B==0) {
                max_A /= 2;
                max_B /= 2;
            }
            else {
                if(next_max_A >= next_max_B) max_A = next_max_A;
                else max_B = next_max_B;
            }
        }
        //총 충전량 power에 값을 더함
        power += max_A + max_B;
        //시간 끝까지 가면 종료
        if(time == total_time) return;
        //좌표바꿈(지도 밖으로 나오는 경우가 없다고 문제에 제시되어 있기 때문에 범위확인은 하지 않음)
        pA[0] += dx[act_pA[time]];
        pA[1] += dy[act_pA[time]];
        pB[0] += dx[act_pB[time]];
        pB[1] += dy[act_pB[time]];
        //다음 시간으로 넘어감
        run(time+1, act_pA, act_pB, bc);
    }
     
}