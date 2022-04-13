package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek17143 {

	static class shark implements Comparable<shark>{
		public int r,c,s,d,z;
		public boolean isDead;
		


		public shark(int r, int c, int s, int d, int z, boolean isDead) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.isDead = isDead;
		}



		@Override
		public int compareTo(shark o) {
			return (this.z-o.z); // 상어의 크기로 내림차순 정렬
		}
	}
	static shark[][] map,moved;
	static shark[] sharks;
	static int R,C,M;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		System.out.println(-3%10);
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new shark[R+1][C+1];
		moved = new shark[R+1][C+1];
		
		sharks=  new shark[M];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks[i]= new shark(r,c,s,d,z,false);
			map[r][c] = sharks[i];
		}
		
		int ans =0; //정답
		
		//상어이동
		Arrays.sort(sharks);// 상어를 내림차순 정렬 함으로써 작은상어 먼저 이동
		
		//낚시왕 오른쪽 한칸 이동
		for(int idx=1;idx<=C;idx++) { //idx 낚시왕의 x 좌표
			
			//낚시
			shark cur = fishing(idx);
			if(cur != null) {
				cur.isDead=true;//잡은 상어 죽음
				map[cur.r][cur.c] = null;
				ans+=cur.z;
			}
			for(int i=0;i<M;i++) {
				if(sharks[i].isDead) //상어가 죽었다면 스킵
					continue;
				move(sharks[i]);//상어 이동		
			}
			shark[][] tmp = map;
			map = moved;
			moved = tmp;

		}
		System.out.println(ans);
		
		

	}

	static int[][] dir = {{},{-1,0},{1,0},{0,1},{0,-1}}; // 위 아래 오른쪽 왼쪽
	
	static void move(shark tmp) {
		
		map[tmp.r][tmp.c] = null; //기존 상어 위치 초기화
		

		int cnt = tmp.s; //속력만큼 이동
		
		if(tmp.d==1 || tmp.d ==2) {
			cnt = cnt % ((R-1)*2);
		}
		else
			cnt = cnt % ((C-1)*2);
		
		int ny=tmp.r,nx=tmp.c;
		
		while(cnt>0) {
			ny = tmp.r+dir[tmp.d][0];
			nx = tmp.c+dir[tmp.d][1];
			
			if(ny>R||ny<1||nx>C||nx<1) {//맵 끝에 도달하면 방향 전환
				
				if(tmp.d %2==0)
					tmp.d-=1;
				else
					tmp.d+=1;
				continue;
			}
			tmp.r=ny;
			tmp.c=nx;
			
			cnt--;
		}
		if(moved[tmp.r][tmp.c]  != null) { //이동 후 배열에서 기존 좌표에 상어있으면 상어 먹음
			moved[tmp.r][tmp.c].isDead=true;
			moved[tmp.r][tmp.c] = tmp;
		}
		else
			moved[tmp.r][tmp.c] = tmp; //바뀐 위치 값 넣기
	}
	
	static shark fishing(int c) {
		
		int tmp =1;
		
		while(tmp<R+1&&map[tmp][c] == null) tmp++;
		
		if(tmp == R+1)
			return null;
		
		return map[tmp][c];
	}

}
