package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//http://boj.kr/52f66fde09794162967a52bc55078f71
public class baek14503 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		st= new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];

		//0 북 1 동 2 남 3 서
		st= new StringTokenizer(br.readLine());
		
		int curY = Integer.parseInt(st.nextToken());
		int curX = Integer.parseInt(st.nextToken());
		int curD = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N;i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
		
		int ans =1;
		map[curY][curX] = 2; //시작 위치 청소
		
		int cnt=0; // 왼쪽 돌아간 횟수
		
		outer:while(true) {
			
			curD = (curD+4-1)%4;
			cnt++;
			if(map[curY+dir[curD][0]][curX+dir[curD][1]]==0) { //왼쪽방향에 벽이없고 아직 안간경우 
				curY = curY+dir[curD][0];
				curX = curX+dir[curD][1];
				
				map[curY][curX] = 2; //visited 처리
				cnt=0; // 횟수 초기화 
				ans++; //청소 구역 +1
			}
			
			if(cnt==4) {
				int ny = curY - dir[curD][0]; //현재 방향 뒤방향 
				int nx = curX - dir[curD][1];
				
				if(map[ny][nx]==1)
					break outer;
				else {
					curY = ny;
					curX = nx;
					cnt=0;
				}
			}
		}
		System.out.println(ans);
		
	}

}
