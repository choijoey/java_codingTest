package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baek15961 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st =new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());//접시 수
		int d = Integer.parseInt(st.nextToken());//가짓 수
		int k = Integer.parseInt(st.nextToken());//연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken());//쿠폰 번호

		//접시 배열
		int[] list = new int[N];

		//중복확인용 배열
		int[] v = new int[d+1];

		for(int i=0;i<N;i++) {
			list[i] = Integer.parseInt(br.readLine());
		}

		int ans =-1; //먹을 수 있는 초밥의 가짓수의 최대값

		int cnt=0;//현재 먹을 수 있는 가짓 수
		int bonus=0;//쿠폰 으로 가짓 수 늘릴 수 있으면 보너스 
		
		for(int i=0;i<k;i++) { //윈도우 크기 k
			v[list[i]]++;
			if(v[list[i]]==1)
				cnt++;
		}

		if(v[c]==0)//쿠폰 가짓수 추가
			bonus=1;

		ans=cnt+bonus;

		if(ans!=k+1) { //첫 윈도우에 정답이 나오지 않으면
			int start=0; //윈도우 시작
			int end=k; //윈도우 끝

			for(int i=0;i<N;i++) {
				

				if(v[list[end]]==0)//개수가 늘었으면 +1
					cnt++;
				
				v[list[start]]--;
				v[list[end++]]++;
				
				if(v[list[start++]]==0)//개수가 줄었으면 -1
					cnt--;

				
				if(v[c]==0)//쿠폰 가짓수 추가
					bonus=1;
				else
					bonus=0;
				ans=Math.max(ans, cnt+bonus);
				
				end%=N;
			}
			
		}
		bw.write(ans+"");
		bw.flush();
		bw.close();


	}

}
