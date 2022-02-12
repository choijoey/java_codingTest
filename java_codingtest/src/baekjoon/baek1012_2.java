package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek1012_2 {


	static int[][] map;
	static boolean[][] v;
	static int ans;

	static ArrayList<int[]> list;

	static int[] dy = {-1,1,0,0};//상하좌우
	static int[] dx = {0,0,-1,1};

	static int M;
	static int N;
	static int K;


	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st ;
		int T = Integer.parseInt(br.readLine());

		while(T>0) {
			st=new StringTokenizer(br.readLine());
			M =Integer.parseInt(st.nextToken());
			N =Integer.parseInt(st.nextToken());

			map = new int[N][M];

			v = new boolean[N][M];

			ans =0;


			//배추 개수 
			K =Integer.parseInt(st.nextToken());

			//배추 위치 저장
			list = new ArrayList<>();


			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());

				//가로
				int x = Integer.parseInt(st.nextToken());
				//세로
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
				list.add(new int[] {y,x});
			}

			bfs();

			bw.write(ans+"\n");
			bw.flush();

			T--;
		}
		bw.close();
	}


	static void bfs() {


		for(int j=0;j<list.size();j++) {
			
			int[] start = list.get(j);
			
			if(v[start[0]][start[1]])
				continue;
			
			Queue<int[]> queue = new LinkedList<>();
			queue.offer(start);
			ans++;

			while(!queue.isEmpty()) {
				int[] tmp = queue.poll();


				for(int i=0;i<4;i++) {
					int ny = tmp[0]+dy[i];
					int nx = tmp[1]+dx[i];

					if(ny>=N||ny<0||nx>=M||nx<0||v[ny][nx]||map[ny][nx]==0)
						continue;
					else {
						queue.offer(new int[] {ny,nx});
						v[ny][nx]= true;
					}
				}

			}
		}



	}
}
