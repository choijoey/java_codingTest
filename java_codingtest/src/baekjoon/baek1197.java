package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class baek1197 {
	//http://boj.kr/e1485905170b40de9bc3619294e64aae
	//간선 중심의 MST => 크루스칼 O(ElogE) :정렬이 가장 오래걸림
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] edgeList = new int[E][3];
		

		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			edgeList[i][0] =from;
			edgeList[i][1] =to;
			edgeList[i][2] =val;
		}
		Arrays.sort(edgeList,(o1,o2)->(o1[2]-o2[2])); //가중치 기준 오름차순 정렬
		makeSet(V);
		
		int ans =0;
		
		for(int i=0;i<E;i++) {
			int[] cur =edgeList[i];
			
			if(union(cur[0],cur[1]))
				ans+=cur[2];
			
		}
		System.out.println(ans);
		
	}
	static int[] par;
	static void makeSet(int N) {
		par = new int[N+1];
		
		for(int i=1;i<=N;i++)
			par[i] = i;
	}
	static int findSet(int num) {
		
		if(par[num] == num)
			return num;
		
		return par[num] = findSet(par[num]);
	}
	static boolean union(int a,int b) {
		
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot==bRoot)
			return false;
		
		par[bRoot] = aRoot;
		return true;
	}
	

}
