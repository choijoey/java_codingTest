package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek2206 {

	
	static class Node{
		int y;
		int x;
		int state;//벽 부순 후 1  벽 부수기 전  0
		
		public Node(int y, int x, int state) {
			super();
			this.y = y;
			this.x = x;
			this.state = state;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		boolean[][][] v = new boolean[N][M][2]; // 벽이 한번 부술 수 있는지 확인 
		
		for(int i=0;i<N;i++) {
			String s=  br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		Queue <Node> queue = new LinkedList<>();
		
		queue.offer(new Node(0,0,0));
		
		int level=0;
		boolean flag =false; //제대로 끝났는지 확인
		
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};
		
		
		outer:while(!queue.isEmpty()) {
			
			int size = queue.size();
			
			for(int i=0;i<size;i++) {
				Node cur = queue.poll();
				
				for(int j=0;j<4;j++) {
					int ny = cur.y+dy[j];
					int nx = cur.x+dx[j];
					
					if(ny>=N||ny<0||nx>=M||nx<0)
						continue;
					
					if(ny==N-1&&nx==M-1) {
						flag =true;
						level++;
						break outer;
					}
					
					if(v[ny][nx][cur.state])//현재  상태에서 지나간 자리면 스킵
						continue;
					
					if(map[ny][nx]==1 &&cur.state==0) {//벽 한번 부술 수 있으면 큐에 추가
						queue.offer(new Node(ny,nx,1));
						v[ny][nx][1]=true;
					}
					
					if(map[ny][nx]==1) // 벽 부술 수 없으면 스킵
						continue;
					
					queue.offer(new Node(ny,nx,cur.state));
					v[ny][nx][cur.state]=true;
				}
			}
			level++;
		}
		
		if(N==1&&M==1) {//N과 M이 1일 경우 조건 추가
			flag = true;
			level=0;
		}
		if(flag)
			bw.write((level+1)+""); //시작 칸 포함해서 세기 때문에 +1
		else
			bw.write("-1");
		
		
		bw.flush();
		bw.close();
		
	}

}
