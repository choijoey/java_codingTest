package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek2606 {

	static int N,ans;
	static boolean[] v;
	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		int E=Integer.parseInt(br.readLine());
		
		v=new boolean[N+1];
		adjList = new ArrayList[N+1];
		
		for(int i=0;i<N+1;i++) {
			if(i==0)
				continue;
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0;i<E;i++) {
			st= new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		//dfs 수행 
		dfs(1);
		System.out.println(ans);
		
	}
	static void dfs(int node) {
		
		//1번 바이러스 걸림
		v[node] = true;

		
		for(int i=0, size=adjList[node].size() ;i<size;i++) {
			
			int adjNode = adjList[node].get(i);
			
			if(v[adjNode])
				continue;
			else {
				ans++;
				dfs(adjNode);
			}
		}
	}

}
