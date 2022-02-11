package This_is_CodingTest_EX.chap03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ex2 {

	//큰 수의 법칙 93pg
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		
		int[] arr =new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		int ans =0;
		int start=N-1;
		int cnt =0;
		
		for(int i=1;i<=M;i++) {
			if(i%(K+1)==0) {
				ans+=arr[start-1];
			}
			else
				ans+=arr[start];
		}
		System.out.println(ans);
		
		
	}

}
