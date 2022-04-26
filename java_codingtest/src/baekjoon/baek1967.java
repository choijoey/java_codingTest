package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek1967 {


//재귀 풀이 보니까 허무하네 ....
//트리 구현하고 위상정렬로 풀었다
//http://boj.kr/c5f17d4051b14050a39ad38053a49427
	
	static class Node {
		int pval;//부모 Edge 가중치
		int cval;//자식 경로 가중치
		int val;//해당 노드에서의 가장 큰 경로 값
		int val2;//해당 노드에서 두번째로 큰 경로 값 
		int indeg;//진입차수
		
		ArrayList<Node> child; //자식 주소
		Node par;// 부모 주소
		public Node(ArrayList<Node> child) {
			super();
			this.pval = 0;
			this.cval = 0;
			this.val = 0;
			this.val2 = 0;
			this.indeg=0;
			this.child = child;
		}
	}
	static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		int N = Integer.parseInt(br.readLine());
		
		Node[] tree = new Node[N+1]; //각 노드의 부모로의 tree edge 가중치

		
		for(int i=1;i<=N;i++) {
			tree[i]=new Node(new ArrayList<Node>());
		}
		
		for(int i=0;i<N-1;i++) {
			st= new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			tree[to].par=tree[from];//부모 설정
			tree[from].child.add(tree[to]);//자식 설정
			tree[to].pval = val;//가중치 설정
			tree[from].indeg++;//진입차수 +1
		}
		
		Queue<Node> queue = new LinkedList<>();
		
		for(int i=1;i<=N;i++) {
			if(tree[i].child.isEmpty()) { //리프 노드 큐에 추가
				queue.offer(tree[i]);
			}
		}
		while(!queue.isEmpty()) {
			Node cur =queue.poll();
			if(cur.par==null)
				break;
			cur.par.indeg--;
			
			int tmp = cur.pval+cur.cval;//현재 노드에서 경로값 + 부모로의 가중치를 부모노드의 경로값과 비교
			if(tmp>cur.par.val) {//부모노드의 제일 큰 경로값보다 클 경우
				cur.par.val2=cur.par.val;
				cur.par.val=tmp;
			}else if(tmp>cur.par.val2) {//부모노드의 두번째로 큰 경로값보다 클 경우
				cur.par.val2=tmp;
			}
			cur.par.cval= Math.max(cur.par.cval, cur.pval+cur.cval);//부모의 경로값은 (현재 노드에서의 경로값 + 부모노드로의 가중치) 중 최대

			max=Math.max(max, cur.par.val+cur.par.val2);//현재 부모노드의 값이 정답이 될 수있음
			
			if(cur.par.indeg==0)//차수 -1 
				queue.offer(cur.par);
		}
		
		System.out.println(max);
	}

}
