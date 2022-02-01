package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_1920 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[] arr =new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());


		for(int i=0;i<M;i++) {
			int start =0;
			int end = N-1;

			boolean flag = false;
			int tmp =Integer.parseInt(st.nextToken());
			
			while(start<=end) {
				int mid = (start+end)/2;

				if(tmp == arr[mid]) {
					flag = true;
					break;
				}

				if(tmp <arr[mid]) {
					end = mid-1 ;
				}else if(tmp >arr[mid]) {
					start=mid+1;
				}
			}
			if(flag) {
				bw.write(1+"\n");
			}
			else {
				bw.write(0+"\n");
			}
			bw.flush();
		}
		bw.close();
	}





}
