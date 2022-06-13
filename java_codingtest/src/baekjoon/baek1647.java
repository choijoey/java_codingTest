package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//http://boj.kr/fe7c9b28a7234289973c9ff0d9d7f18f

public class baek1647 {

	static int[] par;
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int val;
		public Edge(int from, int to, int val) {
			super();
			this.from = from;
			this.to = to;
			this.val = val;
		}
		@Override
		public int compareTo(Edge o) {
			return this.val-o.val;
		}
	}
	static void makeSet(int N) {
	
		par = new int[N+1];
		
		for(int i=1;i<=N;i++)
			par[i]= i;
		
	}
	static int find(int a) {
		if(par[a]==a)
			return a;
		
		return par[a] = find(par[a]);
	}
	static boolean union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot)
			return false;
		
		par[bRoot] = aRoot;
		return true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int M = Integer.parseInt(st.nextToken()); // 길의 개수
		
		makeSet(N);
		
		//간선이 주어지므로 크루스칼 방식
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		
		for(int i=0;i < M;i++) {
			st= new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(from,to,val));
		}
		
		
		
		int ans = 0;
		int maxVal = 0;
		
		while(!pq.isEmpty()) {
			
			Edge cur = pq.poll();
			
			if(!union(cur.from,cur.to))//같은 트리라면
				continue;
			
			ans += cur.val;
			maxVal = Math.max(cur.val, maxVal);
		}
		ans-= maxVal;
		
		System.out.println(ans);
		
	}

}
