package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek5577 {

	static int[] list;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		list = new int[N+2];
		
		for(int i=N;i>=1;i--) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		int ans =N;
		
		for(int i=1;i<=N;i++) {
			
			if(list[i]!=list[i-1])
				ans =Math.min(ans, pang(i,list[i-1]));
			if(list[i]!=list[i+1])
				ans =Math.min(ans, pang(i,list[i+1]));
		}
		System.out.println(ans);
	}
	static int pang(int idx, int color) {
		
		int st = idx;
		int end = idx;
		
		while(st>1&&color == list[st-1])st--;
		while(end<N && color== list[end+1])end++;
		
		int res = N;
		
		if(end-st+1 >=4)
			res = remove(st,end);
		
		return res;
	}

	static int remove(int st,int end) {
		
		int cnt=0;
		st--;end++;
		int tmpStart= st;
		int tmpEnd=end;
		while(st>=1&&end<N+1) {

			while(tmpStart>0&&list[tmpStart]==list[st]&&list[tmpStart]==list[end]) {
				tmpStart--;
				cnt++;
			}
			while(tmpEnd<=N&&list[tmpEnd]==list[end]&&list[tmpEnd]==list[st]) {
				tmpEnd++;
				cnt++;
			}
			if(cnt>=4) {
				st = tmpStart;
				end = tmpEnd;
				cnt=0;
			}
			else
				break;
		}
		
		return (N-end+1)+st;
	}
}