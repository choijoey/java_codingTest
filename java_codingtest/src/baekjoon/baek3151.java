package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek3151 {


	// 혹시 27퍼 쯤에서 틀렸다면 
	// 배열이 모두 0인 경우 10000 C 3   => 정답은  int 범위를 초과한다 
	
	// 투포인터를 이용하여 두 수를 먼저 정하고 나머지 한 수만 이분탐색
	// 정렬 NLogN 
	// 탐색 N*(N-1)*(upper bound(Log N) + lower bound(Log N))
	// O(N^2 * log N)
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] list = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		for(int i=0;i<N;i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		//이진탐색을 위한 소트
		Arrays.sort(list);
		
		long ans=0;


		//첫번쨰와 두번째 수는 완탐 형식으로 배열 돌림
		for(int i =0;i<N-2;i++) {
			for(int j=i+1;j<N-1;j++) {
				
				int cur=list[i]+list[j];//첫번째로 선택한 값+ 두번째로 선택한 값
				
				
				
				//두번의 이분탐색을 통해 lower bound와 higher bound를 구함
				//만약 세번째 고른 값의 합이 0 이 나온다면
				// 정답 += (higher bound - lower bound +1)
				int low = j+1;
				int high = N-1;
				
				while(low<high) {
					int mid = low+(high-low)/2;
					
					if(cur+list[mid] <0) {
						low=mid+1;
					}
					else {
						high=mid;
						
					}
				}
				
				int lMid = low;
				
				low = j+1;
				high = N-1;
				
				while(low<high) {
					int mid = low+(high-low+1)/2;
					
					if(cur+list[mid] <=0) {
						low=mid;
					}
					else {
						high=mid-1;
						
					}
				}
				
				int hMid= low;
				
				if(cur+list[low]==0) {
//					System.out.println(lMid + "         "+ hMid);
					ans+= (hMid-lMid+1);
				}

			}
			
		}
		System.out.println(ans);
		
		
		
	}

}
