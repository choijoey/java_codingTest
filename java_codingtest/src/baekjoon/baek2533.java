package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//모르겠어서 답봄
//찾아보니까 트리 dp라는 유형이 있음....
//http://boj.kr/08c1c062c8d74b01857c8ef10197724d
public class baek2533 {

	static class Node{
		int num;
		Node next;//부모 자식 노드 포인터
		public Node(int num, Node next) {
			super();
			this.num = num;
			this.next = next;
		}

	}
	static Node[] tree;
	static boolean[] v;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());//정수 개수
		
		dp = new int[N+1][2];
		tree = new Node[N+1];//트리 adjList
		v = new boolean[N+1];//방문체크  0 방문X 1 방문 2 얼리 아답터
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			tree[from] = new Node(to,tree[from]);
			tree[to] = new Node(from,tree[to]);
		}
		
		dfs(1);
		
		System.out.print(Math.min(dp[1][0], dp[1][1]));
		
	}
	static void dfs(int cnt) {
		v[cnt] = true;
		dp[cnt][0]= 0;//해당 cnt 노드가 얼리 어답터가 아닌 경우
		dp[cnt][1] = 1;//해당 cnt 노드가 얼리 어답터인 경우
		
		for(Node cur = tree[cnt]; cur != null; cur= cur.next) {
			if(!v[cur.num]) {
				dfs(cur.num);//연결된 노드에 dfs를 실행
				
				dp[cnt][0]+= dp[cur.num][1]; //현재 노드가 얼리 어답터가 아니면 무조건 자식들이 얼리어답터
				dp[cnt][1]+= Math.min(dp[cur.num][0], dp[cur.num][1]);
				//현재 노드가 얼리 어답터면 자식들이 얼리어답터일 수도 아닐 수 도 있다.
			}
		}
		
	}

}
