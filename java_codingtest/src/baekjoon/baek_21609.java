package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baek_21609 {

	static int[][] map;
	static boolean[][] v;

	static int ans,N,M;

	static int tmp ; //그룹 최대 수 , 해당 그룹

	static int tmpY,tmpX;
	static int tmpRainbow;

	static int[][] dir = {{0,-1},{0,1},{-1,0},{1,0}}; 

	static class Block{
		int y;
		int x;

		public Block(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}


	static ArrayList<Block> list ;


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //격자 한변 크기
		M = Integer.parseInt(st.nextToken()); //색상 개수


		map = new int[N][N];
		ans = 0;
		list = new  ArrayList<>();


		//블록 입력
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}

		//시뮬 시작
		while(true) {

			tmp=0;
			tmpY=0;
			tmpX=0;
			tmpRainbow=0;

			//크기가 가장 큰 블록 그룹을 찾아서 제거 한 후 점수 반환
			int num = findBlock();
			ans += num; //점수 합산

			if(num == 0)
				break;

			//격자 중력
			gravity();
			//격자 90도 회전
			turn();
			//격자 중력
			gravity();


		}
		System.out.println(ans);

	}
	static int findBlock() {
		int st =0; 
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] > 0) {
					v = new boolean[N][N];
					v[i][j]=true;
					ArrayList<Block> grp = new ArrayList<>();
					grp.add(new Block(i,j));

					dfs(i,j,map[i][j],grp);

				}
			}
		}

		if(tmp ==1)
			return 0;

		//최대 그룹 지우기
		for(Block cur : list) {

			map[cur.y][cur.x] =-2;

		}

		//list 초기화
		list.clear();


		return tmp*tmp; //점수 반환
	}
	static void dfs(int y,int x,int startColor ,ArrayList<Block> grp) {


		for(int i=0;i<4;i++) {

			int ny = y + dir[i][1];
			int nx = x + dir[i][0];

			if(ny<0||nx<0||ny>=N||nx>=N)
				continue;
			if(v[ny][nx])continue;

			if(map[ny][nx] == startColor || map[ny][nx] == 0) {
				v[ny][nx]=true;

				grp.add(new Block(ny,nx));


				if(grp.size()>tmp) {
					tmp=grp.size();
					list =grp;

					int stY=N;
					int stX=N;
					int curRainbow=0;
					
					for(Block t : grp) {
						if(map[t.y][t.x] ==0) curRainbow++;
						else {
							if(t.y < stY) {
								stY = t.y;
								stX = t.x;
							}
							if(t.y == stY && t.x <stX) {
								stY = t.y;
								stX = t.x;
							}
						}
					}

					tmpY = stY;
					tmpX = stX;
					tmpRainbow= curRainbow;
				}
				else if(grp.size() == tmp) {

					int curRainbow=0;
					int stY=N;
					int stX=N;

					for(Block t : grp) {
						if(map[t.y][t.x] ==0) curRainbow++;
						else {
							if(t.y < stY) {
								stY = t.y;
								stX = t.x;
							}
							if(t.y == stY && t.x <stX) {
								stY = t.y;
								stX = t.x;
							}
						}
					}
					if(tmpRainbow <curRainbow) {
						list =grp;
						tmpRainbow= curRainbow;
						tmpY = stY;
						tmpX = stX;
					}

					if(tmpRainbow==curRainbow && tmpY<stY) {
						list =grp;
						tmpRainbow= curRainbow;
						tmpY = stY;
						tmpX = stX;
					}

					if(tmpRainbow==curRainbow && tmpY==stY && tmpX<stX) {
						list =grp;
						tmpRainbow= curRainbow;
						tmpY = stY;
						tmpX = stX;
					}
				}
				dfs(ny,nx,startColor,grp);
			}
		}

	}

	static void gravity() {
		for(int i=N-2;i>=0;i--) {
			for(int j=0;j<N;j++) {
				if(map[i][j] != -1)
					gCal(i,j);
			}
		}
	}
	
	static void gCal(int y,int x) {

		int ny = y +1; //중력

		if(ny>=N)
			return;

		if(map[ny][x] == -2) {//밑이 빈칸이면
			map[ny][x] = map[y][x];
			map[y][x] = -2; //기존 칸 초기화

			gCal(ny,x);//재귀 호출
		}
	}
	static void turn() {
		//반시계 90도 회전 
		int[][] tmp = new int[N][N];

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				// [y좌표][N-1 -(x좌표))]
				tmp[N-1-j][i] = map[i][j];
			}
		}
		map = tmp;
	}

}
