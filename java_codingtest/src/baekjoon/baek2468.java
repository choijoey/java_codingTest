package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baek2468 {

	static int ans,N;
	static int[][] map;
	static boolean[][] v;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		map = new int[N+2][N+2];//배열 크기를 늘려서 탐색 줄임 
		v = new boolean[N+2][N+2];
		int mh = 0;
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mh=Math.max(mh,map[i][j] );
			}
		}

		for(int i=0;i<=mh;i++) {//봉우리 최대값을 구해서 매번 DFS 실행
			ans = Math.max(ans, DFS(i));
		}
		
		System.out.println(ans);

	}
	static int DFS(int h) {
		int cnt=0;

		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(v[i][j])
					continue;

				if(map[i][j] > h) {
					DFS2(i,j,h);
					cnt++;
				}
			}
		}
		v = new boolean[N+2][N+2];
		return cnt;
	}
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	static void DFS2(int y,int x,int h) {
		
		v[y][x] = true;
		
		for(int i=0;i<4;i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			
			
			if(v[ny][nx])
				continue;
			if(map[ny][nx]<=h)
				continue;
			DFS2(ny,nx,h);
		}
	}
}
