package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//http://boj.kr/809871ef55a548ac8deb39bfa61add29
//첫번째 풀이 후 다른 코드를 참고해서 수정해봤다
//파이어볼을 움직이는 과정에서 맵 탐색을 하지 않기때문에 훨씬 빠르다
//Ball 객체의 spread와 cnt 그리고 맵을 언제 초기화 시켜야 하는지가 관건인것 같다
public class baek20056_2 {

	static class Ball{
		int y,x,m,d,s;//질량 방향 속력
		int spread,cnt;// 0 안겹침 1겹침(방향 같음) 2겹침(방향 다름)// 겹쳐진 파이어볼 개수
		
		public Ball(int y, int x, int m, int d, int s) {
			super();
			this.y = y;
			this.x = x;
			this.m = m;
			this.d = d;
			this.s = s;
			this.spread = 0;
			this.cnt=1;
		}


	}
	static class Node{
		Stack<Ball> stack;

		public Node() {
			super();
			this.stack = new Stack<>();
		}

	}
	static Ball[][] map;// 이동 전 배열
	static Ball[][] moved;//이동 후 배열

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

		map=new Ball[N][N];
		moved= new Ball[N][N];
		
		q=new LinkedList<>();//파이어볼을 담을 큐
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());

			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			q.offer(new Ball(y,x,m,d,s));
		}


		while(K-->0) {//K번  이동

			//1번 실행
			move();

			//2번 실행
			ans=set();

			//map 스왑
			Ball[][] tmp = moved;
			moved=map;
			map= tmp;
		}
		System.out.println(ans);
	}
	
	static int[][] dir= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};//방향
	
	static Queue<Ball> q;//파이어볼 담을 큐 
	
	static int positiveMod(int n,int mod) {//음수도 모듈러 연산을 가능하게 해줌
		return (n%mod+mod)%mod;
	}
	
	

	static void move() {

		while(!q.isEmpty()) {

			Ball cur = q.poll();
			map[cur.y][cur.x]=null;//기존 맵 초기화
			
			cur.y = positiveMod(cur.y+dir[cur.d][0]*cur.s,N);//모듈러 연산을 통해 위치 한번에 찾음
			cur.x = positiveMod(cur.x+dir[cur.d][1]*cur.s,N);

			if(moved[cur.y][cur.x] == null) {//이동한 칸이 비어있다면 그대로 값 입력
				moved[cur.y][cur.x]= cur;
			}else {//칸이 비어있지 않다면
				merge(cur);
			}

		}
	}
	static void merge(Ball cur) {
		Ball tmp =moved[cur.y][cur.x];

		tmp.m+=cur.m;
		tmp.s+=cur.s;

		if(tmp.spread==2) { // 이미 방향이 틀렸다면 개수 증가 후 리턴
			tmp.cnt++;
			return;
		}
		
		//홀짝 전부 같은지 확인 
		if(cur.d%2 == tmp.d%2) {//방향 홀짝 같다면 
			tmp.spread = 1;
		}else {
			tmp.spread=2;
		}
		tmp.cnt++;
		
	}
	
	static int set() {//이동한 파이어볼을 맵에 저장 
		int cnt=0;

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				Ball cur = moved[i][j];
				if(cur == null) continue; //칸 비어있으면 스킵
				
				if(cur.spread ==0) {//파이어볼 1개만 있는경우
					cnt+=cur.m;
					q.offer(cur);
				}
				else if(cur.spread ==1) {//파이어볼 겹치고 방향 같은 경우(짝,홀)
					int nM = cur.m/5;
					int nS = cur.s/cur.cnt;
					
					if(nM ==0) {
						moved[i][j]=null;
						continue; //파이어볼 0 이면 소멸
					}
					
					for(int k=0;k<4;k++) {
						q.offer(new Ball(cur.y,cur.x,nM,k*2,nS));
					}
					cnt+=nM*4;
				}else {//파이어볼 겹치고 방향 다름
					int nM = cur.m/5;
					int nS = cur.s/cur.cnt;
					
					if(nM ==0) {
						moved[i][j]=null;
						continue; //파이어볼 0 이면 소멸
					}
					for(int k=0;k<4;k++) {
						q.offer(new Ball(cur.y,cur.x,nM,k*2+1,nS));
					}
					cnt+=nM*4;
				}
			}
		}

		return cnt;
	}
}
