package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_19237 {

	
	
	static int[][] dir = {{},{0,-1},{0,1},{-1,0},{1,0}}; //상 하 좌 우 
	
	static Shark[][] map ;//격자
	static Shark[][] movMap ;//상어 이동한 격자
	
	static int[][][] dirMap; //상어 방향 우선순위 
	static int N,M,K; // N x N 격자 ,M 상어 개수 , K 는 냄새 유지 
	
	static int[] curShark;//현재 상어 방향 
	
	static class Shark{
		int num; // 상어 번호
		int smell; //상어 냄새
		boolean isShark;//상어 유무
		
		public Shark(int num, int smell, boolean isShark) {
			super();
			this.num = num;
			this.smell = smell;
			this.isShark = isShark;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		map = new Shark[N][N];
		movMap = new Shark[N][N];
		dirMap = new int[M+1][5][5]; //각 상어 마다 2차원 배열 할당
		curShark = new int[M+1];
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				
				
				int num = Integer.parseInt(st.nextToken()); //각 칸마다 상어 번호 입력
				
				if(num >0 )
					map[i][j] = new Shark(num, K, true);
				else
					map[i][j] = new Shark(num, 0, false);
				movMap[i][j] = new Shark(0,0,false);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1;i<=M;i++) {
			curShark[i] = Integer.parseInt(st.nextToken()); //각 상어 현재 방향 입력
		}
		
		for(int i=1;i<=M;i++) {
			for(int j=1;j<5;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=1;k<5;k++) {
					dirMap[i][j][k] = Integer.parseInt(st.nextToken()); //각 칸마다 상어 번호 입력
				}
			}
		}
		
		int cnt=0; //격자 밖으로 나가는 상어 개수
		int tc =1 ; //초 
		
		out:for(;tc<=1000;tc++) { //1000 초 동안 시뮬 
			//1번 상어만 격자에 남게 되기까지 걸리는 시간을 출력한다.
			//단, 1,000초가 넘어도 다른 상어가 격자에 남아 있으면 -1을 출력한다.
			
			//상어 이동
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!map[i][j].isShark) {
						continue;
					}
					
					int mov = calDir(i,j);
					
					curShark[map[i][j].num] = mov;
					
					int nx = j + dir[mov][0];
					int ny = i + dir[mov][1];
					
					if(movMap[ny][nx].num !=0)
						cnt++;
					
					if(cnt == M-1)
						break out;
					
					map[i][j].isShark = false;
					
					if(movMap[ny][nx].num > map[i][j].num || movMap[ny][nx].num ==0) {//현재 이동하는상어가 기존 상어보다 크다면 먹음
						movMap[ny][nx].num = map[i][j].num;
					}			
					movMap[ny][nx].smell = K;
					movMap[ny][nx].isShark = true;
				}
			}
			//냄새 이동
			copy(movMap,map);
			Shark[][] tmp =map;
			map = movMap;
			movMap = tmp;
			
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(movMap[i][j].num+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//			System.out.println();
//			System.out.println();
			
			
		}
		
		if(tc ==1001)
			System.out.println(-1);
		else
			System.out.println(tc);
		
		
	}
	
	static int calDir(int y,int x) {
		//어느 방향으로 이동할지 계산하는 함수
		int num = map[y][x].num;
		
		int curD = curShark[num];//현재 상어의 방향
		
		int[] li = dirMap[num][curD]; 
		
		for(int i=1;i<=4;i++) { //사방위 확인
			
			int nx = x+dir[li[i]][0];
			int ny = y+dir[li[i]][1];
			
			if(ny <0 ||nx<0 ||ny>=N||nx>=N)
				continue;
			// 아무 냄새 없는 칸 자리 있으면
			if(map[ny][nx].smell ==0)
				return li[i];
			
		}
		
		for(int i=1;i<=4;i++) { //사방위 확인
			
			int nx = x+dir[li[i]][0];
			int ny = y+dir[li[i]][1];
			if(ny <0 ||nx<0 ||ny>=N||nx>=N)
				continue;
			// 자기 냄새 칸 있으면
			if(map[ny][nx].num ==num)
				return li[i];
			
		}
		
		for(int i=1;i<=4;i++) { //사방위 확인
			
			int nx = x+dir[li[i]][0];
			int ny = y+dir[li[i]][1];
			if(ny <0 ||nx<0 ||ny>=N||nx>=N)
				continue;
			
			return li[i];
			
		}
		
		return -1;
	}
	static void copy(Shark[][] a,Shark[][] b) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!a[i][j].isShark && b[i][j].smell >1) { //상어가 없는 칸이면
					a[i][j].smell = b[i][j].smell-1;
					a[i][j].num = b[i][j].num;
				}

				//b 배열 초기화
				b[i][j].num =0;
				b[i][j].smell = 0;
				b[i][j].isShark =false;
			}
		}

	}
}
