package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;


// 수업 때 했던 np 응용한 조합으로 해봤습니다.

public class baek17135 {

	static int[] p;
	static int N,M,D;
	
	static Stack<int[]> stack;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//D 궁수 거리 제한 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int[][] map =new int[N][M];
		p = new int[M];
		stack = new Stack<>();
		
		int enemy=0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		for(int i=0;i<3;i++) {
			p[M-1-i] = 1;
		}
		
		//정답
		int score=0;
		
		do {
			
			int tmpScore=0;
			int[][] tmpMap = new int[N][M];
			
			//맵 복사
			arrayCopy(map,tmpMap);
			
			//궁수 배치
			int[] list = new int[3];
			int idx =0;
			
			for(int i=0;i<M;i++) {
				if(p[i] ==1)
					list[idx++] = i;
			}
			
			int cnt = N;
			
			//N 만큼 시뮬
			while(cnt>0) {
				
				//괴물 없는지 확인 
				boolean flag = true;
				
				for(int i=0;i<N;i++) {
					for(int j=0;j<M;j++) {
						if(map[i][j] == 1)
							flag =false;
					}
				}
				
				if(flag)
					break;
				
				//궁수 공격
				for(int i=0;i<3;i++) {
					attack(list[i],tmpMap);
				}
				
				//맵 업데이트
				while(!stack.isEmpty()) {
					int[] cur =stack.pop();
					if(tmpMap[cur[0]][cur[1]] == 1)
						tmpScore++;
					tmpMap[cur[0]][cur[1]] = 0;
				}
				
				//전진
				for(int i=N-1;i>0;i--) {
					tmpMap[i] = tmpMap[i-1];
				}
				tmpMap[0] = new int[M];
				
				cnt--;
			}
			
			score = Math.max(score, tmpScore);
			
			
//			System.out.println(Arrays.toString(list));
		}while(np());
		
		System.out.println(score);
		
	}
	static void arrayCopy(int[][] arr,int[][] arr2) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr2[i][j] = arr[i][j];
			}
		}
	}
	static void attack(int idx,int[][] map) {
		
		
		// 0 거리  1 y 2 x 순으로 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				if(o1[0]==o2[0])
					return o1[2]-o2[2];
				
				return o1[0]-o2[0];
				
			}});
		
		
		for(int i=N-1;i>0;i--) {
			for(int j=0;j<M;j++) {
				if(map[i][j] ==1 && range(idx,i,j)<=D) {
					pq.offer(new int[] {range(idx,i,j),i,j});
				}
			}
		}
		
		if(!pq.isEmpty()) {
			int[] tmp =pq.poll();
			stack.add(new int[] {tmp[1],tmp[2]});
		}
		
	}
	static int range(int curx, int y,int x) {
		int cury = N;
		
		return Math.abs(curx-x)+Math.abs(cury-y);
	}
	static boolean np() {
		
		int N = p.length;
		//1 꼭대기
		int i = N-1;
		
		while(i>0 &&p[i-1]>=p[i])
			i--;
		
		if(i==0)
			return false;
		//2 교환값
		int j =N-1;
		
		while(p[i-1]>=p[j])
			j--;
		
		//3 swap
		swap(i-1,j);
		
		//4 정렬
		int k = N-1;
		
		while(i<k) {
			swap(i++,k--);
		}
		return true;
		
		
	}
	static void swap(int i,int j) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}

}
