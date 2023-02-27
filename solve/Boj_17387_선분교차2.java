package cote;
//비슷한 문제인 2162 선분그룹 풀이법 조금 수정하고 제출함 (불필요한 연산 있음)
//CCW알고리즘안쓰고 기울기 구하는 나누는 방식을 써서 일부 테케에 나눗셈의 오차가 있고 오차값 지정해서 해결
//매우 최악 복잡 스파게티 따라하지 마시오...
import java.io.*;
import java.util.*;

public class Boj_17387_선분교차2 {
	
	static int[][][] edges;
	static double[][] infos;
	static double OCHA = 0.0001;
	
	public static void main(String[] args) throws Exception {
		
		edges = new int[2][2][2];
		infos = new double[2][2]; // `y=ax+b`에서 a와 b의 값을 담고 있음
		
		for(int i=0; i<2; i++) {
			edges[i][0][0] = read();
			edges[i][0][1] = read();
			edges[i][1][0] = read();
			edges[i][1][1] = read();
			
			// a=(y2-y1)/(x2-x1), b=y-ax
			if(edges[i][1][0] == edges[i][0][0]) {
				infos[i][0] = Double.MAX_VALUE; //그래프가 수직인 경우
				infos[i][1] = edges[i][0][0]; //a를 MAX, b를 x절편으로 설정
				continue;
			}
			infos[i][0] = (double)(edges[i][1][1]-edges[i][0][1])/(edges[i][1][0]-edges[i][0][0]);
			infos[i][1] = edges[i][1][1] - infos[i][0]*edges[i][1][0];
		}
		
		int[][] edge1 = edges[0];
		double[] info1 = infos[0];
		int[][] edge2 = edges[1];
		double[] info2 = infos[1];
		//기울기가 같고
		if(info1[0] == info2[0]) {
			//y절편이 다르면 평행 -> 만나지 않음
			if(info1[1] != info2[1]) {
				System.out.println(0);
				return;
			}
			if(info1[0] == Double.MAX_VALUE) {
				int min1 = Math.min(edge1[0][1], edge1[1][1]);
				int max1 = Math.max(edge1[0][1], edge1[1][1]);
				int min2 = Math.min(edge2[0][1], edge2[1][1]);
				int max2 = Math.max(edge2[0][1], edge2[1][1]);
				if((edge1[0][1]+OCHA >= min2 && edge1[0][1]-OCHA <= max2) || (edge1[1][1]+OCHA >= min2 && edge1[1][1]-OCHA <= max2)
						|| (edge2[0][1]+OCHA >= min1 && edge2[0][1]-OCHA<=max1 && edge2[1][1]+OCHA >= min1 && edge2[1][1]-OCHA<=max1)) {
					System.out.println(1);
					return;
				}
				System.out.println(0);
				return;
			}
			int min1 = Math.min(edge1[0][0], edge1[1][0]);
			int max1 = Math.max(edge1[0][0], edge1[1][0]);
			int min2 = Math.min(edge2[0][0], edge2[1][0]);
			int max2 = Math.max(edge2[0][0], edge2[1][0]);
			
			//둘 다 같으면 같은 선분
			//한 Edge안 에 W다른 Edge 좌표가 있으면 접한다는 뜻
			if((edge1[0][0]+OCHA >= min2 && edge1[0][0]-OCHA <= max2) || (edge1[1][0]+OCHA >= min2 && edge1[1][0]-OCHA <= max2)
					|| (edge2[0][0]+OCHA >= min1 && edge2[0][0]-OCHA<=max1 && edge2[1][0]+OCHA >= min1 && edge2[1][0]-OCHA<=max1)) {
				System.out.println(1);
				return;
			}
		} else {
			//기울기가 다른 경우
			//1)하나가 기울기가 Double.MAX일 경우 (x=b인 경우)
			double x, y;
			if(info1[0] == Double.MAX_VALUE) {
				x = info1[1];
				y = info2[0] * x + info2[1];
				int min1 = Math.min(edge1[0][1], edge1[1][1]);
				int max1 = Math.max(edge1[0][1], edge1[1][1]);
				int min2 = Math.min(edge2[0][1], edge2[1][1]);
				int max2 = Math.max(edge2[0][1], edge2[1][1]);
				if((y+OCHA >= min2 && y-OCHA <= max2) && (y+OCHA >= min1 && y-OCHA<= max1)) {
					if(info2[0] == 0) {
						int xmin2 = Math.min(edge2[0][0], edge2[1][0]);
						int xmax2 = Math.max(edge2[0][0], edge2[1][0]);
						if(x < xmin2 || x > xmax2) {
							System.out.println(0);
							return;
						}
					}
					System.out.println(1);
					return;
				}
				
			} else if (info2[0] == Double.MAX_VALUE) {
				x = info2[1];
				y = info1[0] * x + info1[1];
				int min1 = Math.min(edge1[0][1], edge1[1][1]);
				int max1 = Math.max(edge1[0][1], edge1[1][1]);
				int min2 = Math.min(edge2[0][1], edge2[1][1]);
				int max2 = Math.max(edge2[0][1], edge2[1][1]);
				if((y+OCHA >= min2 && y-OCHA <= max2) && (y+OCHA >= min1 && y-OCHA<= max1)) {
					if(info1[0] == 0) {
						int xmin1 = Math.min(edge1[0][0], edge1[1][0]);
						int xmax1 = Math.max(edge1[0][0], edge1[1][0]);
						if(x < xmin1 || x > xmax1) {
							System.out.println(0);
							return;
						}
					}
					System.out.println(1);
					return;
				}
			} else {
				//2) Long.MAX인 기울기가 없는 경우. 접점. x=(d-b)/(a-c), y=a(d-b)/(a-c)+b
				x = (info2[1]-info1[1])/(info1[0]-info2[0]);
				y = info1[0] * x + info1[1];
				int min1 = Math.min(edge1[0][0], edge1[1][0]);
				int max1 = Math.max(edge1[0][0], edge1[1][0]);
				int min2 = Math.min(edge2[0][0], edge2[1][0]);
				int max2 = Math.max(edge2[0][0], edge2[1][0]);
				if((x+OCHA >= min1 && x-OCHA <= max1) && (x+OCHA >= min2 && x-OCHA <= max2)) {
					System.out.println(1);
					return;
				}
			}
		}
		
		System.out.println(0);
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c=System.in.read())>32) n = (n<<3) + (n<<1) + (c&15);
		return negative ? ~n + 1 : n;
	}
}
