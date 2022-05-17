package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//dp를 이용한 풀이
//Bottom Up 방식으로 해결

//O(MN) 테이블을 채우기 위해 각 칸마다 한번씩 연산을 수행한다.
//http://boj.kr/4a3fda577fd84750acc25b3c5bfc204f

public class baek9251_2 {

	static int[][] dp;
	static char[] s1,s2;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		s1 = br.readLine().toCharArray();
		s2 =  br.readLine().toCharArray();
		
		int N = s1.length+1;
		int M = s2.length+1;
		
		dp = new int[N][M];
		
		
		for(int i=1;i<N;i++) {
			for(int j=1;j<M;j++) {
				if(s1[i-1]==s2[j-1])
					dp[i][j]= 1+dp[i-1][j-1];
				else
					dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
			}
		}
		
		System.out.println(dp[N-1][M-1]);
		
	}

}
