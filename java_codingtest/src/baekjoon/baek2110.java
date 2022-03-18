package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek2110 {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] list = new int[N];
		
	
		for(int i=0;i<N;i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(list);//배열 정렬
		
		int low = 0;
		int high =list[N-1]; //최대값 배열 끝 값
		int mid=0;
		
		
		while(low<high) {
			mid = low+(high-low+1)/2; //mid 값 중 최대값 찾아야 함 
			
			int cnt=1;
			int cur =list[0];
			for(int i=1;i<N;i++) {
				if(list[i]-cur <mid)
					continue;
				else {
					cur=list[i];
					cnt++;
				}
				if(cnt>=C)
					break;
			}
			if(cnt <C) {
				high = mid-1;
			}
			else {
				low = mid;		//최대값 찾기 떄문에 low는 범위에 포함시킴
			}
			
		}
		System.out.println(low);
		
	}

}
