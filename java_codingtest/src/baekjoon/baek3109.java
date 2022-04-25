package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;




public class baek3109 {

	static char[][] map;
	static int R,C;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R+2][C+2];


		//값 입력
		for(int i=1;i<=R;i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=1;j<=C;j++)
				map[i][j]= tmp[j-1];
		}

		//벽 둘러싸기
		for(int i=0;i<R+2;i++) {
			map[i][0]='x';
			map[i][C+1]='x';
		}
		for(int i=0;i<C+2;i++) {
			map[0][i]='x';
			map[R+1][i]='x';
		}



		for(int start=1;start<=R;start++) {//파이프 출발
			dfs(start,0);
		}
		System.out.println(ans);
	}

	static int ans=0;
	static int[] dir= {-1,0,1};//순서대로 우상 우 우하 방향

	static boolean dfs(int y,int x) {

		map[y][x]= 'x';//지나간 파이프 방문처리

		if(x == C) {//도착하면 종료
			map[y][x]= 'x';
			ans++;
			return true;
		}

		for(int i=0;i<3;i++) {
			int	ny = y+ dir[i];
			int	nx = x+1;

			if(map[ny][nx] == 'x')//지나갈 수 없다면 스킵
				continue;

			if(dfs(ny,nx))
				return true;//해당 파이프로 이동
		}
		return false;
	}
}
