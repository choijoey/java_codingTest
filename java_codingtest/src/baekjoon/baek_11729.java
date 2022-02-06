package baekjoon;

import java.util.Scanner;

public class baek_11729 {

	static int cnt=0;
	static StringBuilder sb = new StringBuilder();
	public static void cal (int n,int from,int temp,int to) {

		if(n==0)
			return;


		cal(n-1,from,to,temp);
		sb.append(from+" "+to+"\n");
		cnt++;
		cal(n-1,temp,from,to);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		cal(sc.nextInt(),1,2,3);
		
		System.out.println(cnt);
		System.out.println(sb.toString());
		
	}

}
