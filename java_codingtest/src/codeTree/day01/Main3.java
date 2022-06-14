package codeTree.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());//원소 갯수
		
		Map<String,Integer> map = new TreeMap<>();
		TreeSet<Integer> set = new TreeSet<>();
		
		set.add(3);
		set.add(4);
		set.add(6);
		set.add(8);
		set.add(10);
		
		System.out.println(set.higher(5));
		System.out.println(set.lower(5));

		for(int i=0;i<N;i++) {
			String s = br.readLine();
			
			
			if(map.get(s) == null) {
				map.put(s, 1);
			}
			else {
				int val = map.get(s);
				map.put(s, val+1);
			}
		}
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			int value = (int)map.get(key);
			System.out.printf("%s %.4f \n",key,((float)value/(float)N)*100);
		}

	}

}
