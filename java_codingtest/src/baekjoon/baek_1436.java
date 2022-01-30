package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baek_1436 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		int cnt = 1;
		int start = 666;
		while(true) {
			if(cnt == num)
				break;
			start++;
			String s =String.valueOf(start);
			if(s.indexOf("666") != -1) {
				cnt++;
			}
		}
		
		bw.write(start+"\n");
		bw.flush();
		bw.close();
			
	}
	

}
