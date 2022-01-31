package baekjoon;


import java.util.Scanner;


public class baek_1654 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int N = sc.nextInt();
		
		long[] arr =new long[K];
		
		long max = -1;
		long min = 1;

		
		for(int i =0 ; i <K;i++) {
			arr[i]=sc.nextLong();
			if(max<arr[i]) {
				max = arr[i];
			}
		}
		long cnt =0;
		long mid=(max+min)/2;
		
		while(min<=max ) {	
			cnt =0;
			for(int i=0;i<K;i++) {
				cnt=cnt+arr[i]/mid ;
			}
			
			if(cnt<N) {
				max = mid-1;
			}else if (cnt>=N) {
				min = mid+1;
			}
			mid =(max+min)/2;
		}
		
		System.out.println(mid);

	}

}
