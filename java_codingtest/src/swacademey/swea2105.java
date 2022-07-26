package swacademey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class swea2105 {

	static int[][] map;
	static int ans;
	static int[] dy= {1,1,-1,-1};
	static int[] dx= {1,-1,-1,1};
	static int N;


	static HashSet<Integer> set= new HashSet<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());



		for(int tc = 1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			ans=-1;
			set.clear();//이미 방문한 카페인지 알려줌


			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for(int i=0;i<N-2;i++) {
				for(int j=1;j<N-1;j++) {
					set.add(map[i][j]);
					dfs(i,j,i,j,0);
					set.remove(map[i][j]);
				}
			}

			System.out.println("#"+tc+" "+ ans);
		}
	}
	static void dfs(int y,int x,int stY,int stX,int turn) {
		//현재 y ,현재 x, 초기 y, 초기 x, 지나간 개수, 방향 , 방향 회전 개수
		
		if(turn >= 4)//회전이 4개 이상이면 종료
			return;
		
		y=y+dy[turn];
		x=x+dx[turn];

		if(set.size() >=4 && y==stY&&x==stX) {//원점으로 돌아왔을 경우

			ans=Math.max(ans, set.size());
			return;
		}


		if(y>=N||y<0||x>=N||x<0)
			return;


		if(!set.add(map[y][x]))//넣었는데 있는 값이면 스킵
			return;


		dfs(y,x,stY,stX,turn);//회전 안함

		dfs(y,x,stY,stX,turn+1);//회전

		set.remove(map[y][x]);


	}

}
