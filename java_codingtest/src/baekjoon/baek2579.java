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

		int[] arr = new int[N+3];
		int[] dp = new int[N+3];

		for(int i=3;i<N+3;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 초기값 0 1 2 => 0으로 설정해서 첫번쨰 칸부터 계산 
		
		//현재 계단을 기준으로
		//자기한테 올 수 있는 경우는 2칸 점프 후 1칸 
		//또는 2칸 점프 후 도착
		
		//이렇게 계산할 경우 2번 연속 오르는 경우는 없음
		for(int i=3;i<N+3;i++) {
			dp[i]=Math.max(dp[i-3]+arr[i-1], dp[i-2])+arr[i];
			//System.out.println(Arrays.toString(dp));
		}
		
		System.out.println(dp[N+2]);

	}

}
