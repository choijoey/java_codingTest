package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//재귀로 푸는사람 있던데 코드 봐도 모르겠다..
//아마 이진트리 값 추가할때마다 log N번 비교하기 때문에
// O(N log N)일듯
//http://boj.kr/03a7f5334e404cb9b98545019e71f5d2

public class baek5639 {

	static class Node{
		int val;
		Node left,right;

		public Node(int val) {
			super();
			this.val = val;
		}
		
		void add(int num) {
			if(num<this.val) {
				if(this.left==null) {
					this.left=new Node(num);
				}else {
					this.left.add(num);
				}
				
			}else {
				if(this.right==null) {
					this.right=new Node(num);
				}else {
					this.right.add(num);
				}
			}
		}
	}
	static void post(Node tmp) {
		
		if(tmp.left !=null)
			post(tmp.left);
		if(tmp.right !=null)
			post(tmp.right);
		System.out.println(tmp.val);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Node root = new Node(Integer.parseInt(br.readLine()));
		
		while(true) {
			String s = br.readLine();
			if(s==null)
				break;
			root.add(Integer.parseInt(s));
		}
		post(root);
	}

}
