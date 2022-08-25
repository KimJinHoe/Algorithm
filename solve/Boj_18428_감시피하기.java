package algo;
import java.util.*;
import java.io.*;

public class Boj_18428_�������ϱ� {
	
	static int N;
	static char[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean ans;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/algo/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		//�Է�
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		//����
		dfs(0, 0, 0);
		
		//���
		if(ans) bw.write("YES");
		else bw.write("NO");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int y, int x, int cnt) {
		if(ans) return; //������ ��츦 �̹� ã������ ����
		if(cnt == 3) {
			//�ʿ��� T�� ã�Ƽ� ���þȰɸ����� üũ
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++){
					if(map[i][j] != 'T') continue;
					if(check(i, j)) continue; //���
					else return; //�ɸ�
				}
			}
			ans = true;
			return;
		}
		
		for(int j=x+1; j<N; j++) {
			if(map[y][j] != 'X') continue;
			map[y][j] = 'O';
			dfs(y, j, cnt+1);
			map[y][j] = 'X';
		}
		
		for(int i=y+1; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 'X') continue;
				map[i][j] = 'O';
				dfs(i, j, cnt+1);
				map[i][j] = 'X';
			}
		}
	}
	
	static boolean check(int y, int x) {
		for(int d=0; d<4; d++) {
			int ny = y;
			int nx = x;
			while(true) {
				ny += dy[d];
				nx += dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=N) break; //���(������)
				if(map[ny][nx] == 'S') return false; //���� �ɸ�
				if(map[ny][nx] == 'O') break; //���
				if(map[ny][nx] == 'T') break; //���
			}
		}
		return true; //����
	}
}
