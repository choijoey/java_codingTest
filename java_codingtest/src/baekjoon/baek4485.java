package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek4485 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		
		
		int[] dy = {0,0,1,-1};
		int[] dx = {1,-1,0,0};
		
		int t=1; //테케 횟수
		
		while(true) { 
			
			int N = Integer.parseInt(br.readLine());
			
			if(N==0)// N이 0이면 종료
				break;
			
			int[][] map = new int[N][N]; //실제 맵
			int[][] tmpMap = new int[N][N];// 지나가는데 드는 비용을 저장한 맵
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					tmpMap[i][j] = 999_999_999;
				}
			}
			
			for(int i=0;i<N;i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<int[]> queue = new LinkedList<>();
			
			queue.offer(new int[] {0,0});
			tmpMap[0][0] = map[0][0];
			
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				for(int i=0;i<4;i++) {
					
					int ny = cur[0] + dy[i];
					int nx = cur[1] + dx[i];
					
					if(ny<0||ny>=N||nx<0||nx>=N)
						continue;
					
					//방문한 지점을 건너뛰기 전에 만약 그 값이 최소값이 아니면 갱신 
					//값이 갱신 되는 상황이면 큐에 추가 
					if(map[ny][nx]+tmpMap[cur[0]][cur[1]]<tmpMap[ny][nx]) {
						tmpMap[ny][nx] = map[ny][nx]+tmpMap[cur[0]][cur[1]];
						queue.offer(new int[] {ny,nx});
					}
	
				}
			}
			sb = new StringBuilder();
			sb.append("Problem ").append(t++).append(": ").append(tmpMap[N-1][N-1]).append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
	}
}
