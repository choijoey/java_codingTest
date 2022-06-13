package swacademey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea5650 {

	static int[][] map;
	static int[][] pair;//웜홀 위치 저장
	static int[][] dir = {{},
			{1,3,0,2},
			{2,3,1,0},
			{2,0,3,1},
			{3,2,0,1},
			{2,3,0,1}
			};
	//각 블록에 튕겼을때 공의 방향 (위에서부터 반시계 방향으로 들어온다고 가정)
	static int N;
	
	static int[] dy = {1,0,-1,0};
	static int[] dx = {0,1,0,-1};
	
	static int ans; //정답
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=  new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			
			pair = new int[2][11]; //웜홀 위치 저장
			map = new int[N][N];
			ans = 0;
			
			for(int i=0;i<2;i++) {
				Arrays.fill(pair[i], -1);
			}

			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= 6 && map[i][j] <=10) {//웜홀 일 때
						if(pair[0][map[i][j]] == -1) {//웜홀 페어가 아직 없으면
							pair[0][map[i][j]] = i*N+j;
						}else {
							pair[1][map[i][j]] = i*N+j;
						}
					}
				}
			}
			
			for(int i=0;i<N;i++) { //모든 칸에서 게임 실행하여 최댓값 뽑음
				for(int j=0;j<N;j++) {
					if(map[i][j] == 0 ) {
						for(int s =0;s<4;s++) {
							start(i,j,s);
						}
					}
						
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(ans+"").append("\n");
			bw.write(sb.toString());
			
		}
		bw.flush();
		bw.close();
	}
	
	static void start(int y , int x,int d) {
		
		int stY = y;
		int stX = x;
		int score = 0;//벽에 부딪힌 횟수 = 점수
		
		while(true) {
			

			int nY = y+dy[d];
			int nX = x+dx[d];
			
			if(nY>=N||nY<0||nX>=N||nX<0) {//범위 벗어나면
				if(d==0)
					d=2;
				else if(d==1)
					d=3;
				else if(d==2)
					d=0;
				else
					d=1;
				
				score++;
				y = nY;
				x = nX;
				continue;
			}
			
			y = nY;
			x = nX;
			if(map[y][x] == -1 || (y == stY && x == stX)) { //종료 조건
				ans = Math.max(ans, score);
				return;
			}
			
			if(map[y][x]>=1 && map[y][x]<=5) {//블록을 만났다면
				d =dir[map[y][x]][d]; //들어오는 방향과 블록 종류를 받아서 바뀌는 방향 리턴
				
				score++;
				continue;
			}
			
			if(map[y][x]>=6) {//웜홀을 만났다면
				int cur = y*N+x;
				
				if(pair[0][map[y][x]]== cur) {
					int tmp = pair[1][map[y][x]];
					
					x = tmp%N;
					y = (tmp-tmp%N)/N;
				}
				else {
					int tmp = pair[0][map[y][x]];
					x = tmp%N;
					y = (tmp-tmp%N)/N;
				}
			}
			

			
		}
	}

}
