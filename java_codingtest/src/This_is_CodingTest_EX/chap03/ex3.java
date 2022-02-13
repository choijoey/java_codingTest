package This_is_CodingTest_EX.chap03;

import java.util.Scanner;

public class ex3 {
	//96pg
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] map = new int[N][M];
		
		int max = -1;
		
		for(int i=0;i<N;i++) {
			int min=10001;
			for(int j=0;j<M;j++) {
				map[i][j]=sc.nextInt();
				
				if(map[i][j] <min)
					min = map[i][j];
			}
			if(min>max) {
				max = min;
			}
		}
		
		System.out.println(max);
		
	}

}
