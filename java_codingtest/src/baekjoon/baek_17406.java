package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_17406 {

	static int N,M,K;//배열 크기 N, M, 회전 연산의 개수 K
	static int[][] map;
	static int[][] li;//연산 리스트
	static int[] orderList;//순열 리스트
	static boolean[] v;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		ans =999_999_999; //답
		
		for(int i=0;i<N;i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		li = new int[K][3]; // 연산 정보 입력받음
		orderList = new int[K]; //순열 돌리기 위한 연산 순서 리스트
		v = new boolean[K];
		
		for(int i=0;i<K;i++) {
			st= new StringTokenizer(br.readLine());
			li[i]= new int[]{Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())};
		}
		
		
		permu(0);
		
		System.out.println(ans);
		
	}
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	
	static void cal() {
		
		for(int i=0;i<N;i++) {
			int tmp =0;
			for(int j=0;j<M;j++) {
				tmp+=map[i][j];
			}
			ans = Math.min(ans, tmp);
		}
	}
	static void rotate(int[] tmp) {
		int r = tmp[0];
		int c = tmp[1];
		int s = tmp[2];
		
		for(int i=1; i <= s;i++) {
			
			int y = r-i;//시작 좌표
			int x = c-i;
			
			int ny = y + dy[0];
			int nx = x + dx[0];
			
			int next = 0;
			int cur =map[y][x];
			
			for(int t =0 ;t<4;t++) {
				
				for(int k=0;k<2*i;k++) {

					ny = y + dy[t];
					nx = x + dx[t];
					
					next = map[ny][nx];
					map[ny][nx] = cur;
					
					y= ny;
					x= nx;

					
					cur = next;
				}

			}
			
		}
	}
	static void permu(int cnt) {
		
		if(cnt == K) {
			
			int[][] tmp = new int[N][M];
			copy(tmp,map); //기존 맵 정보 임시 저장
			
			for(int i=0;i<K;i++) {
				rotate(li[orderList[i]]); // 순열의 순서대로 연산 시작
			}
			cal(); // 연산 후 점수 계산
			map = tmp; // 바뀐 맵 초기화 
			return;
		}
		for(int i=0;i<K;i++) {
			
			if(v[i]) continue;
			
			v[i] = true;
			orderList[cnt] = i;
			
			permu(cnt+1);
			
			v[i] = false;
		}
		
	}
	static void copy(int[][] a,int[][] b) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				a[i][j] = b[i][j];
			}
		}
	}
}
