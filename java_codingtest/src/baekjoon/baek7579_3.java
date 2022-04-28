package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek7579_3 {

	//냅색 문제를 이해하고 있는지 묻는 문제

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());//앱 개수
		int M = Integer.parseInt(st.nextToken());//필요한 메모리 양
		
		final int MAX = 10001;//메모리 100개 * 최대 비용 100
		
		int[] dp= new int[MAX];
		
		
		int[] val = new int[N+1];//앱 메모리 비용
		int[] cost =new int[N+1];//비활성화 비용
		
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			val[i]=Integer.parseInt(st.nextToken());
		}
		
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			cost[i]=Integer.parseInt(st.nextToken());
		}
		
		
		//배열 거꾸로 접근
		
		for(int i=1;i<=N;i++) {
			for(int j=MAX-1;j>=1;j--) {
				
				if(cost[i]>j) {//만약 현재 앱의 비용이 j(해당 상태에서 드는 비용)을 초과한다면
					continue;
				}
				if(dp[j]<dp[j-cost[i]]+val[i]) {//만약 현재 앱을 껐을때 비용이 같고 그 전 상태에서 보다 메모리가 크게 나온다면 
					dp[j]=dp[j-cost[i]]+val[i];
				}
			}
		}
		
		int ans=0;
		for(int i=1;i<=MAX;i++) {//마지막줄은 각 비용마다 확보할 수 있는 최대 메모리를 나타냄
			if(dp[i]>=M) {
				ans=i; //M바이트를 확보하기 위한 비활성화의 최소 비용
				break;
			}
		}
		System.out.println(ans);
		
	}

}
