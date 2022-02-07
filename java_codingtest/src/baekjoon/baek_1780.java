package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baek_1780 {

	
	static int N;
	
	//0개수 1 개수 -1 개수 
	static int zero_cnt,one_cnt,m_one_cnt;
	
	static int[][] map;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		//map 생성 
		map = new int[N][N];
		
		//map 정보 입력 
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		//재귀 함수 호출 
		recur_cal(N,0,0);
		
		
		//정답 출력 
		StringBuilder sb= new StringBuilder();
		sb.append(m_one_cnt+"\n"+zero_cnt+"\n"+one_cnt);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
		
		
	}
	
	static void recur_cal(int size ,int x_idx,int y_idx ) {//탐색하는 배열 크기 , x 인덱스 , y 인덱스 
		int tmp = map[x_idx][y_idx]; //탐색하는 배열의 첫번 째 값 
		
		if(size == 1) { //만약 size ==1 이면 바로 계산하고 return 
			if(tmp == 0)
				zero_cnt+=1;
			else if(tmp == 1)
				one_cnt+=1;
			else if(tmp == -1)
				m_one_cnt+=1;
			return;
		}
		
		
		//size 크기만큼 배열 탐색 
		
		boolean flag = true;
		outer:for(int i=x_idx ;i<x_idx+size;i++) {
			for(int j= y_idx ;j< y_idx+size;j++) {
				if(map[i][j] != tmp) {
					flag = false;
					break outer;
				}
			}
		}
		//모두 같은 종류라면 return 
		if(flag) {
			if(tmp == 0)
				zero_cnt+=1;
			else if(tmp == 1)
				one_cnt+=1;
			else if(tmp == -1)
				m_one_cnt+=1;
			return;
		}
		
		//같은 종류가 아니라면 사이즈 /3 하고 9번 재귀 함수 호출 
		recur_cal(size/3,x_idx,y_idx);
		recur_cal(size/3,x_idx,y_idx+size/3);
		recur_cal(size/3,x_idx,y_idx+(size/3)*2);
		
		recur_cal(size/3,x_idx+size/3,y_idx);
		recur_cal(size/3,x_idx+size/3,y_idx+size/3);
		recur_cal(size/3,x_idx+size/3,y_idx+(size/3)*2);
		
		recur_cal(size/3,x_idx+(size/3)*2,y_idx);
		recur_cal(size/3,x_idx+(size/3)*2,y_idx+size/3);
		recur_cal(size/3,x_idx+(size/3)*2,y_idx+(size/3)*2);
		
	}
}
