package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] tree = new int[999_999_999];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 전위 순회 --> 후위 순회
		// Root L R (전위 순회) -->
		// L R Root (후위 순회)

		// 완전 이진 트리 생성
		String input;
		while (true) {
			input = br.readLine();

			// 입력 아무것도 없으면 탈출
			if (input == null || input.equals("")) {
				break;
			}
			int num = Integer.parseInt(input);
			insert(num);
		}

		// 트리 완성
		postOrder(1);
	}

	public static void insert(int num) {
		int curNode = 1; // 루트 노드부터 탐색 시작
		while (true) { // 삽입 가능한 위치까지 찾음
			if (tree[curNode] == 0) {
				break;
			}
			if (num > tree[curNode]) {
				curNode = curNode * 2 + 1;
			}

			if (num < tree[curNode]) {
				curNode = curNode * 2;
			}
		}
		tree[curNode] = num;
	}

	public static void postOrder(int n) {
//		System.out.println("N : " + tree[n] + " left : " + tree[n * 2] + " right : " + tree[n * 2 + 1]);
		
		if(tree[n]==0)
			return;
		if (tree[n * 2] != 0) {
			postOrder(n * 2);
		}
		if (tree[n * 2 + 1] != 0) {
			postOrder(n * 2 + 1);
		}
		System.out.println(tree[n]);

	}
}