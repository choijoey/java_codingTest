package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baek2239 {

	static int[][] map;
	
	static boolean[][] row;
	static boolean[][] col;
	static boolean[][] square;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

		int N = 9;
		
		map = new int[N][N];
		
		row = new boolean[N][N+1];//행 1~9 값 저장
		col = new boolean[N][N+1];//열 1~9 값 저장
		square = new boolean[N][N+1];// 작은 3x3 사각형 값 저장
		
		String s = null;
		
		for(int i=0;i<N;i++) {
			s= br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = s.charAt(j)-'0';
				
				row[i][map[i][j]]=true;
				col[j][map[i][j]]=true;
				
				int squareIdx=(i/3)*3+(j/3); // 작은 사각형 좌표 계산 
				square[squareIdx][map[i][j]]=true;
			}
		}
		
		dfs(0);
			
	}
	
	static boolean dfs(int cnt) { //각 칸마다 행 열 사각형 조건에 맞는지 확인하고 재귀 호출한다음 원상태로 복구
		
		if(cnt == 81) {
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			return true;
		}
		
		int j = cnt%9;
		int i = cnt/9;
		
		if(map[i][j]==0) {
			for(int k=1;k<=9;k++) {
				
				int squareIdx=(i/3)*3+(j/3);
				
				if(row[i][k]||col[j][k]||square[squareIdx][k])
					continue;

				map[i][j]=k;
				row[i][k]=true;
				col[j][k]=true;
				square[squareIdx][k]=true;
				
				if(dfs(cnt+1))
					return true;
					
				map[i][j]=0;
				row[i][k]=false;
				col[j][k]=false;
				square[squareIdx][k]=false;
			}
		}
		else
			if(dfs(cnt+1))
				return true;
		
		return false;
	}
	
}
