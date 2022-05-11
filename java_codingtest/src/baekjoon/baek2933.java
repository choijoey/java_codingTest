package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek2933 {

	static char[][] map;
	static int R,C;
	static int[] dy= {0,0,1,-1};
	static int[] dx= {1,-1,0,0};

	static Queue<int[]> beforeMove;

	//구현 문제 
	//문제 이해하는게 제일 어려웠다
	//http://boj.kr/5710f5a9cf6648609512c7edf7979775
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		beforeMove = new LinkedList<>();
		map = new char[R][C];

		for(int i=0;i<R;i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				map[i][j] = tmp[j];
			}
		}

		int N = Integer.parseInt(br.readLine());//던지는 횟수
		st = new StringTokenizer(br.readLine());

		boolean flag =true; //true 왼->오 false 오->왼


		while(N-->0) {

			int cur = Integer.parseInt(st.nextToken());//던지는 높이
			
			cur = R-cur;
			
			//1.막대를 던진다.
			int idx = findIdx(flag,cur);

			flag = !flag; //방향 전환

			if(idx == -1)//맞추는 미네랄 없으면 그냥 스킵
				continue;

			//미네랄 부심
			map[cur][idx]='.';

			//2.던진 막대 지점을 기준으로 bfs를 실행하여 분리되는 클러스터 찾는다
			boolean chk= true;

			for(int i=0;i<4;i++) {
				int ny = cur+dy[i];
				int nx = idx+dx[i];
				
				if(ny>=R||ny<0||nx>=C||nx<0)
					continue;
				
				if(map[ny][nx] =='x') { //맞은 미네랄 근처 4방향에서 bfs 실행 
					if(bfs(cur+dy[i],idx+dx[i],new boolean[R][C])) {//리턴값이 true이면 땅과 연결되어 있지 않은 클러스터 찾음
						chk = false;
						break;
					}
				}
			}
			if(chk)//맵 업데이트 할 필요가 없음
				continue;

			//3.맵 업데이트 한다.
			int size = beforeMove.size();//클러스터 크기
			
			for(int i=0;i<size;i++) {
				int[] tmp = beforeMove.poll();
				map[tmp[0]][tmp[1]] = '.'; //맵에서 지움
				beforeMove.offer(tmp);
			}
			
			//한칸씩 내려가면서 더이상 클러스트가 떨어지지 않는지 확인
			
			chk = false;
			
			while(true) {
				for(int i=0;i<size;i++) {
					int[] tmp = beforeMove.poll();
					tmp[0]+=1;//한칸 내려감
					
					if(tmp[0]==R-1)
						chk= true;
					if(tmp[0]<R-1 && map[tmp[0]+1][tmp[1]]=='x')
						chk = true;
					
					beforeMove.offer(tmp);
				}
				
				if(chk)
					break;

			}
			for(int i=0;i<size;i++) {
				int[] tmp = beforeMove.poll();
				map[tmp[0]][tmp[1]] = 'x'; //맵에서 지움
			}

		}

		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
	}
	static boolean bfs(int y, int x, boolean[][] v) {//땅과 연결되어있으면 true 반환
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {y,x});
		v[y][x] = true;
		
		boolean flag = false;

		beforeMove.offer(new int[] {y,x});

		while(!queue.isEmpty()) {
			int[] cur = queue.poll();

			for(int i=0;i<4;i++) {
				int ny = cur[0] +dy[i];
				int nx = cur[1] +dx[i];

				if(ny>=R||ny<0||nx>=C||nx<0)
					continue;
				if(v[ny][nx]||map[ny][nx] =='.')
					continue;

				if(ny==R-1)//땅과 연결되어있으면 true
					flag = true;

				queue.offer(new int[] {ny,nx});
				v[ny][nx]=true;
				beforeMove.offer(new int[] {ny,nx});
			}
		}

		if(flag) {
			beforeMove.clear();
			return false; //땅과 연결 되어있으면 bfs 다시 
		}
		return true;
	}
	static int findIdx(boolean flag, int height) {

		if(flag) {//왼->오
			int idx=0;
			while(idx<C&&map[height][idx]=='.')idx++;

			if(idx==C)//맞추는 미네랄 없음
				return -1;
			return idx;
		}
		else {//오->왼
			int idx=C-1;
			while(idx>=0&&map[height][idx]=='.')idx--;

			if(idx==-1)//맞추는 미네랄 없음
				return -1;
			return idx;
		}
	}
}
