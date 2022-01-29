package baekjoon;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class baek_1181 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		List<String> list = new ArrayList<>();
		
		while(T>0) {
			if(list.size() == 0)
				list.add(br.readLine());
			else {
				String s = br.readLine();
				if(!list.contains(s)) {
					list.add(s);
				}
			}
			T--;
		}

		Collections.sort(list, (String s1,String s2)-> {
				if(s1.length()!=s2.length())
					return s1.length()-s2.length();
				else {
					return s1.compareTo(s2);
				}
			}
		);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(String s : list) {
			bw.write(s+"\n");
			bw.flush();
		}
		bw.close();
		
	
	
	}

}
