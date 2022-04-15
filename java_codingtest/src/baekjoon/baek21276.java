package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


//위상정렬 시간복잡도 O(V+E)
//http://boj.kr/371e8ad567f64298a3e947661b098a25
public class baek21276 {

	static class Node{
		int idx;
		Node next;
		public Node(int idx, Node next) {
			super();
			this.idx = idx;
			this.next = next;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb;
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());//사람 수
		
		HashMap<String,Integer> map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		
		String[] names = new String[N];
		
		for(int i=0;i<N;i++){
			names[i] = st.nextToken();
		}
		Arrays.sort(names);//해쉬 맵 키값으로 이름 순 정렬
		
		for(int i=0;i<N;i++){
			map.put(names[i] , i);
		}
		
		int M = Integer.parseInt(br.readLine()); // 관계 수
		
		Node[] adjList = new Node[N];//자식 위상정렬
		int[] inDegree = new int[N];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();
			
			int childIdx = map.get(child);
			int parentIdx = map.get(parent);
			
			//부모->자식 으로 가는 간선 생성
			
			adjList[parentIdx]= new Node(childIdx,adjList[parentIdx]);
			
			inDegree[childIdx]++;
		}
		

		int ans =0;
		sb= new StringBuilder();
		
		StringBuilder sb1= new StringBuilder();
		
		for(int i=0;i<N;i++) { //진입 차수 0이면   부모가 없으므로 조상
			if(inDegree[i] == 0) {
				sb1.append(names[i]).append(" ");
				ans++;
			}
		}
		
		
		sb.append(ans).append("\n");
		bw.write(sb.toString());
		bw.write(sb1.toString().trim());
		bw.write("\n");
		
		
		
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=0;i<N;i++) {
			if(inDegree[i]==0)
				queue.add(i);
		}
		

		int[][] find = new int[N][];
		
		while(!queue.isEmpty()) {
			
			int cur = queue.poll();
			
			int cnt =0; //자식 명 수
			
			ArrayList<Integer> childs = new ArrayList<>();
			
			Node ch = adjList[cur];
			for(Node tmp = ch; tmp!=null;tmp=tmp.next) {
				
				if(inDegree[tmp.idx]==1) {//바로 아래 자식들만 childs에 저장 (부모는 한명만 있어서 가능)
					childs.add(tmp.idx);
					cnt++;
				}
				inDegree[tmp.idx]--;
				
				if(inDegree[tmp.idx]==0)
					queue.add(tmp.idx);
			}
		
			Collections.sort(childs); //idx로 정렬 = > 사전순 정렬
			
			find[cur] = new int[childs.size()+1]; // 0인덱스에는 자식 개수 저장
			int i=0;
			find[cur][i++]=cnt;
			
			for(int num:childs) {
				find[cur][i++]=num;
			}
		}
		
		
		for(int i=0;i<N;i++) {
			int childCnt= find[i].length;
			sb= new StringBuilder();
			sb.append(names[i]).append(" "); //현재 사람
			sb.append(find[i][0]).append(" ");//자식 수
			
			for(int j=1;j<childCnt;j++) {
				sb.append(names[find[i][j]]).append(" ");
			}
			bw.write(sb.toString().trim());
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		
		
		
	}

}
