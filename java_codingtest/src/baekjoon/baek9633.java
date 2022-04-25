package baekjoon;

import java.util.Scanner;

public class baek9633 {

	static int ans,N,list[];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		list = new int[N+1];
		
		dfs(1);
		System.out.println(ans);
	}
	
	static void dfs(int node) {
		
		if(node == N+1) {
			ans++;
			return;
		}
		
		for(int i=1;i<=N;i++) {
			list[node]=i;
			
			if(isPromising(node)) {
				dfs(node+1);
			}
		}
	}
	static boolean isPromising(int node) {
		
		for(int i=1;i<node;i++) {
			if(list[i]==list[node]) return false; //행 같으면 제외
			if(node-i==Math.abs(list[node]-list[i])) return false;//우하 대각선 같음
		}
		return true;
	}

}
