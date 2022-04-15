package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek1043 {

	//http://boj.kr/e81ad0fcbeb640fa8d10f85a24a5b3fb
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;


		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //사람 수
		int M = Integer.parseInt(st.nextToken()); //파티 수

		st = new StringTokenizer(br.readLine());
		int tSize = Integer.parseInt(st.nextToken()); //진실 아는 사람 수
		int[] truth = new int[N+1];

		for(int i =0; i <tSize;i++)
			truth[Integer.parseInt(st.nextToken())]++; //진실 아는 사람이면 1 아니면 0

		int[][] map = new int[M][]; //파티 배열
		makeSet(N);//parent 배열 생성

		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			map[i] = new int[tmp];

			for(int j=0;j<tmp;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}

			if(map[i].length >1) { //파티 참석 인원이 2명 이상일 경우 union
				int person =map[i][0]; //임시 parent

				for(int s=0;s<tmp;s++) {
					int p = findSet(map[i][s]); //이 부분 틀려서 한참 고민함 ㅠㅠ
					if(truth[p]==1) {
						person = p;
						break;
					}
				}

				for(int s=0;s<tmp;s++) {
					union(person,map[i][s]);//진실을 아는사람을 parent로
				}

			}
		}
		//입력 끝
		int ans =0;

		for(int i=0;i<M;i++) {
			int[] cur = map[i];
			int size = cur.length;
			boolean flag = true; //파티 안에 진실 아는 사람 있으면 true

			for(int j=0;j<size;j++) {
				if(truth[findSet(cur[j])]==1) {
					flag = false;
					break;
				}
			}
			if(flag)
				ans++;
		}

		System.out.println(ans);



	}

	static int[] par;
	static void makeSet(int N) {
		par = new int[N+1];

		for(int i=1;i<=N;i++)
			par[i] = i;
	}
	static int findSet(int num) {
		if(par[num] == num)
			return num;

		return par[num]=findSet(par[num]);
	}
	static boolean union(int a, int b) {

		a=findSet(a);
		b=findSet(b);

		if(a==b)
			return false;

		par[b] = a;
		return true;
	}

}
