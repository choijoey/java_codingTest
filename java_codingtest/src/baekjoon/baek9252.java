package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class baek9252 {

	static int[][] dp;
	static char[] s1,s2;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		s1 = br.readLine().toCharArray();
		s2 =  br.readLine().toCharArray();
		
		int N = s1.length;
		int M = s2.length;
		
		dp = new int[N+1][M+1];
		
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(s1[i-1]==s2[j-1])
					dp[i][j]= 1+dp[i-1][j-1];
				else
					dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
			}
		}
		

		System.out.println(dp[N][M]);
		
		Stack<Character> stack = new Stack<>();
		
		if(dp[N][M] != 0) {
			int val = dp[N][M];
			
			int curY=N,curX=M;
			while(val != 0) {
				
				if(curY <0 ||curX<0)
					break;
				
				if(val == dp[curY-1][curX]){
					curY--;
				}
				else if(val == dp[curY][curX-1]){
					curX--;
				}
				else {
					val--;
					curY--;
					curX--;
					stack.push(s2[curX]);
				}

			}
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop());
		}
	}

}
//CAPCAK