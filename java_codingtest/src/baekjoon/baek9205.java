package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek9205 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			int N =  Integer.parseInt(br.readLine());//편의점 개수
			
			int[][] map = new int[N+2][2];
			
			for(int i=0;i<N+2;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				map[i][0] = a;
				map[i][1] = b;
			}
			
			Queue<int[]> queue = new LinkedList<>();
			boolean[] v = new boolean[N+1];
			
			queue.offer(map[0]);
			String ans="sad";
			
			outer:while(!queue.isEmpty()) {
				
				int[] cur = queue.poll();
				if(cal(cur,map[N+1])<=1000) {
					ans="happy";
					break outer;
				}
				for(int i=1;i<=N;i++) {
					
					if(v[i])
						continue;
					int tmp = cal(cur,map[i]);
			
					if(tmp<=1000) {
						queue.offer(map[i]);
						v[i] = true;
					}
				}
			}
			System.out.println(ans);
			
		}
	}
	static int cal(int[] a ,int[] b) {//두 지점 거리 계산
		return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]); 
	}

}
