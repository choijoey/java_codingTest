package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// O(V^2)

public class baek16398 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//프림 알고리즘으로 해결
		//최소 비용 long 
		
		boolean[] v = new boolean[N];
		
		// 0 weight 1 vertex
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[0]-o2[0]);
		
		pq.offer(new int[] {0,0});
		
		int cnt=0;
		long ans =0;
		
		while(!pq.isEmpty()) {
			
			int[] cur =pq.poll();
			
			if(v[cur[1]])
				continue;
			v[cur[1]]=true;
			cnt++;
			ans+= cur[0];
			
			if(cnt == N)
				break;
			
			for(int i=0;i<N;i++) {
				if(map[cur[1]][i] ==0)
					continue;
				pq.offer(new int[] {map[cur[1]][i],i});
			}
			
		}
		
		System.out.println(ans);
		
	}

}
