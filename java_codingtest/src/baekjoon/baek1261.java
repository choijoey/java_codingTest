package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek1261 {

	static int[][] map;
	static int[][] v;
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new int[N][M];
		
		for(int i=0;i<N;i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				map[i][j] =(int)(tmp[j]-'0');
				v[i][j] =Integer.MAX_VALUE;
			}
		}
		
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};
		
		Queue<int[]> queue = new LinkedList<>();
		v[0][0] = 0;
		queue.add(new int[] {0,0});

		while(!queue.isEmpty()) {
			
			int[] cur = queue.poll();
			
			for(int i=0;i<4;i++) {
				
				int ny = cur[0]+dy[i];
				int nx = cur[1]+dx[i];
				
				if(ny>=N||ny<0||nx>=M||nx<0)
					continue;
				
				if(map[ny][nx]==1 &&v[ny][nx] > v[cur[0]][cur[1]]+1 ) {//다음 칸이 벽이고 현재 상태보다 비용이 많이 든다면 값을 갱신한뒤 큐에 추가
					v[ny][nx] = v[cur[0]][cur[1]]+1;
					queue.add(new int[] {ny,nx});
				}
				else if(map[ny][nx]==0 &&v[ny][nx] > v[cur[0]][cur[1]]) {// 다음 칸이 벽이 아니고 현재 상태보다 비용이 많이 든다면 값을 갱신한뒤 큐에 추가
					v[ny][nx] = v[cur[0]][cur[1]];
					queue.add(new int[] {ny,nx});
				}
			}
		}
		
		System.out.println(v[N-1][M-1]); //모든 경우 탐색 후에 목표지점 최솟값 출력
		
	}

}
