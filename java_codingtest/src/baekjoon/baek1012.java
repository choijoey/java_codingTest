package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baek1012 {

	
	static int[][] map;
	static boolean[][] v;
	static int ans;
	
	static ArrayList<int[]> list;
	
	static int[] dy = {-1,1,0,0};//상하좌우
	static int[] dx = {0,0,-1,1};
	
	static int M;
	static int N;
	static int K;
	
	//DFS 특성상 인접한 개수에 따라 시간복잡도가 달라질거 같네요...
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st ;
		int T = Integer.parseInt(br.readLine());
		
		while(T>0) {
			st=new StringTokenizer(br.readLine());
			M =Integer.parseInt(st.nextToken());
			N =Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			v = new boolean[N][M];
			
			ans =0;
			
			
			//배추 개수 
			K =Integer.parseInt(st.nextToken());
			
			//배추 위치 저장
			list = new ArrayList<>();
			
			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				
				//가로
				int x = Integer.parseInt(st.nextToken());
				//세로
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
				list.add(new int[] {y,x});
			}
			
			dfs(0);
			
			bw.write(ans+"\n");
			bw.flush();
			
			T--;
		}
		bw.close();
	}

	
	static void dfs(int start) {
		for(int i=0;i<K;i++) {
			int y =list.get(i)[0]; 
			int x =list.get(i)[1];
			
			if(!v[y][x]) {
				ans++;
				dfs2(list.get(i));
			}
		}
	}
	static void dfs2(int[] idx) {
		

		int y = idx[0];
		int x = idx[1];
		
		//방문했으면 종료
		if(v[y][x])
			return;
		
		v[y][x]=true;
		
		for(int i=0;i<4;i++) {//상하좌우 탐색
			int new_y = y+dy[i];
			int new_x = x+dx[i];
			
			if(new_y>=N||new_y<0||new_x>=M||new_x<0||map[new_y][new_x]==0)
				continue;
			
			dfs2(new int[] {new_y,new_x});	
			
		}
		
	}
}
