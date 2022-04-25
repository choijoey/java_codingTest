package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//그리디로도 풀수 있을것 같았는데 도저히 못풀겠어서 다른코드 참고해서 풀었다..
//disjoint Set을 이렇게 쓸 수있구나...
//게이트에서 다음으로 비어있는 인덱스를 가리킬때 값을 갱신시키기 위해서 계속 N^2의 연산을 수행하면서
//그리디적으로만 해결해보려고 했었는데 다음에는 인덱스 여러개를 한번에 갱신시켜야 된다면
//분리집합을 이용하여 한번에 갱신시키는 방법도 고려해봐야 될것 같다.

//http://boj.kr/a2daa7fc34314f8aab8aaa2535fb6975
public class baek10775 {


	static int[] gate;
	
	static int findSet(int a) {
		
		if(gate[a]==a)
			return a;
		
		return gate[a]=findSet(gate[a]);
	}
	static boolean union(int a,int b) {
		a=findSet(a);
		b=findSet(b);
		
		if(a==b)
			return false;
		
		gate[b]=a;
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

		int G = Integer.parseInt(br.readLine());//게이트 수
		int P = Integer.parseInt(br.readLine());//비행기 수
		
		 gate = new int[G+1];
		 
		 for(int i=1;i<=G;i++) //makeSet
			 gate[i]=i;
		
		int ans=0;
		
		for(int i=1;i<=P;i++) {
			int num = Integer.parseInt(br.readLine());
			
			int idx = findSet(num);//num에서 시작해서 비어있는 게이트 위치
			
			if(idx==0) {
				break;
			}
			
			union(idx-1,idx);
			ans++;
		}
		System.out.println(ans);
	}
}
