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

public class baek3055 {

	static int R,C;
	static char[][] map;

	static class Node {
		int y;
		int x;
		char state;
		Node(int y,int x, char state){
			this.y=y;
			this.x=x;
			this.state=state;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		boolean[][] v = new boolean[R][C];

		Queue<Node> queue = new LinkedList<>();

		int tmp[]=null; // 고슴도치 큐의 맨 뒤에 넣기 위해 값 따로 저장

		for(int i=0;i<R;i++) {
			String s = br.readLine();

			for(int j=0;j<C;j++) {

				map[i][j] = s.charAt(j);
				if(map[i][j] == '*') {
					queue.offer(new Node(i,j,'*'));
					v[i][j]= true;
				}
				if(map[i][j] == 'S') {
					tmp=new int[] {i,j};
				}
			}
		}


		queue.offer(new Node(tmp[0],tmp[1],'S'));
		v[tmp[0]][tmp[1]]= true;

		int level=0;//시간

		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};

		boolean flag = false;//고슴도치 종료지점 도착 여부 

		outer:while(!queue.isEmpty()) {

			int size = queue.size();

			for(int i=0;i<size;i++) {

				Node cur = queue.poll();

				for(int j=0;j<4;j++) {
					int ny = cur.y+dy[j];
					int nx = cur.x+dx[j];

					if(ny>=R||ny<0||nx>=C||nx<0)
						continue;
					if(map[ny][nx] =='X')//돌에 도달할 경우
						continue;
					
					if(v[ny][nx])//이미 지나온 경우
						continue;
					
					if(cur.state=='S' && map[ny][nx]=='D') { //굴에 도착
						flag=true;
						level++;
						break outer;
					}
					
					if(map[ny][nx]=='D') //물이 굴에 도달할 경우 스킵
						continue;

					if(cur.state=='S') {
						queue.offer(new Node(ny,nx,'S'));
						v[ny][nx]= true;
						map[ny][nx]='S';
					}
					if(cur.state=='*') {
						queue.offer(new Node(ny,nx,'*'));
						v[ny][nx]= true;
						map[ny][nx]='*';
					}
					
//					for(int a=0;a<R;a++) {
//						for(int b=0;b<C;b++) {
//							System.out.print(map[a][b]);
//						}
//						System.out.println();
//
//					}
//					System.out.println();
//					System.out.println();
				}



			}

			level++;

		}
		if(flag) {
			bw.write(level+"");
		}
		else {
			bw.write("KAKTUS");
		}

		bw.flush();
		bw.close();


	}

}
