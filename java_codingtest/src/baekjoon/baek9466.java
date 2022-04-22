package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek9466 {

	static class Node{
		int num;//현재 값
		Node next;//가리키는 값

		public Node(int num,Node next) {
			super();
			this.num = num;
			this.next = next;
		}
	
	}
	//위상정렬 O(V+E)
	//위상정렬 조금 풀어보니까 문제에서 보이기 시작함..!!
	//http://boj.kr/adde4c21a3db4ab3a29f41de1ad6e39a
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		int[] indeg;
		Queue<Integer> queue;
		Node[] adjList;//인접리스트
		
		for(int tc=1;tc<=TC;tc++) {
			int N = Integer.parseInt(br.readLine());
			
			indeg=new int[N];
			queue = new LinkedList<>();
			adjList = new Node[N];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<N;i++) {
				int to = Integer.parseInt(st.nextToken())-1;
				adjList[i]= new Node(to,adjList[i]);
				indeg[to]++;
			}
			
			for(int i=0;i<N;i++) {
				if(indeg[i]==0)
					queue.offer(i);//진입차수가 0인 노드를 큐에 담는다
			}
			
			
			while(!queue.isEmpty()) {
				int num = queue.poll();
				Node cur = adjList[num];
				
				for(Node tmp = cur;tmp!=null;tmp=tmp.next) {
					indeg[tmp.num]--;
					
					if(indeg[tmp.num]==0) {
						queue.offer(tmp.num);
					}
				}
				
			}
			
			int ans =0; //정답
			
			for(int i=0;i<N;i++) {
				if(indeg[i]!=0)
					ans++;
			}
			System.out.println(N-ans);
		}
	}

}
