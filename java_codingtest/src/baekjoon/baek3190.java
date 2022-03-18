package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baek3190 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//보드 크기
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];

		//사과 개수
		int K = Integer.parseInt(br.readLine());
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]=2;
		}

		//방향 변환 횟수
		int L = Integer.parseInt(br.readLine());

		int[][] turn = new int[L][2];

		for(int i=0;i<L;i++) {
			st = new StringTokenizer(br.readLine());
			turn[i][0]=Integer.parseInt(st.nextToken());
			if(st.nextToken().charAt(0)=='D')
				turn[i][1]=1;
			else
				turn[i][1]=0;
		}

		//현재 머리 위치
		int cur_x =0,cur_y=0;

		//turn idx 위치 
		int turn_idx=0;
		//움직일 방향 0상 1우 2하 3좌
		int heading = 1;
		//시간
		int ans =0;

		//뱀 몸통 좌표 저장 
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0,0});
		map[0][0] = 1;

		//게임 시작 
		while(true) {

			ans++;

			if(turn_idx <L) {
				//움직일 방향 정함 
				if(turn[turn_idx][0] ==ans-1) {
					//방향을 왼쪽으로 틀 경우
					if(turn[turn_idx][1] == 0) {
						if(heading == 0)
							heading=3;
						else
							heading--;
					}//오른쪽으로 틀 경우
					else {
						if(heading==3)
							heading=0;
						else
							heading++;
					}
					turn_idx++;
				}
			}
			//움직일 방향 0상 1우 2하 3좌
			//방향에 맞춰서 한칸 움직임
			if(heading == 0) {
				cur_y -=1;
			}
			else if(heading ==1) {
				cur_x +=1;
			}
			else if(heading ==2) {
				cur_y+=1;
			}
			else if(heading ==3) {
				cur_x-=1;
			}

			//종료 조건 1 범위 벗어날 때
			if(cur_x >=N ||cur_x<0 ||cur_y>=N||cur_y<0)
				break;
			//종료조건 2 몸통 만날 때
			if(map[cur_y][cur_x]==1)
				break;

			//이동한 칸에 사과가 없다면
			if(map[cur_y][cur_x]!=2){
				int[] tail =queue.poll();
				map[tail[0]][tail[1]] =0;
			}

			queue.offer(new int[] {cur_y,cur_x});
			map[cur_y][cur_x]=1;


//			System.out.println("ㅇㅇㅇ"+queue.size());
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
//			System.out.println();

		}
		System.out.println(ans);

	}

}
