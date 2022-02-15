package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//시간복잡도  NlogN

public class baek11399 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		//list[0] 순으로 오름차순 (인출시간)
		PriorityQueue<Integer> list = new PriorityQueue<>((o1,o2)-> o1-o2);
		
		StringTokenizer st =new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			list.offer(Integer.parseInt(st.nextToken()));
		} 
		
		
		StringBuilder sb =new StringBuilder();
		
		int time=0;
	
		for(int i=0;i<N;i++) {
			int tmp =list.poll();
			time += tmp*(N-i);
		}
		sb.append(time);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}

}
