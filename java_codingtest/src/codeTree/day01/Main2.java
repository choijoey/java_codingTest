package codeTree.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());//원소 갯수
		int M = Integer.parseInt(st.nextToken());//질의 수
		
		HashMap<Integer,Integer> map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(map.get(num) == null) {
				map.put(num, 1);
			}
			else {
				int val = map.get(num);
				map.put(num, val+1);
			}
		}
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<M;i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(map.get(num) == null) {
				System.out.print("0 ");
			}
			else {
				System.out.print(map.get(num)+" ");
			}
		}
		
	}

}
