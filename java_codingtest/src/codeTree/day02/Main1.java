package codeTree.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1 {

	static class Node implements Comparable<Node>{
		int y;
		int x;
		

		public Node(int y, int x) {
			super();
			this.y = Math.abs(y);
			this.x = Math.abs(x);
		}


		@Override
		public int compareTo(Node o) {
			int y1 = this.y;
			int x1 = this.x;
			int y2 = o.y;
			int x2 = o.x;
			
			if(y1+x1== y2+x2) {
				if(y1 !=y2)
					return y1-y2;
				else 
					return x1-x2;
			} 
			
			return (y1+x1)-(y2+x2);

		}


		@Override
		public String toString() {
			return y + " " + x;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][2];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i=0;i<N;i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[i][0] = a;
			map[i][1] = b;
			pq.offer(new Node(a,b));
			
		}
		
		for(int i=0;i<M;i++) {
			Node cur = pq.poll();
			
			cur.y+=2;
			cur.x+=2;
//			System.out.println(cur);
			pq.offer(cur);
		}
		System.out.println(pq.poll());
	}

}
