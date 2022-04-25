package baekjoon;
import java.io.*;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	//98퍼에서 실패.. 
	static int N, M;
	static int[][] distance;

	public static void main(String[] args) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시의 개수
		M = Integer.parseInt(br.readLine()); // 버스의 개수

		StringTokenizer st;
		distance = new int[N][N];
		
		final int MAX = 999_999_999;
		
		// 거리 배열 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					distance[i][j] = 0;
					continue;
				}
				distance[i][j] = MAX;
			}
		}

		// 입력 받음
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 도시 번호가 1부터 시작하니까 1을 빼줬음!
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			// 같은 도시 출발, 도착인 버스가 두개 이상일 수 있음(노선 두개 이상)
			// 최소 비용을 저장한다
			distance[from][to] = Math.min(distance[from][to], weight);
		}
		// input END

		// 플로이드-와샬
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// k를 거쳐서 가는 비용이 더 적으면 그걸로 갱신
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 갈 수 없는 경우 0 출력
				if (distance[i][j] >= MAX) {
					sb.append("0" + " ");
					continue;
				}
				sb.append(distance[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

}