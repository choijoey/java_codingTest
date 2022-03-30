package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek1600 {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][][] v = new int[N][M][K+1];
		
		

		int[][] m1= {{0,1},{0,-1},{1,0},{-1,0}};
		int[][] m2= {{-1,-2},{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2}};

		int ans=-1;
		

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] {0,0,0});
		v[0][0][0]=0;
		
		
		outer: while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			
			if(cur[2] < K) {
				for(int i=0;i<8;i++) {
					int ny = cur[0]+m2[i][0];
					int nx = cur[1]+m2[i][1];
					
					if(ny<0||ny>=N||nx<0||nx>=M)
						continue;
					
					if(v[ny][nx][cur[2]+1]!=0)
						continue;
					if(map[ny][nx]==1)
						continue;
					if(N-1==ny && M-1==nx) {
						ans = v[cur[0]][cur[1]][cur[2]]+1;
						break outer;
					}
					queue.offer(new int[] {ny,nx,cur[2]+1});
					v[ny][nx][cur[2]+1] = v[cur[0]][cur[1]][cur[2]]+1;
				}
			}
			
			for(int i=0;i<4;i++) {
				int ny = cur[0]+m1[i][0];
				int nx = cur[1]+m1[i][1];
				
				if(ny<0||ny>=N||nx<0||nx>=M)
					continue;
				
				if(v[ny][nx][cur[2]]!=0)
					continue;
				if(map[ny][nx]==1)
					continue;
				if(N-1==ny && M-1==nx) {
					ans = v[cur[0]][cur[1]][cur[2]]+1;
					break outer;
				}
				queue.offer(new int[] {ny,nx,cur[2]});
				
				v[ny][nx][cur[2]] = v[cur[0]][cur[1]][cur[2]]+1;
			}
		}
		
		
		if(N==1 && M==1)
			ans=0;

		
		bw.write(ans+"");
		bw.flush();
		bw.close();

	}

}
