package baekjoon;

import java.util.Scanner;

public class baek_1316 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int ans =0;
		
		while(T>0) {
			
			String s = sc.next();
			
			boolean flag = true;
			int [] arr = new int[26];
			
			
			for(int i=0;i<s.length();i++) {
				if(i !=0 && s.charAt(i)==s.charAt(i-1))
					continue;
				if(arr[(int)(s.charAt(i)-'a')]==1) {
						flag=false;
						break;
				}
				arr[(int)(s.charAt(i)-'a')]=1;
			}
			if(flag)
				ans++;
			
			T--;
		}
		System.out.println(ans);
	}

}
