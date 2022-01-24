package baekjoon;

import java.util.Scanner;

public class baek_2941 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc =new Scanner(System.in);
		
		String s = sc.next();
		
		s= s.replaceAll("c=", "#")
		.replaceAll("c-", "#")
		.replaceAll("dz=", "#")
		.replaceAll("d-", "#")
		.replaceAll("lj", "#")
		.replaceAll("nj", "#")
		.replaceAll("s=", "#")
		.replaceAll("z=", "#");
		
		String[] a=s.split("");
		System.out.println(a.length);
		
		
		
	}

}
