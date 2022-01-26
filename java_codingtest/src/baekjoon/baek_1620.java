package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 다시 풀어보기 
public class baek_1620 {

	public static void main (String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String,Integer> nameMap = new HashMap<String,Integer>();
		
		String[] arr =new String[N+1];
		
		StringBuilder sb = new StringBuilder();
		
		
		for(int i=1;i<N+1;i++) {
			String name = br.readLine();
			nameMap.put(name, i);
			arr[i]=name;
		}
		
		while(M>0) {
			
			String ans=br.readLine();
			
			if(chk(ans)) {
				int index= Integer.parseInt(ans);
				sb.append(arr[index]);
			}else {
				sb.append(nameMap.get(ans));
			}
			sb.append("\n");
			M--;
		}
		System.out.println(sb.toString());
		
	}
	public static boolean chk(String s) {
		try {
			Double.parseDouble(s);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}

}
