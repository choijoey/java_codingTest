package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek10815 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st =null;
		
		int N=Integer.parseInt(br.readLine());
		int[] deck = new int[N];
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			deck[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int M=Integer.parseInt(br.readLine());
		int[] search = new int[M];
		
		st=new StringTokenizer(br.readLine());
		
		Arrays.sort(deck);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<M;i++) {
			int tmp = Integer.parseInt(st.nextToken());
			
			int low = 0;
			int high = N-1;
			boolean flag =true;
			while(low<=high) {

				int mid = low+(high-low)/2;
				
				if(deck[mid]==tmp) {
					flag=false;
					sb.append(1+" ");
					break;
				}
				else if(deck[mid]>tmp)
					high=mid-1;
				else
					low= mid+1;
			}
			if(flag) {
				sb.append(0+" ");
			}
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		
		
		
		
		
	}

}
