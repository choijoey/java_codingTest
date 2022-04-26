package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek1967_2 {

	static class Node {
		
		int num;//노드 번호
		int val;//가중치 값
		Node next;
		public Node(int num, int val, Node next) {
			super();
			this.num = num;
			this.val = val;
			this.next = next;
		}

	}
	static int ansNode,max;
	static boolean[] v;
	static Node[] tree;
	
	//재귀를 이용한 풀이
	//첫번째 dfs에서 루트에서 가장 먼 노드를 찾음
	//두번째 dfs에서 찾은 노드를 기준으로 가장 먼 노드를 찾음=> 답
	
	//문제에서 주어진 그림이 힌트였던 문제
	//http://boj.kr/077ad1a721724dcda9828428b4b66d12
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		int N = Integer.parseInt(br.readLine());
		
		tree = new Node[N+1];

		
		for(int i=0;i<N-1;i++) {
			st= new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			tree[from]= new Node(to,val,tree[from]);
			tree[to]= new Node(from,val,tree[to]);
		}
		
		v=new boolean[N+1];
		v[1]=true;
		dfs(1,0);
		
		v=new boolean[N+1];
		v[ansNode]=true;
		dfs(ansNode,0);
		
		System.out.println(max);
	}
	static void dfs(int node,int sum) {

		for(Node cur=tree[node];cur!=null;cur=cur.next) {
			if(v[cur.num])
				continue;
			v[cur.num]= true;
			dfs(cur.num,sum+cur.val);
		}

		if(max<sum) {
			max=sum;
			ansNode=node;
		}
		
	}

}
