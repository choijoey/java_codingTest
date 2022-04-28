package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek1194 {

	//노래들으면서 하니까 꿀잼 ㅋㅋㅋ
	
	//3차원 BFS+ 그리디 + 비트마스킹이 포함된 문제였다.
	//문제를 너무 쉽게 봐서 오히려 예외조건을 생각하는데 시간이 오래 걸렸다.
	//이미 지나온 길을 되돌아가야하는 경우가 있는데 무작정 코딩하느라 생각을 못했다.
	//3차원 BFS를 풀기 전에 항상 상태(3차원)이 어떻게 바뀔지 예외  조건을 생각하고 풀자!
	//http://boj.kr/ac520943ed12466492f9d128b7e4a774
	
	//다른 풀이보니까 그냥 3차원 배열을 [N][M][키의 모든 상태 2^6=64]으로 해도 해결이 된다.
	//괜히 메모리초과날까봐 개쫄아서 다른풀이를 생각하느라 제 시간안에 못 푼 원인인것 같다.
	//https://syh39.github.io/algorithm/algorithm_2/
	//다음에는 공간복잡도도 계산해서 풀어야겠다.
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		int N = Integer.parseInt(st.nextToken());//세로
		int M = Integer.parseInt(st.nextToken());//가로
		
		char[][] map = new char[N+2][M+2];//[가로][세로]
		int[][][] v = new int [N+2][M+2][7];//[가로][세로][열쇠 개수]
		
		int curY=0,curX=0;
		

		for(int i=1;i<=N;i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=1;j<=M;j++) {
				map[i][j]=tmp[j-1];
				Arrays.fill(v[i][j], -1);
				if(map[i][j]=='0') {//현재 위치 저장
					curY=i;
					curX=j;
				}
			}
		}
		
		//벽 두르기
		for(int i=0;i<N+2;i++) {
			map[i][0]='#';
			map[i][M+1]='#';
		}
		for(int i=0;i<M+2;i++) {
			map[0][i]='#';
			map[N+1][i]='#';
		}
	
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {curY,curX,0,0});//위치좌표,현재 열쇠 상태를 저장,키 비트마스킹
		//열쇠를 먹으면 다른 층으로 간다고 생각하고 그 층의 2차원 배열에서 방문체크
		
		int[] dy = {1,-1,0,0};
		int[] dx = {0,0,-1,1};
		
		int ans =-1;//정답이 없으면 -1
		int level=0;//bfs 레벨
		
		outer:while(!queue.isEmpty()) {
			
			int size =queue.size();
			
			for(int i=0;i<size;i++) {
				int[] cur = queue.poll();
				
				for(int j=0;j<4;j++) {
					int ny = cur[0]+dy[j];
					int nx = cur[1]+dx[j];
					int stat = cur[2];//현재 어떤 층인지 상태
					int curKey = cur[3];//현재 가지고 있는 키
					
					if(map[ny][nx]=='#')
						continue;
					if(map[ny][nx]=='1') {//목적지 도착
						ans = level+1;
						break outer;
					}

					if((map[ny][nx]-'A')>=0&&(map[ny][nx]-'A')<6) {//문을 만날 경우
						
						if((curKey&1<<(map[ny][nx]-'A')) == 0)//열쇠가 없다면 스킵
							continue;
					}
					
					if((map[ny][nx]-'a')>=0&&(map[ny][nx]-'a')<6) {//열쇠가 있는 칸일 경우
						
						curKey=curKey|1<<(map[ny][nx]-'a');//열쇠 습득
						
						if(v[ny][nx][map[ny][nx]-'a'+1]<curKey) {//해당층(키를 먹고 올라간 층)에서 만약 v의 다음칸 보다 자기 키값이 클 경우(키를 먹고 왔던길을 되돌아가는 경우)
							queue.offer(new int[] {ny,nx,map[ny][nx]-'a'+1,curKey});
							v[ny][nx][map[ny][nx]-'a'+1]=curKey;
						}
					}
					
					if(v[ny][nx][stat]<curKey) {//현재 층에서 만약 v의 다음칸 값보다 자기 키값이 클경우
						queue.offer(new int[] {ny,nx,stat,curKey});
						v[ny][nx][stat]=curKey;
					}
				}
			}
			
			if(!queue.isEmpty())
				level++;
		}
		System.out.println(ans);
	}
}