package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baek_17136 {

	static boolean[][] map; //10 x 10 맵 

	static int[] cur; // 현재 가지고 있는 종이 수 
	static int ans;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new boolean[10][10];

		ans =26;
		cur = new int[6];
		Arrays.fill(cur, 5);

		for(int i=0;i<10;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 0 )
					map[i][j] = false;
				else {
					map[i][j] = true;
				}
			}
		}

		cal(0,0,0); //재귀 함수 호출

		if(ans == 26)
			System.out.println(-1);
		else
			System.out.println(ans);

	}

	static void cal(int y,int x,int now ) {

		if(ans < now) return;

		if(y==9 && x==10) { //종료 조건

			ans = Math.min(ans, now); //최소 값 갱신 

			return;
		}

		if(x>9) {//다음줄로 이동
			cal(y+1,0,now);
			return;
		}
		
		//1인 칸에 대해 유망한지 찾아봄
		if(map[y][x]) {
			
			for(int k=5;k>0;k --) {
				if(chk(y,x,k) && cur[k] >0) { //이 칸이 종이를 놓을 수 있는 칸이고 k 사이즈의 종이가 남아 있다면
					cur[k] --;
					
					fill(y,x,k,false);
					
					cal(y,x+1,now+1);//해당 칸을 체크하고 재귀 호출
					
					cur[k] ++;
					fill(y,x,k,true);
				}
			}
		}
		else
			cal(y,x+1,now);
	}
	static boolean chk(int y,int x,int num) { //해당 칸을 기준으로 왼쪽 위 모서리에 종이를 겹쳤을때 num 크기의 종이가 맞으면 true 

		for(int i=0;i<num;i++) {
			for(int j=0;j<num;j++) {
				int ny = y+ i;
				int nx = x+ j;

				if(ny >= 10 || nx >= 10)
					return false;
				if(!map[ny][nx])
					return false;
			}
		}
		return true;
	}
	static void fill(int y,int x,int num , boolean flag) {
		for(int i=0;i<num;i++) {
			for(int j=0;j<num;j++) {
				map[y+j][x+i] = flag;
			}
		}
	}

}
