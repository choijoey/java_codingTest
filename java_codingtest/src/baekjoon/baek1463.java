package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baek1463 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int X = Integer.parseInt(br.readLine());
		
		int[] list = new int[X+1];
		
		for(int i=2;i<=X;i++) {
			
			list[i] =list[i-1]+1;
			
			if(i%3==0) {
				list[i] = Math.min(list[i/3]+1, list[i]);
			}
			if(i%2==0) {
				list[i] = Math.min(list[i/2]+1, list[i]);
			}
		}
		
		bw.write(list[X]+"");
		bw.flush();
		bw.close();
	}

}
