package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baek_1085 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[4];
		
		for(int i=0; i<4;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		int ans =Math.min(Math.abs(arr[0]-arr[2]),Math.abs(arr[1]-arr[3]));
		int ans2 =Math.min(Math.abs(arr[0]),Math.abs(arr[1]));
		ans = Math.min(ans,ans2);
		bw.write(ans+"");
		bw.flush();
		bw.close();
		

		
	}

}
