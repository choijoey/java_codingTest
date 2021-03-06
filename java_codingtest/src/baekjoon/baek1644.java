package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// O(n log log n)

public class baek1644 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N =  Integer.parseInt(br.readLine());
		boolean[] list = new boolean[4000001];//전체 수 집합


		int[] prime = new int[4000001];//소수집합
		int end =0; // 마지막 idx

		//소수 구하는 에라토스테네스체  시간복잡도 ->  O(n log log n) 라네요... (https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Algorithm_complexity)
		for(int i=2;i<=N;i++) {
			if(!list[i]) {
				int j =2;
				int nidx= i*j;
				while(nidx<=N) {
					list[nidx]= true;
					j++;
					nidx= i*j;
				}
				prime[end++] = i;
			}
		}
		//		System.out.println(Arrays.toString(prime));
		
		int ans=0;
		
		if(N!=1) {//1일경우 그냥 종료

			//투포인터 시간복잡도 N
			int st=0;
			int en=0;
			int sum = 0; //초기값
			while(en<=end) {
				if(sum<N) {
					sum+= prime[en++];
				}
				else{
					if(sum==N) //정답
						ans++;
					sum-=prime[st++];
				}
			}
		}
		StringBuilder sb =new StringBuilder();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
