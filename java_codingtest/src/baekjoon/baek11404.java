package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baek11404 {

	//플로이드 워셜 O(V^3)
	//http://boj.kr/957ee444aab340dda843c083974a757b
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb;
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());//정점 개수
		int E = Integer.parseInt(br.readLine());//간선 개수

		int[][] map = new int[N][N];

		final int INF = 999_999_999; 

		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				map[i][j] =INF;

		for(int i=0;i<N;i++)
			map[i][i]=0;

		for(int i=0;i<E;i++) {
			st= new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int val = Integer.parseInt(st.nextToken());
			
			if(map[from][to]==INF)
				map[from][to]=val;
			else if(map[from][to]>val)
				map[from][to]=val;
		}


		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				if(k==i) continue;
				for(int j=0;j<N;j++) {
					if(i==j || k==j) continue;

					if(map[i][j]> map[i][k]+map[k][j])
						map[i][j] = map[i][k]+map[k][j];
				}
			}
		}


		for(int i=0;i<N;i++) {
			sb = new StringBuilder();
			for(int j=0;j<N;j++) {
				if(map[i][j]==INF)
					map[i][j] =0;
				sb.append(map[i][j]).append(" ");
			}
			bw.write(sb.toString().trim());
			bw.write("\n");
		}
		bw.flush();
		bw.close();

	}

}
