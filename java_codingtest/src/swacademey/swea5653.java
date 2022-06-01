package swacademey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class swea5653 {

	static class Node{
		int y;
		int x;
		int time;//해당 세포 생명력 수치
		int left;//남은 생명력
		boolean born;
		public Node(int y, int x, int time,boolean born) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
			this.left = time*2;
			this.born = born;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=  new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int tc = 1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());//세로
			int M = Integer.parseInt(st.nextToken());//가로
			int K = Integer.parseInt(st.nextToken());//시간

			Node[][] map = new Node[351][351];

			Queue<Node> queue = new LinkedList<>();

			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					int tmp  = Integer.parseInt(st.nextToken());
					if(tmp != 0) {
						map[i+150][j+150] = new Node(i+150,j+150,tmp,false);
						queue.offer(map[i+150][j+150]);//줄기세포 큐에 추가
					}
				}
			}

			int[] dy = {1,-1,0,0};
			int[] dx = {0,0,-1,1};

			int start =0;
			int ans =0;

			while(start++ < K) {
				while(!queue.isEmpty()) {
					Node cur = queue.poll();

					
					if(cur.left-- == cur.time) {//첫 한시간 동안 세포가 번식
						int y = cur.y;
						int x = cur.x;

						for(int j=0;j<4;j++) {
							int ny= y+dy[j];
							int nx = x+dx[j];

							if(map[ny][nx]==null) {//빈 칸이면
								map[ny][nx] = new Node(ny,nx,cur.time,true);
							}
							else {
								if(!map[ny][nx].born)//이번 시간에 태어난 세포가 아니면 스킵
									continue;
								if(map[ny][nx].time < cur.time) //현재 세포의 크기가 더 크다면
									map[ny][nx] = new Node(ny,nx,cur.time,true);
							}
						}
					}
				}
				

				//죽지 않은 세포들 큐에 추가
				int cnt=0;
				for(int i=0;i<351;i++) {
					for(int j=0;j<351;j++) {
						if(map[i][j]==null) continue;

						map[i][j].born=false;//태어난거 끝 

						if(map[i][j].left>0) {
							queue.offer(map[i][j]);
							cnt++;
						}
					}
				}
				ans = cnt;
			}
			System.out.println("#"+tc+" "+ans);
		}
	}

}
