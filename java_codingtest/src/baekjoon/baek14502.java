package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek14502 {

	static int N,M;
	//전체 맵 //바이러스 좌표
	static int[][] map,virus;
	//벽이 세워지는 좌표
	static int[] wall;
	//정답 , 바이러스 개수 
	static int ans,virus_cnt;

	
	//맵 , 벽이 세워지는 좌표 , 바이러스 좌표 , 0의 개수를 이용하여 각 바이러스 위치마다 bfs
	//남아있는 0의 개수 반환
	
	static int bfs(int[][] tmpMap, int[][] wall,int[][] virus,int zero_cnt) {

		for(int i=0;i<3;i++) {
			tmpMap[wall[i][0]][wall[i][1]] = 1;
		}

		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};

		for(int i=0;i<virus_cnt;i++) {

			int[] start = virus[i];

			Queue<int[]> queue = new LinkedList<>();

			queue.offer(start);

			while(!queue.isEmpty()) {

				int[] cur =queue.poll();

				for(int j=0;j<4;j++) {
					int ny = cur[0]+dy[j];
					int nx = cur[1]+dx[j];

					if(ny>=N||ny<0||nx>=M||nx<0)
						continue;
					if(tmpMap[ny][nx]!=0)
						continue;

					queue.offer(new int[] {ny,nx});
					tmpMap[ny][nx]=2;
					zero_cnt--;

					if(zero_cnt==0)
						return 0;
				}

			}

		}

		return zero_cnt;
	}

	//0~N*M까지의 수 중  수 3개를 조합으로 구한다음
	//각 수를 좌표로 변환 시키고 bfs
	//bfs돌린 값중 최대값 ans
	
	static void combination(int cnt,int start,int zero_cnt) {

		if(cnt == 3) {
			int[][] makeWall= new int[3][2];

			for(int i=0;i<3;i++) {
				int cur = wall[i];
				int y = cur/M;
				int x = cur%M;
				makeWall[i][0]=y;
				makeWall[i][1]=x;
			}
			int[][] tmpMap=new int[N][M];

			//			System.out.println(Arrays.toString(wall));
			copyArray(map,tmpMap);

			ans =Math.max(ans, bfs(tmpMap,makeWall,virus,zero_cnt));
			return;

		}
		for(int i=start;i<N*M;i++) {

			int y = i/M;
			int x = i%M;
			if(map[y][x] != 0)
				continue;
			
			wall[cnt] = i;

			combination(cnt+1,i+1,zero_cnt);

		}
	}
	static void copyArray(int[][] a ,int[][] b){
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				b[i][j] = a[i][j];
			}
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st= new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		virus = new int[N*M][2];
		wall = new int[3];

		int zero_cnt=0;


		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					virus[virus_cnt][0]=i;
					virus[virus_cnt][1]=j;
					virus_cnt++;
				}
				if(map[i][j]==0)
					zero_cnt++;
			}
		}
		//벽 3개 세워주니까 0개수 -3 
		combination(0,0,zero_cnt-3);
		System.out.println(ans);

	}
}
