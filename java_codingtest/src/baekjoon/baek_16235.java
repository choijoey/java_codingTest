package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class baek_16235 {

	static class Tree{

		PriorityQueue<Integer> list;

		public Tree(PriorityQueue<Integer> list) {
			super();
			this.list = list;
		}
	}

	static Tree[][] map ;

	static Stack<int[]> dead;
	static int N,M,K;
	static int[][] add,cur;
	static Stack<Integer> tmp;

	static int[][] dir = {{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1}};

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //땅 크기
		M = Integer.parseInt(st.nextToken()); 
		K = Integer.parseInt(st.nextToken()); // K년 지난 후 
		dead = new Stack<>(); //죽은 나무 위치 저장

		//2차원 배열  원소마다 ArrayList 가지는 3차원 배열
		map = new Tree[N+1][N+1];
		add = new int[N+1][N+1]; //각 칸에 추가되는 양분의 양

		cur = new int[N+1][N+1]; //현재 양분 양


		tmp = new Stack<>();

		for(int i=1;i<=N;i++) {
			st =new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = new Tree(new PriorityQueue<>());

				add[i][j] = Integer.parseInt(st.nextToken());
				cur[i][j] = 5;
			}
		}

		//나무 심기
		for(int i=0;i<M;i++) {
			st =new StringTokenizer(br.readLine());

				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());//나무 나이

				map[y][x].list.offer(z);
			
		}

		int ans = 0;

		for(int tc=1;tc<=K;tc++) {

			//봄 여름
			springAndSummer();

			//가을
			autumn();
			
			//겨울
			winter();
			
		}
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				ans+=map[i][j].list.size();
			}
		}
		System.out.println(ans);

	}
	static void winter() {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				cur[i][j]+=add[i][j];
			}
		}
	}
	static void autumn() {

		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {


				for(int t : map[i][j].list) {
					if(t%5 ==0) { //나무의 나이가 5의 배수이면 번식
						for(int k=0;k<8;k++) {
							int ny = i+dir[k][0];
							int nx = j+dir[k][1];

							if(ny<1||nx<1||ny>N||nx>N)
								continue;

							map[ny][nx].list.offer(1); //새로운 나무 번식
						}
					}
				}

			}
		}
	}
	static void springAndSummer() {

		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				//양분만큼 나이먹기
				int curFood = cur[i][j]; //현재 양분
				int deadTreeSum =0;

				int size =map[i][j].list.size();
				
				for(int k=0;k<size;k++) {
					int curTree = map[i][j].list.poll(); //해당 칸 가장 어린 나무
					if(curFood-curTree>=0) {
						curFood -= curTree;
						tmp.push(curTree); //밥먹음
					}
					else {
						deadTreeSum += (curTree)/2;
					}
				}
				cur[i][j] = curFood;
				
				//밥 먹은 나무 다시 추가
				while(!tmp.isEmpty()) {
					map[i][j].list.offer(tmp.pop()+1); //나무 나이 먹음
				}

				cur[i][j]+= deadTreeSum;//현재 칸 양분 추가
			}
		}


	}

}
