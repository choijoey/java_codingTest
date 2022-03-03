package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baek1238_2 {

	static class Node{
		int num;
		Node next;
		//가중치
		int val;
		public Node(int num, Node next,int val) {
			super();
			this.num = num;
			this.next = next;
			this.val = val;
		}

	}
	static class Vertex{
		int num;
		int val;
		public Vertex(int num, int val) {
			super();
			this.num = num;
			this.val = val;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st =new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		//입력 그대로 받는 adjList
		Node[] adjList= new Node[N+1];

		//입력받는  방향 반대로 받는 adjList
		Node[] adjList2= new Node[N+1];

		for(int i=0;i<M;i++) {
			st =new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to,adjList[from],val);
			adjList2[to] = new Node(from,adjList2[to],val);
		}

		int ans=0;

		//X에서 출발하는 다익스트라 

		int start =X;
		int[] distance = new int[N+1];
		boolean[] v = new boolean[N+1];
		Arrays.fill(distance,Integer.MAX_VALUE);

		PriorityQueue<Vertex> pq =new PriorityQueue<>((o1,o2)-> o1.val-o2.val);
		pq.offer(new Vertex(start,0));

		distance[start]=0;
		int cnt= 0;

		//X에서 출발하여 모든 정점 도착
		while(!pq.isEmpty()) {

			Vertex cur =pq.poll();

			if(v[cur.num])
				continue;

			v[cur.num]=true;
			cnt++;

			//모든 정점을 방문하면 종료
			if(cnt==N)
				break;

			for(Node tmp = adjList[cur.num];tmp!=null;tmp=tmp.next) {

				if(!v[tmp.num] && distance[tmp.num]>distance[cur.num]+tmp.val) {
					distance[tmp.num]=distance[cur.num]+tmp.val;
					pq.offer(new Vertex(tmp.num,distance[tmp.num]));
				}
			}
		}
		
		int[] dis = new int[N+1];
		v = new boolean[N+1];
		Arrays.fill(dis,Integer.MAX_VALUE);

		pq =new PriorityQueue<>((o1,o2)-> o1.val-o2.val);
		pq.offer(new Vertex(start,0));

		dis[start]=0;
		cnt= 0;
		//X에서 출발하여 모든 정점 도착
		while(!pq.isEmpty()) {

			Vertex cur =pq.poll();

			if(v[cur.num])
				continue;

			v[cur.num]=true;
			cnt++;
			
			//X를 방문하면 종료
			if(cnt==N)
				break;

			for(Node tmp = adjList2[cur.num];tmp!=null;tmp=tmp.next) {

				if(!v[tmp.num] && dis[tmp.num]>dis[cur.num]+tmp.val) {
					dis[tmp.num]=dis[cur.num]+tmp.val;
					pq.offer(new Vertex(tmp.num,dis[tmp.num]));
				}
			}
		}
		for(int i=1;i<=N;i++) {
			if(i==X)
				continue;
			ans=Math.max(ans, dis[i]+distance[i]);
		}

		StringBuilder sb =new StringBuilder();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
