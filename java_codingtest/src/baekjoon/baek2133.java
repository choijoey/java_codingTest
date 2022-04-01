package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baek2133 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[] list = new int[N+2];

		list[0]=1; // N=4도 계산할 수 있게끔 1 설정
		list[2]=3;

		
		//타일 뒤에서부터 채우면서 경우를 계산 
		//f(4) = f(2)*f(2)+2*f(0)
		//f(6) = f(4)*f(2)+2*f(2)+2*f(0)
		//f(8) = f(6)*f(2)+2*f(4)+2*f(2)+2*f(0)
		
		if(N>=4) {
			for(int i=4;i<=N;i++) {
				list[i] = list[i-2]*list[2];
				for(int j=i-4;j>=0;j-=2) {
					list[i] = list[i] + list[j]*2;
				}
			}
		}
		
//		bw.write(list[N]+"");
//		bw.flush();
//		bw.close(); 
		System.out.println(Arrays.toString(list));
	}

}
