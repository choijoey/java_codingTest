package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek9466_2 {


	//DFS를 이용한 사이클 탐색
	//노드마다 엣지가 하나라 클래스를 따로 만들 필요가 없다는걸 풀고 나서 알았다...
	//http://boj.kr/933b5aa6e6cc4eda8e27897168a0d34a
	static int[] adjList;//인접리스트
	static boolean[] v;//방문체크
	static boolean[] recurStack;//DFS 탐색 경로 저장
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());



		for(int tc=1;tc<=TC;tc++) {
			int N = Integer.parseInt(br.readLine());

			v = new boolean[N];
			adjList = new int[N];
			recurStack = new boolean[N];
			ans=0;
			
			st = new StringTokenizer(br.readLine());

			for(int i=0;i<N;i++) {
				int to = Integer.parseInt(st.nextToken())-1;
				adjList[i]= to;
			}


			dfs(N);
			System.out.println(N-ans);
		}
	}

	static void dfs(int N) {


		for(int i=0;i<N;i++) {
			if(!v[i])
				dfs2(i);
		}
	}
	static void dfs2(int node) {
		
		if(recurStack[node]) {//사이클이 존재 한다면 사이클과 연결된 노드 개수세고 종료
			
			ans++;//자기자신 카운트
			
			for(int i=adjList[node];i!=node;i=adjList[i]) {
				v[i]=true;//사이클 탐색 과정에서도 방문체크 하게끔 수정
				ans++;
			}
			return;
		}
		if(v[node])
			return;
		
		v[node] = true;//해당 노드 방문
		recurStack[node] = true;//DFS 경로 추가
		
		int nextIdx = adjList[node];
		dfs2(nextIdx);
		recurStack[node] = false;//재귀 호출 후 지나온 경로 백트래킹

	}
}
