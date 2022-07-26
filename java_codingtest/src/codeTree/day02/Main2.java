package codeTree.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] list = new int[N];
		

		st= new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			list[i]= Integer.parseInt(st.nextToken());
		}
		
		int[] list1 = new int[N+1];
		
		list1[0]= 0;
		
		for(int i=1;i<=N;i++) {
			list1[i]= list1[i-1]+list[i-1];
		}
//		System.out.println(Arrays.toString(list1));
		int ans =list1[M]-list1[0];
		
		for(int i=M;i<=N;i++) {
			int tmp =list1[i]-list1[i-M];
			ans=Math.max(ans, tmp);
		}
		
		System.out.println(ans);
	}
	

}
