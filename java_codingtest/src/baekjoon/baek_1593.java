package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baek_1593 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer  st = new StringTokenizer(br.readLine());
		
		int g = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		
		
		int[] list = new int[60];
		int[] list2 = new int[60];
		
		String G  = br.readLine();
		String S = br.readLine();
		
		int ans=0;
		
		
		if(s==1) {
			if(S.charAt(0)==G.charAt(0))
				ans=1;
		}
		else {
			for(int i=0;i<g;i++) {
				char c = G.charAt(i);
				list2[(int)(c-'A')]++;
			}

			
			for(int i=0;i<g;i++) {
				char c = S.charAt(i);
				list[(int)(c-'A')]++;
			}
			
			boolean chk = true;
			
			for(int j=0;j<60;j++) {
				if(list[j] != list2[j]) {
					chk =false;
					break;
				}
			}
			if(chk)
				ans++;
			
			for(int i=g;i<s;i++) {
				char c = S.charAt(i);
				
				int out = (int)(S.charAt(i-g)-'A');
				int in = (int)(c-'A');
				
				
				list[out]--;
				
				list[in]++;
				
				if(list[out] == list2[out] && list[in] == list2[in]) {
					 chk = true;
					
					for(int j=0;j<60;j++) {
						if(list[j] != list2[j]) {
							chk =false;
							break;
						}
					}
					if(chk)
						ans++;
				}
			}
			
		}

		System.out.println(ans);
	}

}
