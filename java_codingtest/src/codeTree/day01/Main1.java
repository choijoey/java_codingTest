package codeTree.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		HashMap<Integer,Integer> map = new HashMap<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			String s = st.nextToken();
			
			if(s.equals("add")) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map.put(a, b);
			}
			else if(s.equals("find")) {
				int a = Integer.parseInt(st.nextToken());
				
				if(map.get(a)== null) {
					System.out.println("None");
				}
				else {
					System.out.println(map.get(a));
				}
			}else if(s.equals("remove")) {
				
				int a = Integer.parseInt(st.nextToken());
				map.remove(a);
			}
			
		}
	}

}
