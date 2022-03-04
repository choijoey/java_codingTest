package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간복잡도  O(V+E)
public class baek5567 {

	static class Node{
		int num;
		Node next;
		public Node(int num, Node next) {
			super();
			this.num = num;
			this.next = next;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		//간선리스트
		Node[] adjList = new Node[N+1];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from= Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to,adjList[from]);
			adjList[to] = new Node(from,adjList[to]);
		}
		
		//BFS
		Queue<Integer> queue = new LinkedList<>();
		boolean[] v = new boolean[N+1];
		
		queue.offer(1);
		v[1] = true;
		
		//초대하는 동기 수 (1은 빼야되므로 -1부터 시작)
		int ans=-1;
		
		//아는 사이 간격
		int level=0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int i=0;i<size;i++) {
				int cur = queue.poll();
				ans++;
				
				for(Node tmp = adjList[cur];tmp!=null;tmp=tmp.next) {
					if(!v[tmp.num]) {
						queue.offer(tmp.num);
						v[tmp.num] = true;
					}
				}
			}
			level++;
			//친구의친구의친구 면 종료 
			if(level==3)
				break;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
