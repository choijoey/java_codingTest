package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//http://boj.kr/843f87b938b849b38cf3be0f93708f6d

public class baek20955 {
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
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 뉴런의 개수
		int M = Integer.parseInt(st.nextToken()); // 시냅스의 개수
		
		int unionVal=0; //MST에 포함된 시냅스
		int notUnionVal=0; //제거할 시냅스 
		makeSet(N);
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken()); 
			int to = Integer.parseInt(st.nextToken());
			
			if(union(from,to)) {
				unionVal++;
			}else
				notUnionVal++;
		}
		
		System.out.println(N-1-unionVal+notUnionVal);
	}

}
