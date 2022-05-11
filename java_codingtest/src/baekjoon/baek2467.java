package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//투포인터를 이용하라고 대놓고 광고한 문제
//시간복잡도 O(N)

//시작 min 값이 음수일때를 생각 못해서 한참을 맞왜틀...

//http://boj.kr/6570cc47ce1744748563f71d555fadc7
public class baek2467 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] list = new int[N];

		st=new StringTokenizer(br.readLine());

		for(int i=0;i<N;i++)
			list[i]=Integer.parseInt(st.nextToken());


		//투포인터
		int start=0;
		int end=N-1;

		int min = Math.abs(list[start]+list[end]); //최소값

		int ans1=list[start];
		int ans2=list[end];

		while(start<end) {//포인터가 만나면 종료
			int sum = list[start]+list[end]; //최대값이 20억을 넘지 않으므로 int 가능

			int tmp= Math.abs(sum);
			if(tmp<min) {
				ans1=list[start];
				ans2=list[end];
				min=tmp;
			}
			if(sum>0)
				end--;
			else if(sum<0)
				start++;
			
			if(min==0)
				break;
		}
		System.out.print(ans1+" "+ans2);
	}

}
