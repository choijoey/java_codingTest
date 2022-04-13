package baekjoon;
import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Shark[][] board = new Shark[R][C];
		int[][] coords = new int[M][];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(s, d, z);
			board[r][c] = shark;
			coords[i] = new int[] {r, c};
		}
		
		System.out.println(calcCatchSum(board, coords, R, C));
	}
	
	private static int calcCatchSum(Shark[][] board, int[][] coords, int R, int C) {
		int sum = 0;
		for (int j = 0; j < C; j++) { // 낚시왕의 현재 위치
			sum += catchShark(board, j);
			coords = simulateShark(board, coords, R, C);
		}
		
		return sum;
	}

	private static int[][] simulateShark(Shark[][] board, int[][] coords, int R, int C) {
		int[][] nextCoords = new int[coords.length][];
		Shark[] sharks = new Shark[coords.length];
		
		for (int i = 0; i < coords.length; i++) {
			int[] coord = coords[i];
			if (coord == null) continue;
			
			// 1. 해당 위치에 있는 상어 객체 가져오기
			int x = coord[0];
			int y = coord[1];
			Shark shark = board[x][y];
			
			// 2. 그런데 상어가 잡아먹혔거나해서 없을 수가 있다. 이 경우는 좌표도 null로 만들어주고 continue
			if (shark == null) {
				coord = null;
				continue;
			}
			
			// 3. 이제 찾은 상어의 이동을 시뮬레이션 한다.
			// 모든 상어가 동시에 움직이기 때문에, 이 반복문 안에서는 어느 위치로 간다는 좌표만 기억해둔다.
			nextCoords[i] = shark.getNextCoords(x, y, R, C);
			sharks[i] = shark;
			board[x][y] = null;
		}
		
		// 기억해둔 다음 좌표를 가지고 실제 이동 + 잡아먹기 시뮬레이션
		for (int i = 0; i < sharks.length; i++) {
			Shark shark = sharks[i];
			if (shark == null) continue;
			
			int[] coord = nextCoords[i];
			int x = coord[0];
			int y = coord[1];
			
			if (board[x][y] == null || board[x][y].size < shark.size) {
				board[x][y] = shark;
			}
		}
		
		return nextCoords;
	}

	private static int catchShark(Shark[][] board, int j) {
		// 현재 열에 있는 가장 가까운 상어를 잡는다.
		for (int i = 0; i < board.length; i++) {
			if (board[i][j] != null) {
				int size = board[i][j].size;
				// 이 상어는 사라졌으므로 null로 대체한다.
				board[i][j] = null;
				return size;
			}
		}
		
		// 없으면 0 리턴
		return 0;
	}
	

	public static class Shark {
		public int speed;
		public int direction; // 0부터 상하우좌
		public int size;
		public int dirR;
		public int dirC;
		
		public static int[] deltaR = {-1, 1, 0, 0};
		public static int[] deltaC = {0, 0, 1, -1};

		public Shark(int s, int d, int z) {
			speed = s;
			dirR = deltaR[d];
			dirC = deltaC[d];
			size = z;
		}

		public int[] getNextCoords(int x, int y, int R, int C) {
			x = getPositiveMod(x + speed * dirR, 2 * (R - 1));
			y = getPositiveMod(y + speed * dirC, 2 * (C - 1));
			// x는 0 ~ 2R 범위에 있다.
			// R ~ 2R: 방향 바뀜. 위치는 2R - x
			// 0 ~ R: 방향 안 바뀜
			
			// 방향 바꾸기
			if (x >= R - 1) {
				dirR = -dirR;
				x = 2 * (R - 1) - x;
			}
			if (y >= C - 1) {
				dirC = -dirC;
				y = 2 * (C - 1) - y;
			}
			
			return new int[] {x, y};
		}

		private static int getPositiveMod(int x, int mod) {
			return ((x % mod) + mod) % mod;
		}
		
	}

}
