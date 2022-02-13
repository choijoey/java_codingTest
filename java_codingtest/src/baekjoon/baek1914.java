package baekjoon;

import java.math.BigInteger;
import java.util.Scanner;

public class baek1914 {

	static StringBuilder sb=new StringBuilder();
	static int flag;
	static int ans;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc =new Scanner(System.in);
		flag = sc.nextInt();

		if(flag >20) {
			BigInteger big = BigInteger.valueOf(2);
			big=big.pow(flag).subtract(BigInteger.valueOf(1));
			System.out.println(big);
			
		}		
		else {
		int n = flag;
		hanoi(n,1,2,3);
		System.out.println(ans);
		System.out.println(sb.toString());
		}
	}

	static void hanoi(int n,int from,int tmp,int to) {
		if(n==0)
			return;
		
		hanoi(n-1,from,to,tmp);
		sb.append(from+" "+to+"\n");
		ans++;
		hanoi(n-1,tmp,from,to);
	}
}
