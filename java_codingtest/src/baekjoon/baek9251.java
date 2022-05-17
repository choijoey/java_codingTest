package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//메모리제이션을 이용한 풀이
//Top Down 방식으로 해결

//O(MN) 테이블을 채우기 위해 각 칸마다 한번씩 연산을 수행한다.
//http://boj.kr/0c5eab3946a047469fceafee7094e8b8


//dp를 이용한 풀이
//http://boj.kr/4a3fda577fd84750acc25b3c5bfc204f

public class baek9251 {

	static int[][] memo;
	static char[] s1,s2;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		s1 = br.readLine().toCharArray();
		s2 =  br.readLine().toCharArray();
		
		memo = new int[s1.length+1][s2.length+1];
		
		for(int i=0;i<s1.length+1;i++)//방문 안한 테이블을 판별하기 위해 -1로 값을 채운다
			Arrays.fill(memo[i], -1);
		
		dfs(0,0);
		System.out.println(memo[0][0]);
	}

	static int dfs(int i, int j) {
		
		if(memo[i][j] !=-1) //이미 방문한 칸이면 해당 칸의 값 리턴
			return memo[i][j];
		
		if(i==s1.length||j==s2.length) {//범위를 벗어나면 같은 문자가 없는 거라 0  리턴
			return 0;
		}
		else if(s1[i]==s2[j]) {//문자가 같다면 해당 칸은 다음 탐색값 +1
			return memo[i][j] = dfs(i+1,j+1) +1; 
		}
		else {//문자가 같지 않다면 오른쪽 인덱스와 왼쪽 인덱스를 각각 늘려서 재귀호출한다음 큰 값을 해당 칸의 값으로 한다.
			return memo[i][j] =Math.max(dfs(i,j+1), dfs(i+1,j));
		}
	}
}
