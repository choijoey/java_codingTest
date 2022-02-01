package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class baek_1874 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Stack<Integer> arr = new Stack<Integer>();

		int N = Integer.parseInt(br.readLine());
	

		int cnt = 1;
		StringBuilder sb =new StringBuilder();
		boolean flag = true;
		
		for(int i=1;i<=N;i++) {
			int tmp =Integer.parseInt(br.readLine());
			int cur=0;
			
			if(arr.size()!=0)
				cur = (arr.peek()).intValue();


			if(cnt>tmp && !arr.contains(tmp)) {
				flag= false;
				break;
			}
			

			if(cur<tmp) {
				while(cur!=tmp) {	
					arr.push(cnt);
					sb.append("+"+"\n");
					cur = arr.peek();
					cnt++;
				}
			}else if(cur>tmp) {
				while(cur!=tmp) {	
					arr.pop();
					sb.append("-"+"\n");
					cur = arr.peek();
				}			
			}
			if(cur == tmp) {
				arr.pop();
				sb.append("-"+"\n");
			}

		}
		
		if(flag) {
			bw.write(sb.toString());
		}
		else {
			bw.write("NO");
		}
		bw.flush();
		bw.close();
	}

}
