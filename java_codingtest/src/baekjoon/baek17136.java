package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 답봄 나중에 다시 풀어봐야지..
//http://boj.kr/1cbe1894a7a84f6f8562f9848822b247
public class baek17136 {

	static int[][] map;
	static int[] cnt;//종이 개수
	static int ans;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];
		cnt = new int[6];
		ans = 999_999_999;


		for(int i=0;i<10;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}


		dfs(0,0,0);
		
		if(ans==999_999_999) //최솟값이 바뀌지 않으면 -1
			System.out.print(-1);
		else
			System.out.print(ans);
	}
	static void dfs(int y,int x,int add) {
		
		if(ans<=add)//만약 붙여하는 종이 개수가 최솟값보다 크다면 가지치기
			return;
		
		boolean flag = true;
		outer: for(int i=y;i<10;i++) {//현재 y 위치를 기준으로 다음 1인 칸을 찾는다 
			for(int j=0;j<10;j++) {
				if(map[i][j]==1) {
					y=i;
					x=j;
					flag = false;
					break outer;
				}
			}
		}
		if(flag) {//모든 칸이 0이라면 정답
//			System.out.println(Arrays.toString(cnt));
			ans = Math.min(ans, add);
			return;
		}

		
		for(int size=5;size>=1;size--) {//사이즈가 큰 종이부터 차례대로 비교해본다
			
			if(isPromising(y,x,size)) {//유망하다면

				fill(y,x,size,0);//해당 칸을 덮고
				cnt[size]++;//해당 종이 개수 증가
				dfs(y,x,add+1);//다음 1인 칸을 찾아 종이 덮기
				
				fill(y,x,size,1);//백트래킹
				cnt[size]--;
			}
		}

	}
	static boolean isPromising(int y,int x, int size) {

		if(y+size>10 || x+size>10||cnt[size] ==5)
			return false;
		for(int i=y;i<y+size;i++) {
			for(int j=x;j<x+size;j++) {
				if(map[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	static void fill(int y,int x, int size,int to) {
		for(int i=y;i<y+size;i++) {
			for(int j=x;j<x+size;j++) {
				map[i][j] =to;
			}
		}
	}
}
