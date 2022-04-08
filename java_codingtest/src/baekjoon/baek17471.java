package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baek17471 {

	static int N;
	static Node[] nodes;
	static boolean[] v;//어떤 노드 선택할지
	static boolean[] visited;//DFS방문체크
	static int[] val;//인구수 
	static int ans;
	
	static class Node{ //노드 생성
		int num;
		int[] next;
		public Node(int num, int[] next) {
			super();
			this.num = num;
			this.next = next;
		}
	
	}

	static void DFS(int cur) { // 같은 선거구 체크
		
		visited[cur] =true;
		
		int len = nodes[cur].next.length;
		
		for(int i=0;i<len;i++ ) {
			
			int next = nodes[cur].next[i];
			if(visited[next])
				continue;
			if(v[next] != v[cur])
				continue;
			DFS(next);
		}
		
	}
	static boolean chk() { //모든 정점에 대해 DFS 실행 -> 2와 다른값이 나오면 false
		
		int cnt =0;
		visited=new boolean[N+1];
		
		for(int i=1;i<=N;i++) {
			
			if(!visited[i]) {
				DFS(i);
				cnt++;
			}
			if(cnt>2)
				return false;
		}
		
		if(cnt<2)
			return false;
		
		return true;
	}
	static void Comb(int cnt,int end, int start) {
		
		if(cnt == end) { 
			
			int cnt1=0;
			int cnt2=0;
			
			if(chk()) {
				for(int i=1;i<=N;i++) {
					if(v[i])
						cnt1+=val[i];
					else
						cnt2+=val[i];
				}
				ans = Math.min(ans, Math.abs(cnt1-cnt2));
			}
			
			return;
		}
		
		for(int i=start;i<=N;i++) {
			
			if(v[i])
				continue;
			
			v[i]=true;
			Comb(cnt+1,end,i+1);
			v[i] = false;
		}
	}

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		nodes= new Node[N+1];
		v=new boolean[N+1];
		val = new int[N+1];
		ans = 999_999_999;
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			val[i] = Integer.parseInt(st.nextToken());//인구 수
		}
		
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken());//인접한 구역 수
			
			Node tmp = new Node(i,new int[M]);
			
			for(int j=0;j<M;j++) {
				int near = Integer.parseInt(st.nextToken());
				tmp.next[j]=near;
			}
			nodes[i]=tmp;
		}
		for(int i=1;i<=N/2;i++)
			Comb(0,i,1);
		
		if(ans == 999_999_999) //고를 수 없는 경우 -1
			ans = -1;
		System.out.println(ans);
		
	}

}
