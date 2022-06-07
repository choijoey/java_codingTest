package swacademey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea1865 {

	static double ans;
	static int N;
	static int[][] list;
	static boolean[] v;
	static int[] input;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=  new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			list = new int[N][N]; //확률 
			ans =0;
			v = new boolean[N];
			input = new int[N];
			
			for(int i=0;i<N;i++) {
				st=  new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					list[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			cal(0,1);

			System.out.printf("# %d %f\n",tc,Math.round(ans*Math.pow(10,8))/Math.pow(10, 6));
		}
	}
	static boolean flag = false;
	
	static void cal(int num,double tmp) {
		
		if(tmp==0)
			return;

		if(num == N) {
			

			ans = Math.max(ans,tmp);
			flag = true;
			return;
		}
		if(flag) {

			if(tmp<ans)
				return;
		}
		for(int i=0;i<N;i++) {
			if(v[i])
				continue;
			
			v[i] = true;
			input[num] = i;
			cal(num+1,tmp*list[num][i]/100);
			
			v[i]=false;
		}
	}



}
