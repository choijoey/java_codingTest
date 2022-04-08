package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baek1956 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[V+1][V+1];
		final int MAX = 999_999_999;
		
		for(int i=1;i<=V;i++) {
			for(int j=1;j<=V;j++)
				map[i][j] = MAX;
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from= Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int val= Integer.parseInt(st.nextToken());
			map[from][to] = val;
		}
		
		//플로이드 워셜 
		for(int k=1;k<=V;k++) {
			for(int i=1;i<=V;i++) {
				for(int j=1;j<=V;j++) {
					if(map[i][k]==MAX||map[k][j]==MAX)
						continue;
					if(map[i][j] > map[i][k]+map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
					}
				}
			}
		}
		
		int min = MAX;
		
		//i,i 값 => 사이클이므로 최소값 확인
		for(int i=1;i<=V;i++) {
			min =Math.min(min, map[i][i]);
		}
		
		if(min == MAX)
			System.out.println(-1);
		else
			System.out.println(min);
	}

}
