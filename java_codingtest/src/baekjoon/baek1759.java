package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


//시간복잡도 O(C Combination L)
//정렬은 L>=3 이라 시간복잡도 고려 안함

public class baek1759 {

	static int L,C;
	static int[] selected;

	//주어진 배열이 조건에 맞는지 확인 
	static boolean chk(char[] nums) {
		int xcnt=0;//모음 개수
		int ycnt=0;//자음 개수

		for(int i=0;i<L;i++) {
			if(nums[i] == 'a' ||nums[i] == 'e' ||nums[i] == 'i' ||nums[i] == 'o' ||nums[i] == 'u')
				xcnt++;
			else
				ycnt++;
		}

		if(xcnt==0 || ycnt <2)
			return false;

		return true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		char[] list = new char[C];

		for(int i=0;i<C;i++) {
			list[i] = (char) st.nextToken().charAt(0);
		}
		
		Arrays.sort(list); //알파벳 순서는 변하지 않으므로 미리 정렬
		
//		System.out.println(Arrays.toString(list));

		selected = new int[C]; //배열 값이 -1이면  같은 인덱스인 list 값 선택 

		StringBuilder sb = null;

		for(int i=0;i<L;i++) { //np할 때 앞에서부터 -1 넣으면 앞에서부터 조합이 뽑아집니다	(강의에선 뒤에서부터 1 넣어서 뒤에서부터 뽑아졌음)
			selected[i] = -1;
		}

		do{

			char[] tmp = new char[L];
			int cnt=0;
			
			for(int i=0;i<C;i++) {
				if(selected[i]==-1)
					tmp[cnt++] = list[i];
			}
//			System.out.println(Arrays.toString(selected));
//			System.out.println(Arrays.toString(tmp));
			
			
			if(chk(tmp)) {
				sb = new StringBuilder();
				for(int i=0;i<L;i++) {
					sb.append(tmp[i]);
				}
				sb.append("\n");
				bw.write(sb.toString());
			}
			
		}while(np());


		bw.flush();
		bw.close();



	}

	static boolean np() {

		//1 꼭대기
		int i= C-1;
		while(i>0 && selected[i-1]>=selected[i])
			i--;

		if(i==0)
			return false ; //종료

		//2 j찾기
		int j =C-1;
		while(selected[i-1]>=selected[j])
			j--;

		//3 swap
		swap(j,i-1);

		//4 뒤 정렬
		int k= C-1;
		while(i<k)
			swap(k--,i++);

		return true;

	}
	static void swap(int x,int y) {
		int tmp = selected[x];
		selected[x] =selected[y];
		selected[y] = tmp;
	}

}
