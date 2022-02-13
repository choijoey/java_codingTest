package This_is_CodingTest_EX.chap03;

import java.util.Scanner;

public class ex4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc= new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int cnt =0;
		
		while(N != 1) {
			
			if(N%K ==0)
				N/=K;
			else {
				int tmp = N%K;
				
				cnt+= N%K;
				N-=tmp;
				
			}
				
				
//				N-=1;
			
			cnt++;
		}
		
		System.out.println(cnt);
		
	}
}
