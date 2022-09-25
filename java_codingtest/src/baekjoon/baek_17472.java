package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baek_17472 {

	static int[][] map;
	static int N,M;
	static int[][] tree;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static boolean[][] v;
	static int cnt; //섬 개수
	static class Data implements Comparable<Data>{
		int ver;
		int dis;
		
		public Data(int ver, int dis) {
			super();
			this.ver = ver; //정점
			this.dis = dis; //간선 비용
		}

		@Override
		public int compareTo(Data o) {
			return this.dis -o.dis;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=  new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tree = new int[7][7];

		cnt = 1;

		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				tree[i][j] = 999_999_999;
			}
		}
		for(int i=0;i<N;i++) {
			st=  new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//dfs를 통해 섬 번호 지정
		dfs();

		//각 섬에서 가능한 다리(최소 길이 간선) 구하기
		chk();

		//구한 간선으로 스패닝 트리 구하기
		//프림 알고리즘 사용
		int res =0;
		
		//신장 트리 포함 여부
		boolean[] v = new boolean[cnt];
		
		PriorityQueue<Data> pq = new PriorityQueue<>();
		pq.offer(new Data(1,0)); //시작 정점
		
		Data cur = null;
		int curCnt =0;
		
		while(!pq.isEmpty()) {
			cur = pq.poll();

			if(v[cur.ver])
				continue;
			
			v[cur.ver] =true;
			res +=cur.dis;
			curCnt++; //신장트리에 포함된 정점 수
			
			if(curCnt+1 == cnt) break;
			
			for(int i=0;i < cnt;i++) {
				if(v[i]) continue;
				
				if(tree[cur.ver][i]==999_999_999) continue;
				
				pq.offer(new Data(i,tree[cur.ver][i]));
			}
		}
		
		if(curCnt+1 != cnt) //모든 정점과 연결되어 있지 않다면
			System.out.println(-1);
		else
			System.out.println(res);
	}

	static void chk() {

		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int d=0;d<4;d++) { //각 정점에서 모든방향에 대해 다리를 놓을 수 있는지 탐색
					if(map[i][j] != 0) {
						chk2(i,j,map[i][j],d,0);
					}
				}
			}
		}
	}
	static void chk2(int y,int x,int num,int d, int dis) { //y,x,섬 번호,방향,현재 까지 온 거리

		int ny = y+dy[d];
		int nx = x+dx[d];

		if(ny>=N||ny<0||nx>=M||nx<0)
			return;
		if(map[ny][nx] != 0 && map[ny][nx] != num) {//다른 섬 도착
			int to = map[ny][nx];
			
			if(dis == 1) //다리 길이가 1 이면 스킵
				return;
			
			tree[num][to] = Math.min(dis, tree[num][to]);
			return;
		}
		if(map[ny][nx]== 0) {
			chk2(ny,nx,num,d,dis+1);
		}

	}
	static void dfs() { //섬 번호 매김

		v = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!v[i][j] && map[i][j] == 1) {
					v[i][j] = true;
					dfs2(i,j,cnt++);
				}
			}
		}
	}
	static void dfs2(int y, int x , int cnt) {
		map[y][x] = cnt;

		for(int i=0;i<4;i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];

			if(ny>=N||ny<0||nx>=M||nx<0)
				continue;
			if(map[ny][nx]== 0)
				continue;
			if(v[ny][nx])
				continue;

			v[ny][nx] = true;
			dfs2(ny,nx,cnt);
		}
	}


}
