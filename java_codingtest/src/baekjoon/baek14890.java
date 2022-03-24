package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


// 각 배열 체크하는데 N 걸리는거 같아서 O(N^2)

public class baek14890 {

	static int[][] map;
	static int N;
	static int L;

	static boolean chk(int[] list) {

		boolean[] v = new boolean[N];


		int idx=0;

		while(idx<N-1) {
			//경사로가 같으면 패스
			if(list[idx] ==list[idx+1]) {
				idx++;
				continue;
			}
			//경사로 차이가 2 이상 false
			if(Math.abs(list[idx]-list[idx+1])>1)
				return false;

			//경사로 차이가 1 (내리막)
			if((list[idx]-list[idx+1])==1) {

				//경사로 놓기엔 배열 길이가 부족한 경우
				if(idx+L>=N)
					return false;

				//경사로 놓기 가능한지 확인
				for(int i=idx+1;i<idx+L;i++) {
					if(list[i]!=list[i+1])
						return false;
					v[i] = true; // 경사로 놓았다고 표시
				}
				idx+=L;
				v[idx] = true;//경사로 마지막 idx 비교
				continue;
			}

			//경사로 차이가 -1 (오르막)
			if((list[idx]-list[idx+1])==-1) {

				//경사로 놓기엔 배열 길이가 부족한 경우
				if(idx-L<-1)
					return false;

				//경사로 놓기 가능한지 확인
				for(int i=idx;i>idx-L+1;i--) {
					if(list[i]!=list[i-1])
						return false;
					if(v[i])//경사로 놓아진 곳이면 종료
						return false;
					
					v[i]=true;
				}
				if(v[idx-L+1])//경사로 마지막 idx 비교
					return false;
				
				idx++;
				continue;
			}

		}


		return true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}

		int ans =0;

		//가로 줄 탐색
		for(int i=0;i<N;i++) {
			if(chk(map[i])) {
				ans++;
//				System.out.println("가로줄: "+ i);
			}
		}

		//세로 줄 탐색
		for(int i=0;i<N;i++) {
			int[] tmpList= new int[N];
			for(int j=0;j<N;j++) {
				tmpList[j] = map[j][i];
			}
			if(chk(tmpList)) {
				ans++;
//				System.out.println("세로줄: "+ i);
			}
		}

		bw.write(ans+"");
		bw.flush();
		bw.close();
	}

}
