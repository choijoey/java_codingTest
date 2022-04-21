package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


//http://boj.kr/d87b87785cde4017b5735de4ab7c8aba
//파이어볼을 움직일때와 움직인 파이어볼을 연산하는 과정에서 맵을 모두 탐색하는 과정을 거침

public class baek20056 {

	static class Ball{
		int m,d,s;//질량 방향 속력

		public Ball(int m, int d, int s) {
			super();
			this.m = m;
			this.d = d;
			this.s = s;
		}

	}
	static class Node{
		Stack<Ball> stack;

		public Node() {
			super();
			this.stack = new Stack<>();
		}

	}
	static Node[][] map;
	static Node[][] moved;
	static int ans;

	static int N,M;//N*N배열  파이어볼 개수
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;


		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map=new Node[N][N];
		moved= new Node[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = new Node();
				moved[i][j] = new Node();
			}
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());

			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[y][x].stack.push(new Ball(m,d,s));
		}


		while(K-->0) {//K번  이동

			//1번 실행
			move();

			//2번 실행
			ans=set();
			
			//맵 스왑
			Node[][] tmp = map;
			map = moved;
			moved = tmp;
		}
		System.out.println(ans);
	}
	static int[][] dir= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};

	static int positiveMod(int n,int mod) {
		return (n%mod+mod)%mod;
	}
	static Queue<Ball> q;

	static void move() {


		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j].stack.isEmpty()) continue;

				while(!map[i][j].stack.isEmpty()) {
					Ball cur = map[i][j].stack.pop();

					int ny = positiveMod(i+dir[cur.d][0]*cur.s,N);
					int nx = positiveMod(j+dir[cur.d][1]*cur.s,N);

					moved[ny][nx].stack.push(cur);
				}
			}
		}

	}

	static int set() {
		int cnt=0;


		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {

				int newM=0,newS=0;//분리되는 질량, 속력
				int size=1;

				if(moved[i][j].stack.size()>1) {
					boolean flag = true;

					int newD =moved[i][j].stack.peek().d%2;//홀수인지 짝수인지
					size = moved[i][j].stack.size();

					while(!moved[i][j].stack.isEmpty()) {
						Ball cur = moved[i][j].stack.pop();
						newM+=cur.m;
						newS+=cur.s;
						int tmp = cur.d%2;

						if(tmp!=newD)
							flag = false;//방향이 같지 않음
					}

					int[] a = {0,2,4,6};//방향 모두 같음
					newM/=5;
					newS/=size;

					if(newM>0) { //파이어볼이 0보다 커야됨
						for(int k=0;k<4;k++) {
							if(flag) {
								moved[i][j].stack.add(new Ball(newM,a[k],newS));
							}
							else {
								moved[i][j].stack.add(new Ball(newM,a[k]+1,newS));
							}
						}
					}
					cnt+=newM*4;

				}
				else if(moved[i][j].stack.size()==1){
					cnt+=moved[i][j].stack.peek().m;
				}


			}
		}

		return cnt;
	}
}
