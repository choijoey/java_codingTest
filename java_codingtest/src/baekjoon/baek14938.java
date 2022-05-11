package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek14938 {

	//플로이드 와샬 문제
	//O(V^3)
	//http://boj.kr/c0204d9dc79c4e1e94865187a48b8438
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st= new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());//지역의 개수
		int M = Integer.parseInt(st.nextToken());//수색 범위
		int R = Integer.parseInt(st.nextToken());//길이의 개수

		int[] node = new int[N];//노드의 값(아이템 개수)

		int[][] map= new int[N][N];
		
		final int MAX=999_999_999;


		st= new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			node[i]=Integer.parseInt(st.nextToken());
			for(int j=0;j<N;j++) {
				map[i][j] = MAX;
			}
		}

		for(int i=0;i<N;i++) {
			map[i][i]=0;
		}

		for(int i=0;i<R;i++) {
			st= new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int val = Integer.parseInt(st.nextToken());

			map[from][to] = val;
			map[to][from] = val;
		}

		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				if(i==k) continue;
				for(int j=0;j<N;j++) {
					if(i==k||j==k) continue;
					
					if(map[i][j]>map[i][k]+map[k][j]) {
						map[i][j]=map[i][k]+map[k][j];
					}
				}
			}
		}
		
		int max=0;
		
		for(int i=0;i<N;i++) {
			int tmp =0;
			for(int j=0;j<N;j++) {
				
				if(map[i][j] <=M) {//수색 범위 내라면 아이템 먹음
					tmp+=node[j];
				}
				
			}
			max=Math.max(max, tmp);
		}

		System.out.print(max);
	}

}
