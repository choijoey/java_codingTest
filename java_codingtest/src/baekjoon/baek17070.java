package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek17070 {


	static int[][] map;
	static long[][][] dp;
	static boolean[][][] v;
	static int N;


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;


		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		dp = new long [N][N][3];
		v = new boolean[N][N][3];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0,1,0);
		System.out.println(dp[0][1][0]+dp[0][1][1]+dp[0][1][2]);
	}
	static int[][] dir0 = {{0,1,0},{1,1,2}};//가로
	static int[][] dir1 = {{1,0,1},{1,1,2}};//세로
	static int[][] dir2 = {{0,1,0},{1,0,1},{1,1,2}};//대각선

	static long dfs(int y,int x,int shape) {
		
		if(y==N-1&&x==N-1)
			return 1;
		
		if(dp[y][x][shape] != 0)
			return dp[y][x][shape];

		if(!v[y][x][shape]) {
			v[y][x][shape] =true;

			if(shape == 0) {
				for(int i=0;i<2;i++) {
					int ny = y+dir0[i][0];
					int nx = x+dir0[i][1];
					if(ny<0||ny>=N||nx<0||nx>=N)
						continue;
					if(map[ny][nx]==1)
						continue;
					if(i==1&&(map[ny-1][nx]==1||map[ny][nx-1]==1))
						continue;
					dp[y][x][shape] +=dfs(ny,nx,dir0[i][2]);
				}
			}
			else if(shape == 1) {
				for(int i=0;i<2;i++) {
					int ny = y+dir1[i][0];
					int nx = x+dir1[i][1];
					if(ny<0||ny>=N||nx<0||nx>=N)
						continue;
					if(map[ny][nx]==1)
						continue;
					if(i==1&&(map[ny-1][nx]==1||map[ny][nx-1]==1))
						continue;
					dp[y][x][shape] +=dfs(ny,nx,dir1[i][2]);
				}
			}
			else {
				for(int i=0;i<3;i++) {
					int ny = y+dir2[i][0];
					int nx = x+dir2[i][1];
					if(ny<0||ny>=N||nx<0||nx>=N)
						continue;
					if(map[ny][nx]==1)
						continue;

					if(i==2&&(map[ny-1][nx]==1||map[ny][nx-1]==1))
						continue;

					dp[y][x][shape] +=dfs(ny,nx,dir2[i][2]);
				}

			}
		}
		return dp[y][x][shape];
	}

}
