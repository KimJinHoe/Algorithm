package Algorithm.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//SWEA 하나로
public class Solution_0223_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input30.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//테스트케이스 반복
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			//섬의 좌표 입력
			int[][] xy = new int[N][2];
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<N; j++) {
					xy[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			double[] minedge = new double[N];
			boolean[] visited = new boolean[N];
			
			//인접행렬 생성
			double E = Double.parseDouble(br.readLine());
			double[][] arr = new double[N][N];
			for(int i=0; i<N; i++) {
				for(int j=i; j<N; j++) {
					double price = E*(Math.pow(xy[i][0]-xy[j][0], 2)+Math.pow(xy[i][1]-xy[j][1], 2)); 
					arr[i][j] = arr[j][i] = price;
				}
				minedge[i] = Double.MAX_VALUE;
			}
			
			double result = 0;
			minedge[0] = 0;
			//PRIM알고리즘
			for(int c=0; c<N; c++) {
				double min = Double.MAX_VALUE;
				int minvertex = 0;
				//min갱신 및 인덱스 저장
				for(int i=0; i<N; i++) {
					if(!visited[i] && minedge[i]<min ) {
						min = minedge[i];
						minvertex = i;
					}
				}
				//방문처리 및 결과값에 더함
				visited[minvertex] = true;
				result += min;
				//minedge 갱신
				for(int i=0; i<N; i++) {
					if(!visited[i] && arr[minvertex][i]!=0 && arr[minvertex][i] < minedge[i]) {
						minedge[i] = arr[minvertex][i];
					}
				}
			}
			long ans = (long)Math.round(result);
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
