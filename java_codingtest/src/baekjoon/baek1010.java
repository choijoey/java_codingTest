package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baek1010 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		
		//mCn 계산
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());

		int[][] map = new int[31][31]; //이항계수 dp
		
		
		for(int i=0;i<31;i++) {
			for(int j=1;j<i;j++) {
			}
		}
		for(int i=0;i<31;i++) {
			for(int j=0;j<=i;j++) {
				if(j==0 || i==j) {
					map[i][j]=1;
				}
				else
					map[i][j]=map[i-1][j-1]+map[i-1][j];
			}
		}
		
		StringBuilder sb= null;
		
		for(int i=0;i<T;i++) {
			st= new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			sb=new StringBuilder();
			sb.append(map[M][N]).append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
		
	}

}
