package This_is_CodingTest_EX.chap05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ex4 {

	
	
	static int[][] map;
	static boolean[][] v;
	static int N ;
	static int M ;
	
	//상하좌우
	static int[] dy = {0,0,-1,1};
	static int[] dx = {-1,1,0,0};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		
		
		map = new int[N+1][M+1];
		v = new boolean[N+1][M+1];

		for(int i=1;i<=N;i++) {
			String s = sc.next();
			for(int j=1;j<=M;j++) {
				map[i][j] = (int)(s.charAt(j-1)-'0');
			}
		}
		
		
		System.out.println(bfs());
		
	}
	
	static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {1,1});
		v[1][1] = true;

		while(!queue.isEmpty()) {
			
			int[] tmp = queue.poll();
	
			for(int i=0;i<4;i++) {
				
				int ny = tmp[0]+dy[i];
				int nx = tmp[1]+dx[i];
				
				if(ny<1||ny>N||nx<1||nx>M)
					continue;
				
				if(map[ny][nx] ==0||v[ny][nx])
					continue;

				queue.offer(new int[] {ny,nx});
				v[ny][nx]=true;
				map[ny][nx]=map[tmp[0]][tmp[1]]+1; 
			}
			
			
		}
		
		return map[N][M];
	}

}
