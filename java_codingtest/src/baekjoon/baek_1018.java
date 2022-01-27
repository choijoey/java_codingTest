package baekjoon;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baek_1018 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		String[] arr = {
				"WBWBWBWB",
				"BWBWBWBW",
				"WBWBWBWB",
				"BWBWBWBW",
				"WBWBWBWB",
				"BWBWBWBW",
				"WBWBWBWB",
				"BWBWBWBW"
		};
		String[] brr = {
				"BWBWBWBW",
				"WBWBWBWB",
				"BWBWBWBW",
				"WBWBWBWB",
				"BWBWBWBW",
				"WBWBWBWB",
				"BWBWBWBW",
				"WBWBWBWB"
		};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] crr = new char[N][M];
		

		for(int i=0;i<N;i++) {
			st =new StringTokenizer(br.readLine());
			String s =st.nextToken();
			for(int j=0;j<M;j++) {
				crr[i][j]=s.charAt(j);
			}
		}

		int min =N*M;
		
		for(int i=0;i<=N-8;i++) {
			for(int j=0;j<=M-8;j++) {
				int cnt =0;
				int cnt2 =0;
				
				for(int a=0;a<8;a++) {
					for(int b=0;b<8;b++) {
						if(arr[a].charAt(b) != crr[a+i][b+j]) {

							cnt++;
						}
						if(brr[a].charAt(b) != crr[a+i][b+j]){
							cnt2++;
						}
						
					}
				}
				if(cnt>cnt2) {
					cnt=cnt2;
				}
				if(min>cnt)
					min=cnt;
				
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(min+"\n");
		bw.flush();
		bw.close();

		}
	}


