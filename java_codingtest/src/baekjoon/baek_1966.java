package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek_1966 {

	//https://www.acmicpc.net/problem/1966


	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		while(T>0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			//문서의 개수
			int N = Integer.parseInt(st.nextToken());

			//궁금한 문서가 큐에서 몇번째로 놓여져 있는지
			int M = Integer.parseInt(st.nextToken());

			//배열 받음 
			st = new StringTokenizer(br.readLine());

			//pair 객체 큐 생성
			Queue<Integer> queue = new LinkedList<>();
			Queue<Integer> queue_idx = new LinkedList<>();

			//max 값 
			int max = -1;

			//정답
			int ans=0;
			int cnt=0;
			
			for(int i=0;i<N;i++) {
				int tmp = Integer.parseInt(st.nextToken());
				queue.offer(tmp);
				queue_idx.offer(i);

				max =Math.max(max,tmp);
			}
			while(true) {
				int top =queue.peek();
				
				while(top != max) {
					//top이 max가 아니면 poll 
					queue.offer(queue.poll());
					queue_idx.offer(queue_idx.poll());
					top =queue.peek();
				}
				//top이 max
				

				
				queue.poll();
				ans =queue_idx.poll();
				cnt++;
				
				if(ans == M)
					break;
				
				//다음 max값 찾음
				while(true) {
					
					if(queue.contains(max)) {
						break;
					}else {
						max--;
					}
						
				}
				
			}
			bw.write(cnt+"\n");
			bw.flush();
			T--;
		}
		bw.close();
	}

}
