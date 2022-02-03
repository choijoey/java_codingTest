package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baek_1929 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int M=Integer.parseInt(st.nextToken());
		int N=Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		
		List<Integer> ans = new ArrayList<>(); 
		//소수 입력받는 배열
		
		
		//arr 0이면 소수
		
		
		for(int i=2;i<=N;i++) {
			if(arr[i] == 0) {//i가 소수라면
				
				if(i>=M) // 소수가 M 보다 크다면 추가
					ans.add(i);
				
				for(int j=i;j<=N;j++) {
					
					//오버플로우 방지 
					long temp = (long)i*j;
					
					//N범위보다 값 크게 나오면
					if(temp >=N+1)
						break;
					//소수의 배수들은 제외
					if(arr[(int) temp] == 0)
						arr[(int) temp]=1;
					
				}
			}
			
		}
		
		for(int tmp:ans) {
				sb.append(tmp+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}

}
