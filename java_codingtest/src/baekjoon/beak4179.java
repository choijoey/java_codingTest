package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class beak4179 {

	static class Node{ //좌표 , J 인지 F인지 저장 
		int y;
		int x;
		char state;
		public Node(int y, int x, char state) {
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

		char[][] map = new char[N+2][M+2];

		//맵을 한칸씩 늘려서 주위를 벽으로 침 
		for(int i=0;i<M+2;i++) {
			map[0][i]=map[N+1][i]='#';
		}
		for(int i=0;i<N+2;i++) {
			map[i][0]=map[i][M+1]='#';
		}

		Queue<Node> queue = new LinkedList<>();

		Node c=null;
		
		for(int i=1;i<=N;i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=1;j<=M;j++) {
				map[i][j] = tmp[j-1];

				if(map[i][j]=='J') {
					c=new Node(i,j,map[i][j]);
				}
				if(map[i][j]=='F')
					queue.offer(new Node(i,j,map[i][j]));
			}
		}
		
		//시작부터 종료 상황이면 1출력하고 종료
		if(c.y==N||c.y==1||c.x==M||c.x==1) {
			System.out.println(1);
			return;
		}
		//J를 스텍에 나중에 입력
		queue.offer(c);
		map[c.y][c.x]='D';
		
		
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};

		int level=1;//시간
		boolean flag = false;

		outer:while(!queue.isEmpty()) {

			int size = queue.size();

			for(int i=0;i<size;i++) {

				Node cur = queue.poll();


				for(int j=0;j<4;j++) {
					int ny= cur.y+dy[j];
					int nx= cur.x+dx[j];

					if(map[ny][nx]=='#'||map[ny][nx]=='F')//벽이거나 불을 만나면 스킵
						continue;
					if(cur.state=='J'&&map[ny][nx]=='D')//J가 이전에 갔던곳은 스킵 (J만)
						continue;
					if(cur.state=='J'&&(ny==N||ny==1||nx==M||nx==1)) {//J 종료조건
						level++;
						flag =true;
						break outer;
					}	
					if(cur.state=='J')//J가 지나간곳은 D로 변경
						map[ny][nx] = 'D';
					else
						map[ny][nx]=cur.state;//F가 지나간 곳은 F로 변경
					queue.offer(new Node(ny,nx,cur.state));
				}
			}
			level++;
		}

		if(flag)
			System.out.println(level);
		else
			System.out.println("IMPOSSIBLE");

	}

}
