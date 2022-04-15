package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek17143_2 {

	static class shark {
		public int r,c,s,dr,dc,z;
		int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
		public boolean isDead;
		


		public shark(int r, int c, int s, int d, int z, boolean isDead) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;//속력
			this.dr = dir[d][0];//방향
			this.dc = dir[d][1];
			this.z = z;//크기
			this.isDead = isDead;
		}

		
		public void move() {
			r = positiveMod(r+dr*s,(R-1)*2);
			c = positiveMod(c+dc*s,(C-1)*2);
			
			if(r>= R-1) {
				dr=-dr;
				r = (R-1)*2-r;
			}
			if(c>= C-1) {
				dc=-dc;
				c = (C-1)*2-c;
			}
		}
		public int positiveMod(int num,int mod) {
			return (num%mod+mod)%mod;
		}

	}
	static int[][] map;
	static shark[] sharks;
	static int R,C,M;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		sharks=  new shark[M+1];
		
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			sharks[i]= new shark(r,c,s,d,z,false);
			map[r][c] = i;
		}
		
		int ans =0; //정답

		//낚시왕 오른쪽 한칸 이동
		for(int idx=0;idx<C;idx++) { //idx 낚시왕의 x 좌표
			
			//낚시
			int cur = fishing(idx);
			
			if(cur != 0) {
				shark tmp = sharks[cur];
				tmp.isDead =true;
				map[tmp.r][tmp.c] = 0;
				ans+=tmp.z;
			}

			for(int i=1;i<=M;i++) {
				if(sharks[i].isDead) //상어가 죽었다면 스킵
					continue;
				map[sharks[i].r][sharks[i].c] = 0;
				sharks[i].move();
			}
			//맵에 상어 이동 갱신 
			for(int i=1;i<=M;i++) {
				if(sharks[i].isDead) //상어가 죽었다면 스킵
					continue;
				if(map[sharks[i].r][sharks[i].c] != 0) {
					if(sharks[map[sharks[i].r][sharks[i].c]].z > sharks[i].z)
						sharks[i].isDead=true;
					else {
						sharks[map[sharks[i].r][sharks[i].c]].isDead=true;
						map[sharks[i].r][sharks[i].c]=i;
					}
				}
				else
					map[sharks[i].r][sharks[i].c] = i;
			}
		}
		System.out.println(ans);
		
		

	}

	static int fishing(int c) {
		
		int tmp =0;
		
		while(tmp<R&&map[tmp][c] == 0) tmp++;
		
		if(tmp == R)
			return 0;
		
		return map[tmp][c];
	}

}
