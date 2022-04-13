package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baek1261_2 {
	static int[][] map;
	static int N,M;
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				map[i][j] =(int)(tmp[j]-'0');
			}
		}
		int ans =dijkstra(0,0);
		
		System.out.println(ans);
	}
	
	static int dijkstra(int y,int x) {
		int[][] minTime = new int[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				minTime[i][j] =Integer.MAX_VALUE;
			}
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->(o1[2]-o2[2]));//해당지점 도달하는데까지 걸리는 비용으로 min 정렬 
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};
		minTime[y][x] =0;
		pq.offer(new int[] {y,x,0});
		
		while(!pq.isEmpty()) {
			
			int[] cur = pq.poll();
			
			if(cur[0]==N-1 &&cur[1]==M-1) //도착지점 도달 시 종료
				return minTime[cur[0]][cur[1]];
			
			//어차피 최솟값들이 먼저 뽑히기 떄문에 방문체크 안함
			
			for(int i=0;i<4;i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				
				if(ny<0||ny>=N||nx<0||nx>=M)
					continue;

				if(map[ny][nx]==1&&minTime[ny][nx]>cur[2]+1) { //다음 지점이 벽이고 현재 상태+1 보다 크다면 갱신
					minTime[ny][nx]=cur[2]+1;
					pq.offer(new int[] {ny,nx,minTime[ny][nx]});
				}
				else if(map[ny][nx]==0&&minTime[ny][nx]>cur[2]+minTime[cur[0]][cur[1]]) {  //다음 지점이 벽이 아니고 현재 상태 보다 크다면 갱신
					minTime[ny][nx]=cur[2];
					pq.offer(new int[] {ny,nx,minTime[ny][nx]});
				}
			}
				
		}
		return 0;
	}
	
}
