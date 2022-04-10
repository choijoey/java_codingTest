package study;

import java.util.Arrays;
import java.util.Scanner;

public class 순조부 {

	static int N,R;
	static int[] input, numbers; //input: 입력 수 배열 numbers: 선택 수 배열
	static boolean[] v;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		
		input = new int[N];
		numbers = new int[R];
		
		v = new boolean[N];
		
		for(int i=0;i<N;i++)
			input[i] = sc.nextInt();
		
		
		
		Arrays.sort(numbers); // np쓰기 전에 오름차순으로 정렬
		
		do {
			//실행 할 내용 
		}while(np());
		
		
	}

	static void permutation1(int cnt) {
		
		if(cnt ==R) { //종료조건
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i=0;i<N;i++) {
			
			if(v[i])		//기존에 뽑은 숫자는 패스
				continue;
			
			numbers[cnt] = input[i]; //수를 뽑고 배열에 저장
			
			v[i]= true;				//뽑았으므로 true
			permutation1(cnt+1);
			v[i]= false;			//뽑은 값은 다시 false해서 원상복구
		}
	}
	
	static void combination1(int cnt,int start) {
		
		if(cnt ==R) { //종료조건
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i=start;i<N;i++) {//선택한 다음 수 부터 선택하므로 중복체크 안함
		
			numbers[cnt] = input[i]; //수를 뽑고 배열에 저장
			
			combination1(cnt+1,i+1);
		}
	}
	
	
	//중복해서 뽑을 수 있으면 방문체크는 필요없음 
	static void permutationWithRepetition(int cnt) { //중복순열 
		
		if(cnt ==R) { //종료조건
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i=0;i<N;i++) {
		
			numbers[cnt] = input[i]; //수를 뽑고 배열에 저장
			
			permutationWithRepetition(cnt+1);
		}
	}
	static void combinationWithRepetition(int cnt,int start) { //중복조합
		
		if(cnt ==R) { //종료조건
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i=start;i<N;i++) {//선택한 다음 수 부터 선택하므로 중복체크 안함
		
			numbers[cnt] = input[i]; //수를 뽑고 배열에 저장
			
			combinationWithRepetition(cnt+1,i);//다음에 선택할 수는 현재 선택한 수를 포함하여 선택
		}
	}
	
	//부분집합
	static void subSet(int cnt) { //중복조합
		
		if(cnt ==R) { //종료조건
			for(int i=0;i<N;i++) {
				//v[i]가 참이면 부분집합 원소 
			}
			return;
		}
		
		v[cnt]=true; // 현재 원소를 선택
		subSet(cnt+1);
		
		v[cnt]=false;// 현재 원소를 선택하지 않음 
		subSet(cnt+1);
	}
	
	
	//비트마스킹은 중복체크를 하는 배열을 사용하지 않으려고 사용 => 중복체크가 필요없는 (조합, 중복순열,중복조합)은 사용 X
	
	static void permutation2(int cnt,int flag) {
		
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i=0;i<N;i++) {
			
			if((flag & (1<<i)) != 0) //2^i 값이 결과로 나오기 때문에 1과 비교하면 안됨
				continue;
			
			numbers[cnt] =input[i]; 
			
			permutation2(cnt+1,flag|1<<i); //파라미터로 flag를 넘겨주기 때문에 원상복구는 필요 X
			
		}
	}
	
	static void subSet2() {
		
		int N = input.length; //원소의 수
		
		//caseCount 부분집합의 개수 2^N  => flag가 0 부터 1씩 증가하면서 2^N-1개 만큼의 부분집합 생성
		for(int flag=0, caseCount=1<<N ;flag <caseCount;flag++) {
			
			for(int i=0;i<N;i++) {
				if((flag & (1<<i)) != 0) {//0이 아닐 경우 선택된 수 
					System.out.print(input[i]+" "); 
				}
			}
			System.out.println();
		}
	}
	
	
	
	//np함수같은 경우 오름차순 정렬 => 내림차순으로 정렬되면서 사전순서의 모든 경우를 구할 수 있음=> 같은 원소여도 다른 취급하는 순열이랑은 다름
	//즉 순서를 생각하지 않고 사전순서로 정렬하기 때문에 조합에 응용 될 수 있음
	//미리 오름차순으로 정렬해 놓고 맨 우측 꼭대기를 기준으로 왼쪽으로 움직이면서 정렬하는 방식
	
	static boolean np() {
		
		int i = N-1;
		while(i>0&&numbers[i-1]>=numbers[i])
			i--;
		
		if(i==0) //제일 꼭대기가 첫 원소로 왔기 때문에 내림차순 정렬 완료 
			return false;
		
		int j = N-1;
		while(numbers[i-1]>=numbers[j]) // 꼭대기 왼쪽값과  swap할 값 고름
			j--;
		
		swap(i-1,j);
		
		int k = N-1;
		while(i<k)//꼭대기부터 배열 끝 값들을 차례대로 swap
			swap(i++,k--);
		
		return true;//순열을 만들 수 있으므로 true
	}
	static void swap(int a,int b) {
		int tmp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] =tmp;
	}
}
