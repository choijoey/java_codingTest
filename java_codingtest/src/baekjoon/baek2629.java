package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class baek2629 {

	static int[]chu;//추
	static boolean[][] map;
	
	static int N;//추의 개수
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());//추의 개수
		st = new StringTokenizer(br.readLine());
		chu= new int[N+1];
		map = new boolean[N+1][15001];
		
		for(int i=0;i<N;i++) {
			chu[i]=Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());//구슬 개수
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		dfs(0,0);//부분집합으로 가능한지 아닌지 계산 
			
		for(int i=0;i<M;i++) {
			int ball=Integer.parseInt(st.nextToken());
			boolean flag;
			if(ball>15000)
				flag = false;
			else
				flag = map[N][ball];
			if(flag)
				sb.append("Y ");
			else
				sb.append("N ");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		
	}

	static void dfs(int cnt,int val) {
		
		
		if(cnt==N+1||map[cnt][val]) return;

		map[cnt][val]= true;

		dfs(cnt+1,Math.abs(val-chu[cnt]));
		dfs(cnt+1,val+chu[cnt]);
		dfs(cnt+1,val);

	}
}
