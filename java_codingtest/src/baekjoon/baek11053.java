package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek11053 {

	//NlogN
	//http://boj.kr/3f0fe28b053446b18acf8ece5d4b1ba8
	
	//N^2도 가능
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
		}
		
		LIS[0]=list[0];
		int idx=0;
		
		for(int i=1;i<N;i++) {
			if(list[i]>LIS[idx]) {
				LIS[++idx]=list[i];
			}else {
				int tIdx=bs(LIS,idx,list[i]);
				LIS[tIdx]=list[i];
			}
		}
		System.out.print(idx+1);
		
	}
	static int bs(int[] list,int high,int val) {
		int low =0;
		
		while(low<high) {
			int mid = low+(high-low)/2;
			
			if(list[mid]<val) {
				low=mid+1;
			}else {
				high=mid;
			}
		}
		
		return low;
	}
}
