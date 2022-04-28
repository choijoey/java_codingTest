package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek7579 {

	//냅색 문제를 이해하고 있는지 묻는 문제
	//일단은 수업 때 배운 2차원 dp로 접근..
	//http://boj.kr/c384c4ade004434ba49206d9d81fdd36
	
	//1차원 배열 두개를 스왑해가면서 공간복잡도 줄이기 (시간복잡도는 비슷한듯?)
	//http://boj.kr/68081bc0c3014944aada2b5596b49741
	
	//1차원 배열 하나 사용해서(최대 비용부터 갱신하면서 풀기) 풀기
	//http://boj.kr/2490cb850c96489eb292e247f3733900
	
  //아무래도 연산 횟수는 같기 때문에 시간복잡도는 비슷하지만 공간복잡도는 유의미하게 줄어드는 것 같다.
  //(특히 2차원 ->1차원 2개)
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());//앱 개수
		int M = Integer.parseInt(st.nextToken());//필요한 메모리 양
		
		final int MAX = 10001;//메모리 100개 * 최대 비용 100
		
		int[][] dp= new int[N+1][MAX];//세로: 꺼야되는 앱  //가로: 해당 상태에서 드는비용
		
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
		
		
		//최대값은 20억을 넘지않음
		for(int i=1;i<=N;i++) {
			for(int j=1;j<MAX;j++) {
				
				if(cost[i]>j) {//만약 현재 앱의 비용이 j(해당 상태에서 드는 비용)을 초과한다면
					dp[i][j] = dp[i-1][j];
					continue;
				}
				if(dp[i-1][j]<dp[i-1][j-cost[i]]+val[i]) {//만약 현재 앱을 껐을때 비용이 같고 dp[i-1][j]보다 메모리가 크게 나온다면 
					dp[i][j]=dp[i-1][j-cost[i]]+val[i];
				}
				else
					dp[i][j]=dp[i-1][j];
			}
		}
		
		int ans=0;
		for(int i=1;i<=MAX;i++) {//마지막줄은 각 비용마다 확보할 수 있는 최대 메모리를 나타냄
			if(dp[N][i]>=M) {
				ans=i; //M바이트를 확보하기 위한 비활성화의 최소 비용
				break;
			}
		}
		System.out.println(ans);
		
	}

}
