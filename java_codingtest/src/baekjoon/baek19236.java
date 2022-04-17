package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//이거도 그렇고 위상정렬도 그렇고 문제 자체는 안어려웠는데 
//처음 접하는 유형의 느낌이라 체감상 어려웠던것 같아요
//http://boj.kr/d49072df77f34d54b12d0cea555353b1


public class baek19236 {

	static class Fish{
		int y,x,dir;
		boolean isDead;//물고기 죽었는지 확인
		
		public Fish(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.isDead = false;
		}
	}
	static int[][] dir = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};//위부터 반시계방향
	static int[][] map; //물고기들의 방향 저장
	static Fish[] fishes;
	static int ans;
	static int curX,curY;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[4][4];
		fishes=new Fish[17];//물고기 1번~16번
		
		for(int i=0;i<4;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				fishes[num] = new Fish(i,j,dir-1);
				map[i][j] = num;
			}
		}
		
		//시뮬 시작

		fishes[map[0][0]].isDead=true;
		move();
		dfs(0,0,fishes[map[0][0]].dir,map[0][0]);
		System.out.println(ans);
		
	}
	static void dfs(int y,int x, int d,int cnt) {
		
		boolean flag= false; //경로 상에 물고기가 있는지 확인
		
		
		
		int ny = y;
		int nx = x;
		int[] dirSave= new int[17];
		
		while(true) {
			
			ny +=dir[d][0];
			nx +=dir[d][1];
			
			if(ny>=4||ny<0||nx>=4||nx<0)
				break;
			
			if(!fishes[map[ny][nx]].isDead) {
				
				flag =true;
				fishes[map[ny][nx]].isDead=true;//물고기 죽음
				
				//상어 위치 
				curY = ny;
				curX = nx;
				
				//백트래킹을 하기 위해 기존 물고기들의 방향을 따로 저장함
				//이게 이 문제 핵심같음 
				for(int i=1;i<=16;i++) {
					dirSave[i] = fishes[i].dir;
				}
				
				//물고기 이동
				move();
				
				//재귀 실행
				dfs(ny,nx,fishes[map[ny][nx]].dir,cnt+map[ny][nx]);
				
				//물고기 이동 취소 => 백트래킹
				moveBack(dirSave);
				fishes[map[ny][nx]].isDead=false;
				
			}
		}
		
		
		if(!flag) {//더 이상 갈데가 없다면 종료
			
			ans = Math.max(ans, cnt);//먹은 물고기 최댓값
			return;
		}
	}
	
	static void moveBack(int[] dirSave) {
		for(int i=16;i>=1;i--) {
			Fish cur = fishes[i];
			
			if(fishes[i].isDead) //죽은 물고기 안움직임 
				continue;
			
				int ny = cur.y-dir[cur.dir][0];//현재 방향에서 한칸 뒤로
				int nx = cur.x-dir[cur.dir][1];
				
				cur.dir=dirSave[i];//기존에 저장했던 방향배열에서 움직이기 전 방향을 받아옴
				
				//스왑
				Fish temp = fishes[map[ny][nx]];
				int idx = map[ny][nx];
				
				map[ny][nx] = i;
				map[cur.y][cur.x]= idx;
				temp.y = cur.y;
				temp.x = cur.x;
				cur.y = ny;
				cur.x = nx;
		}
	}
	static void move() {

		for(int i=1;i<=16;i++) {
			Fish cur = fishes[i];
			
			if(fishes[i].isDead) //죽은 물고기 안움직임 
				continue;
			
			for(int j=0;j<8;j++) {//조건 만족하지 않으면 반시계 방향으로 회전 //더 이상 회전 못하면 안움직임
				
				if(j>0)
					cur.dir= (cur.dir+1)%8; //반시계 회전

				int ny = cur.y+dir[cur.dir][0];
				int nx = cur.x+dir[cur.dir][1];
				if(ny>=4||ny<0||nx>=4||nx<0)
					continue;

				if(ny==curY&&nx==curX) //상어 위치와 겹치면 스킵
					continue;
				
				//스왑
				Fish temp = fishes[map[ny][nx]];
				int idx = map[ny][nx];
				
				map[ny][nx] = i;
				map[cur.y][cur.x]= idx;
				temp.y = cur.y;
				temp.x = cur.x;
				cur.y = ny;
				cur.x = nx;
				
				break;
			}

		}
	}

}
