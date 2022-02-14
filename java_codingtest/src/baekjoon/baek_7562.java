package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


//배열 전체를 탐색하긴 하는데 size 100 => 67 size 1000 667  이정도라
//시간복잡도는 N정도인듯 합니다..

public class baek_7562 {

	static int size;
	
	static int[][] map;
	
	static int[] cur;
	static int[] to;
	
	static int[] dx= {1,2,2,1,-1,-2,-2,-1};
	static int[] dy= {-2,-1,1,2,2,1,-1,-2};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//테스트 케이스 수
		int T = Integer.parseInt(br.readLine());
		
		while(T>0) {
		
			//맵 사이즈
			size = Integer.parseInt(br.readLine());
			
			map = new int[size][size];
			
			//현재 위치
			StringTokenizer st = new StringTokenizer(br.readLine());
			cur = new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			
			//목적지 위치 
			st = new StringTokenizer(br.readLine());
			to =new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			
			//정답
			int ans=0;
			
			bfs(cur);
			
			
			ans=map[to[0]][to[1]];
			
			bw.write(ans+"\n");
			bw.flush();
			T--;
		}	
		bw.close();
	}
	
	static void bfs(int[] start) {
		
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			
			int y = tmp[0];
			int x = tmp[1];
			
			for(int i=0;i<8;i++) {
				
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(ny>=size||ny<0||nx>=size||nx<0)
					continue;
				
				if(map[ny][nx] != 0)
					continue;
				
				//탐색하는 위치가 시작위치면 continue
				if(ny==cur[0] &&nx==cur[1])
					continue;
				
				//ny nx 좌표에 기존 값 +1 => 넓이 증가 
				map[ny][nx] = map[y][x]+1;
				
				//도작 좌표가 ny nx이면 종료
				if(ny==to[0] && nx==to[1])
					return;
				
				queue.offer(new int[] {ny,nx});
				
			}
			
		}
		
	}
	
	
	

}
