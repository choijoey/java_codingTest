package baekjoon;
import java.io.*;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] p;
	static boolean[] isKnow;
	static ArrayList<Integer>[] party;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 파티의 수

		party = new ArrayList[M + 1];

		// 파티 리스트 초기화
		for (int i = 1; i <= M; i++) {
			party[i] = new ArrayList<Integer>();
		}

		st = new StringTokenizer(br.readLine());
		int KnowTrue = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
		if (KnowTrue == 0) {
			System.out.println(M);
			return;
		}
		isKnow = new boolean[N + 1]; // 최종적으로 진실을 아는 사람 true, 모르면 false;
		for (int i = 0; i < KnowTrue; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			isKnow[tmp] = true;
		}

		// 파티 정보
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int people = Integer.parseInt(st.nextToken());
			for (int j = 0; j < people; j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		makeSet();

		// 파티 정보 확인
//		for (int i = 0; i < party.length; i++) {
//			System.out.println(party[i].toString());
//		}

		// 파티 정보를 토대로..
		for (int i = 1; i <= M; i++) {
			if (party[i].size() == 1) {
				continue;
			}
			// 처음 사람과 같이 파티를 갔음 --> 부모 노드를 같게 만들어 준다
			int pre = party[i].get(0);
			for (int j = 1; j < party[i].size(); j++) {
				unionSet(pre, party[i].get(j));
				pre = party[i].get(j);
			}
		}

		
		for (int i = 0; i < isKnow.length; i++) {
			if (isKnow[i]) {
				isKnow[findSet(i)] = true;
			}
		}

		int result = 0;
		for (int i = 1; i <= M; i++) {
			if (party[i].size() != 0) {
				int parent = findSet(party[i].get(0));
				if (!isKnow[parent]) {
					result++;
				}
			}
		}
		System.out.println(result);
	}

	// JoinSet 부분

	public static void makeSet() {
		p = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			p[i] = i;
		}
	}

	public static boolean unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot) {
			return false;
		}

		p[bRoot] = aRoot;
		return true;
	}

	public static int findSet(int a) {
		if (a == p[a]) {
			return a;
		}
		p[a] = findSet(p[a]);

		return p[a];
	}
}