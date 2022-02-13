package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baek10974 {

	static boolean[] v;
	static int[] list;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		v= new boolean[N];
		list= new int[N];
		
		recur_cal(0);
		
		
	}
	
	static void recur_cal(int cnt) {
		if(cnt == N) {
			for(int i=0;i<N;i++) {
				System.out.print((list[i] +1)+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<N;i++) {
			
			if(v[i])
				continue;
			
			
			list[cnt] = i;
			v[i]= true;
			
			recur_cal(cnt+1);
			v[i] =false;
			
		}

	}
}
