package swacademey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea5658 {

	//해쉬 이용하면 쉬운문제 ?
	
	//N개의 문자를 하나씩 움직이면서 N번 연산을 수행 O(N^2) + 마지막 정렬 O(NlogN)
	//시간복잡도는 O(N^2)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=  new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());//숫자 길이
			int K = Integer.parseInt(st.nextToken());//K번째로 큰 수 찾기
			
			int len = N/4; //각 숫자 길이
			
			Queue<Character> list = new LinkedList<>();//숫자를 큐에 넣기
			
			char[] tmp = br.readLine().toCharArray();
			for(int i=0;i<tmp.length;i++) {
				list.offer(tmp[i]);
			}
			
			set = new HashSet<>();
			for(int i=0;i<N;i++) {

				char[] num= new char[len];//숫자 조합
				
				for(int k=0;k<N/len;k++) {//조합되는 숫자 개수
					for(int j=0;j<len;j++) {//숫자 조합시키기
						num[j]=list.poll();
						
						list.offer(num[j]);
					}
//					System.out.println(num);
					cal(num);//조합한 숫자 hashSet에 추가
				}

				char next='\0';
				
				
//				for(int j=0;j<N-1;j++) {//다음 조합.
//					next = list.poll();
//					list.offer(next);
//				}
				
				//해쉬 사용하면 그냥 모든 경우의 수 구하면 되기 때문에 시계방향으로 회전시켜도 된다.
				next = list.poll();
				list.offer(next);
			}
			
			ArrayList<Integer> ans = new ArrayList<>(set);
			Collections.sort(ans,(o1, o2)-> -(o1-o2));//내림차순으로 정렬
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(ans.get(K-1)).append("\n");
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
	}
	static HashSet<Integer> set;
	
	static void cal(char[] tmp) {//16진수 -> 10진수로 변환
		int size = tmp.length;
		
		int val =0;
		
		for(int i=0;i<size;i++) {
			int num=0;
			
			if(tmp[i]-'A' >=0) {//문자
				num= tmp[i]-'A' +10;
			}
			else {//숫자
				num = tmp[i]-'0';
			}
			val+= num*Math.pow(16, size-i-1);
		}
		set.add(val);
	}

}
