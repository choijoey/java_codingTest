package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek11053_2 {

	//N^2
	//http://boj.kr/5bcc3d3485ff499795a9873c2d616459
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] list =new int[N];
		int[] LIS = new int[N];
		st= new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			list[i] = Integer.parseInt(st.nextToken());
			LIS[i]=1;
		}

		for(int i=0;i<N;i++) {
			for(int j=0;j<i;j++) {
				if(list[i]>list[j])
					LIS[i]=Math.max(LIS[i], LIS[j]+1);
			}
		}
		
		int ans=0;
		
		for(int i=0;i<N;i++) {
					ans=Math.max(LIS[i], ans);
		}
		System.out.print(ans);
	}
}
