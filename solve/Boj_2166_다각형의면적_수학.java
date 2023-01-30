package cote;
//2023.01.30 128ms 1등
import java.io.*;

//신발끈의 정리 참고
public class Boj_2166_다각형의면적_수학 {
	
	static int N;
	
	public static void main(String[] args) throws Exception{
		//입력
		System.setIn(new FileInputStream("src/cote/input_temp.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = read();
		
		//입력 및 계산
		long x1 = read() + 100000;
		long y1 = read() + 100000;
		long x = read() + 100000;
		long y = read() + 100000;
		long sum = x1*y - y1*x;
		N -= 2;
		while(N--> 0) {
			long tx = read() + 100000;
			long ty = read() + 100000;
			sum += x*ty - y*tx;
			x = tx;
			y = ty;
		}
		sum += x*y1 - y*x1;
		
		//출력
		sb.append(Math.abs(sum/2)).append((sum%2==0) ? ".0" : ".5");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    boolean isNegative = n == 13;
	    if (isNegative) n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	    if (c == 13) System.in.read();
	    return isNegative ? ~n + 1 : n;
	}
}
