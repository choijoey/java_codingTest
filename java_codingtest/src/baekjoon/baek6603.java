package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baek6603 {

	
		static int[] list;
		static int[] npList;
		static int k;
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			String s =br.readLine();
			String[] tmp= s.split(" ");
			
			k = Integer.parseInt(tmp[0]);
			
			if(k == 0)
				break;
			
			list = new int[k];
			npList=new int[k];
			
			for(int i=0;i<k;i++) {
				list[i]=Integer.parseInt(tmp[i+1]);
			}
			
			
			for(int i=0;i<k-6;i++) {
				npList[k-1-i]=1;
			}
			
			StringBuilder sb = new StringBuilder();
			
			do{
				sb = new StringBuilder();
				for(int i=0;i<k;i++) {
					if(npList[i]==0)
						sb.append(list[i]).append(" ");
				}
				bw.write(sb.toString().trim());
				bw.write("\n");
			}while(np());
			
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		
		
	}
	
	static boolean np() {
		
		
		//1. i 찾기
		int i = k-1;
		
		while(i>0&&npList[i-1]>=npList[i])
			i--;
		
		//2.  i==0이면 종료
		if(i==0)
			return false;
		//3. swap 대상 찾기
		
		int j = k-1;
		
		while(npList[i-1]>=npList[j])
			j--;
		
		//4. swap
		swap(i-1,j);
		
		//5.뒤에 배열 오름차순
		int t = k-1;
		
		while(i<t) {
			swap(i++,t--);
		}
		return true;
	}
	static void swap(int i,int j) {
		int tmp = npList[i];
		npList[i]=npList[j];
		npList[j]=tmp;
		return;
	}

}
