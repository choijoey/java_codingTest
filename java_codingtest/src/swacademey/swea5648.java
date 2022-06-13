package swacademey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea5648 {

	static class Atom{
		int y;
		int x;
		int val;
		int dir;
		public Atom(int y, int x, int val, int dir){
			super();
			this.y = y;
			this.x = x;
			this.val = val;
			this.dir = dir;
		}	


	}

	static int[][] map;
	
	static int N;
	static ArrayList<Atom> list= new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int tc = 1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());

			list= new ArrayList<>();
			
			map= new int[4001][4001];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken())*2+2000;
				int y = Integer.parseInt(st.nextToken())*2+2000;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());


				list.add(new Atom(y,x,k,d));
			}

			int[] dy = {1,-1,0,0};
			int[] dx = {0,0,-1,1};

			int ans =0;

			
			while(N>0) {



				//원자 이동
				for(int i=0;i<N;i++) {
					Atom cur = list.get(i);

					
					map[cur.y][cur.x] =0;
					
					int ny = cur.y+ dy[cur.dir];
					int nx = cur.x + dx[cur.dir];

					//범위 벗어나는 원자는 제외
					if(ny >4000 || ny<0||nx>4000||nx<0) {
						list.remove(i);
						N--;
						i--;
						continue;
					}
					

					map[ny][nx] +=cur.val;

					cur.y =ny;
					cur.x= nx;
				}
				
				
				for(int i=0;i<N;i++) {
					Atom tmp = list.get(i);
					
					if(map[tmp.y][tmp.x]!= tmp.val) {
						ans+=map[tmp.y][tmp.x];
						map[tmp.y][tmp.x]=0;
						list.remove(i);
						i--;
						N--;
					}
				}
				

			}
			System.out.println("#"+tc+" "+ans);
			

		}
	}

}
