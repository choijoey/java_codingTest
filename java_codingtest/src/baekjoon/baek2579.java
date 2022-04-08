package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek2579 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N+1];
		int[] dp = new int[N+1];

		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 0;
		dp[1] = arr[1];
		dp[2] = arr[1]+arr[2];
		
		for(int i=1;i<=N-2;i++) {
			dp[i+2]=Math.max(dp[i+2], dp[i]+arr[i+2]);
			System.out.println(Arrays.toString(dp));
		}
		
		System.out.println(dp[N]);

	}

}
