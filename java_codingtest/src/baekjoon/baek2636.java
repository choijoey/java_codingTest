package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek2636 {

	static int N;
	static int M;
	
	static int bfs(int[][] map,boolean[][] v) {
		
		int cnt=0;//치즈 개수
		
		Queue<int[]> queue=new LinkedList<>();
		
		queue.offer(new int[] {0,0});
		v[0][0] = true;
		
		int[] dx  = {0,0,1,-1};
		int[] dy  = {1,-1,0,0};
		
		
		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			
			for(int i=0;i<4;i++) {
				int ny = cur[0]+dy[i];
				int nx = cur[1]+dx[i];
				
				if(ny<0||ny>=N||nx<0||nx>=M)
					continue;
				if(v[ny][nx])
					continue;
				if(map[ny][nx]==1) {
					v[ny][nx] =true;
					map[ny][nx]=0; // 치즈 가장자리
					cnt++;
					continue;
				}
				
				v[ny][nx] =true;
				queue.offer(new int[] {ny,nx});
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time=0;
		
		int curCnt=0;
		int lastCnt=0;
		
		while(true) {
			lastCnt=curCnt;
			curCnt =bfs(map,new boolean[N][M]) ;
			if(curCnt==0)
				break;
			time++;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(time).append("\n").append(lastCnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
		
	}

}
