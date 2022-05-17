package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//매 쿼리마다 부모 방향으로 거슬러 올라가기 위한 최악의 경우 => O(N)
//총 O(NM)

//http://boj.kr/bcff30f188754ea0b94583ffdf6c729e

//부모 노드를 찾는 과정을 2의 지수형태로 건너뛰면서 찾게 되면 O(MlogN)으로 줄일 수 있지만
//이해 안되서 패쓰...
public class baek11437 {

	static class Node{
		int num;
		Node next;
		public Node(int num, Node next) {
			super();
			this.num = num;
			this.next = next;
		}
		
	}
	static boolean[] v;
	static int[] depth;
	static Node[] tree;
	static int[] par;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=  new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		tree  = new Node[N+1];
		par = new int[N+1];//부모배열
		v = new boolean[N+1];//방문체크
		depth = new int[N+1];//노드 깊이 저장
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			tree[from] = new Node(to,tree[from]);
			tree[to] = new Node(from,tree[to]);
		}
		
		//각 노드의 깊이를 만든다
		dfs(1,0);
		
		int M =  Integer.parseInt(br.readLine()); //공통 조상 찾는 쌍의 개수
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(depth[to]>depth[from]) { //from의 깊이가 항상 크게 만든다
				int tmp= from;
				from = to;
				to = tmp;
			}
			

			while(depth[to]!=depth[from]) {
				from = par[from];
			}
			
			while(to != from) {
				to = par[to];
				from = par[from];
			}
			bw.write(to+"\n");
		}
		bw.flush();
		bw.close();
	}

	
	static void dfs(int node,int num) {
		
		v[node] = true;
		depth[node]= num;
		
		for(Node tmp = tree[node]; tmp != null;tmp=tmp.next) {
			if(v[tmp.num])
				continue;
			par[tmp.num]=node;//부모노드 설정 
			dfs(tmp.num,num+1);
		}
	}
	
}
