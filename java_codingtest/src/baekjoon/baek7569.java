package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek7569 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());//가로
		int N = Integer.parseInt(st.nextToken());//세로
		int H = Integer.parseInt(st.nextToken());//높이

		int[][][] map = new int[N][M][H];
		boolean[][][] v = new boolean[N][M][H];

		int[] dy = {1,-1,0,0,0,0};
		int[] dx = {0,0,1,-1,0,0};
		int[] dh = {0,0,0,0,1,-1};

		Queue<int[]> queue = new LinkedList<>();

		int zCnt=0; // 안익은 토마토 개수

		for(int i =0;i<H;i++) {
			for(int j =0;j<N;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k =0;k<M;k++) {
					map[j][k][i] = Integer.parseInt(st.nextToken());

					if(map[j][k][i]==1)
						queue.offer(new int[] {j,k,i});//토마토가 있다면 큐에 추가 
					if(map[j][k][i]==0)
						zCnt++;
				}
			}
		}

		int level =0;
		if(zCnt!=0) {
			outer: while(!queue.isEmpty()) {

				int size = queue.size();

				for(int i=0;i<size;i++) {
					int[] cur = queue.poll();

					for(int j=0;j<6;j++) {
						int ny = cur[0] +dy[j];
						int nx = cur[1] +dx[j];
						int nh = cur[2] +dh[j];

						if(ny<0||ny>=N||nx<0||nx>=M||nh<0||nh>=H)
							continue;
						if(v[ny][nx][nh])
							continue;
						if(map[ny][nx][nh]!=0)
							continue;

						queue.offer(new int[] {ny,nx,nh});
						v[ny][nx][nh]=true;
						zCnt--;
						if(zCnt==0) {//토마토가 모두 익었다면 bfs종료
							level++;
							break outer;
						}
					}
				}
				level++;		
			}
		}


		if(zCnt>0)
			bw.write(-1+"");
		else
			bw.write(level+"");

		bw.flush();
		bw.close();


		//		for(int i =0;i<H;i++) {
		//			for(int j =0;j<N;j++) {
		//				for(int k =0;k<M;k++) {
		//					System.out.print(map[j][k][i]+" ");
		//				}
		//				System.out.println();
		//			}
		//			
		//			System.out.println();
		//			System.out.println();
		//			System.out.println();
		//		}

	}

}
