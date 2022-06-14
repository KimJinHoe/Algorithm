//틀림. 오답.
package Algorithm.solve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//문자	의미
//.	평지(전차가 들어갈 수 있다.)
//*	벽돌로 만들어진 벽
//#	강철로 만들어진 벽
//-	물(전차는 들어갈 수 없다.)
//^	위쪽을 바라보는 전차(아래는 평지이다.)
//v	아래쪽을 바라보는 전차(아래는 평지이다.)
//<	왼쪽을 바라보는 전차(아래는 평지이다.)
//>	오른쪽을 바라보는 전차(아래는 평지이다.)


//문자	동작
//U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
//D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
//L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
//R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
//S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
public class Solution_0204_2 {
	
	static int height;
	static int width;
	static int y; //현재 전차 위치
	static int x;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/Algorithm/solve/input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		//T만큼 반복
		for(int tc=0; tc<T; tc++) {
			String[] str = br.readLine().split(" ");
			//H, W입력받기
			height = Integer.parseInt(str[0]);
			width = Integer.parseInt(str[1]);
			char[][] arr = new char[height][width];
			//배열 입력
			for(int i=0; i<height; i++) {
				String line = br.readLine();
				for(int j=0; j<width; j++) {
					arr[i][j] = line.charAt(j);
					if(arr[i][j] == '<'|| arr[i][j] == '>'|| arr[i][j] == '^'|| arr[i][j] == 'v') {
						y = i;
						x = j;
					}
				}
			}
			//N 입력받기
			int n = Integer.parseInt(br.readLine());
			String actions = br.readLine();
			run(arr, actions, n);
			sb.append("#").append(tc+1).append(" ");
			for(int i=0; i<height; i++) {
				sb.append(arr[i]).append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void run(char[][] arr, String actions, int n) {
		for(int i=0; i<n; i++) {
			char c = actions.charAt(i);
			if(y-1>=0 && c == 'U') {
				if(arr[y-1][x] == '.') {
					arr[y-1][x] = '^';
					arr[y][x] = '.';
					y--;
				}
				else {
					arr[y][x] = '^';
				}
			} else if(y+1<height && c == 'D') {
				if(arr[y+1][x] == '.') {
					arr[y+1][x] = 'v';
					arr[y][x] = '.';
					y++;
				}
				else {
					arr[y][x] = 'v';
				}
			} else if(x-1>=0 && c == 'L') {
				if(arr[y][x-1] == '.') {
					arr[y][x-1] = '<';
					arr[y][x] = '.';
					x--;
				}
				else {
					arr[y][x] = '<';
				}
			} else if(x+1<width && c == 'R') {
				if(arr[y][x+1] == '.') {
					arr[y][x+1] = '>';
					arr[y][x] = '.';
					x++;
				}
				else {
					arr[y][x] = '>';
				}
			} else if(c == 'S') {
				int temp_y = y;
				int temp_x = x;
				//현재 전차 방향에 따라서 발사 방향 다르게
				switch(arr[y][x]) {
				case '^':
					while(true) {
						y--;
						if(y<0 || arr[y][x] == '#') break;
						else if(arr[y][x] == '*') {
							arr[y][x] = '.';
							break;
						}
					}
					y = temp_y;
					break;
				case 'v':
					while(true) {
						y++;
						if(y>=height || arr[y][x] == '#') break;
						else if(arr[y][x] == '*') {
							arr[y][x] = '.';
							break;
						}
					}
					y = temp_y;
					break;
				case '<':
					while(true) {
						x--;
						if(x<0 || arr[y][x] == '#') break;
						else if(arr[y][x] == '*') {
							arr[y][x] = '.';
							break;
						}
					}
					x = temp_x;
					break;
				case '>':
					while(true) {
						x++;
						if(x>=width || arr[y][x] == '#') break;
						else if(arr[y][x] == '*') {
							arr[y][x] = '.';
							break;
						}
					}
					x = temp_x;
					break;
				}
			}
		}
	}
	
}