package baekjoon;


import java.util.ArrayList;
import java.util.Scanner;

public class BJ5577 {
	public static void main(String[] args) {
		Scanner sc  =new Scanner(System.in);
		int N =sc.nextInt();
		
		int[] arr = new int[N];
		ArrayList<Integer> pangPosition = new ArrayList<>();
		
		for(int i = 0 ; i < N ; i++) {
			arr[i] = sc.nextInt();
		}

		int deletedBallMax = Integer.MIN_VALUE;
		for(int i = 0; i < N;i++) { //각 지점 
			for(int value = 1; value <= 3;value++) {
				int deletedBall = gogo(arr,i,N,value);
				if(deletedBallMax <deletedBall) {
					deletedBallMax = deletedBall;
				}

			}
		}
		System.out.println(N-deletedBallMax);
		
	}

	private static int gogo(int[] arr, int i, int N, int value) {
		int left =i-1;
		int right = i+1;
		int deletedBall= 0;
		int count = 1; 
		
		while(true) {
			while( left >= 0 && arr[left] == value ) {
				count++;
				left--;
			}
			while(right < N &&arr[right] ==value) {
				count++;
				right++;
			}
			
			if(count<4) {
				break;
			}
			deletedBall+= count;
			count = 0;
			if(left>=0) {
				value = arr[left];
			}
			if(right < N) {
				value = arr[right];
			}
		}
		
		return deletedBall;
	}
}
